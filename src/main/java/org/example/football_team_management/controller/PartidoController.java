package org.example.football_team_management.controller;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.dto.ResultadoPartidoDTO;
import org.example.football_team_management.service.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PartidoDto>> listar() {
        return ResponseEntity.ok(partidoService.listar());
    }

    @PostMapping("/guardar")
    public ResponseEntity<PartidoDto> guardar(@RequestBody PartidoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidoService.guardar(dto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        partidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PartidoDto> actualizar(@PathVariable Long id, @RequestBody PartidoDto dto) {
        return ResponseEntity.ok(partidoService.actualizar(id, dto));
    }

    // GET /api/partidos/total-goles/{equipoId}
    @GetMapping("/total-goles/{equipoId}")
    public ResponseEntity<Integer> getTotalGolesEquipo(@PathVariable Integer equipoId) {
        return ResponseEntity.ok(partidoService.getTotalGolesByEquipo(equipoId));
    }

    // GET /api/partidos/resultados
    @GetMapping("/resultados")
    public ResponseEntity<List<ResultadoPartidoDTO>> getResultados() {
        return ResponseEntity.ok(partidoService.getResultadosConNombres());
    }
}