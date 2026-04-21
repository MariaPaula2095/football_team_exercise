package org.example.football_team_management.controller;

import org.example.football_team_management.dto.PartidoDto;
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

    // ✅ ENDPOINT FALTANTE - Resultados con nombres de equipos
    @GetMapping("/resultados")
    public ResponseEntity<List<PartidoDto>> resultadosPartidos() {
        return ResponseEntity.ok(partidoService.resultadosPartidos());
    }
}