package org.example.football_team_management.controller;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.service.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos") // Ruta base de partidos
public class PartidoController {

    private final PartidoService partidoService;

    // Inyeccion por constructor
    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    // LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<PartidoDto>> listar() {
        List<PartidoDto> partidos = partidoService.listar();
        return ResponseEntity.ok(partidos); // 200 OK
    }

    // GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<PartidoDto> guardar(@RequestBody PartidoDto dto) {
        PartidoDto guardado = partidoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 CREATED
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        partidoService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PartidoDto> actualizar(@PathVariable Long id, @RequestBody PartidoDto dto) {
        PartidoDto actualizado = partidoService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado); // 200 OK
    }
}