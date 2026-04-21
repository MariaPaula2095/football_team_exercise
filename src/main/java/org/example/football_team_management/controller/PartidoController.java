package org.example.football_team_management.controller;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.dto.ResultadoPartidoDTO;
import org.example.football_team_management.service.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    // CRUD endpoints
    @GetMapping ("/listar")
    public ResponseEntity<List<PartidoDto>> listar() {
        return ResponseEntity.ok(partidoService.listar());
    }

    @PostMapping ("/guardar")
    public ResponseEntity<PartidoDto> crear(@RequestBody PartidoDto partidoDto) {
        PartidoDto nuevo = partidoService.guardar(partidoDto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar(/{id}")
    public ResponseEntity<PartidoDto> actualizar(@PathVariable Long id, @RequestBody PartidoDto partidoDto) {
        PartidoDto actualizado = partidoService.actualizar(id, partidoDto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        partidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Consulta nativa #3: GET /api/partidos/total-goles/{equipoId}

    @GetMapping("/total-goles-Por/{equipoId}")   // ← nombre correcto
    public ResponseEntity<Integer> obtenerTotalGolesEquipo(@PathVariable("equipoId") Long equipoId) {
        Integer total = partidoService.obtenerTotalGolesEquipo(equipoId);
        return ResponseEntity.ok(total);
    }

    // Consulta nativa #4: GET /api/partidos/resultados
    @GetMapping("/resultados")
    public ResponseEntity<List<ResultadoPartidoDTO>> obtenerResultadosConNombres() {
        return ResponseEntity.ok(partidoService.obtenerResultadosConNombres());
    }
}