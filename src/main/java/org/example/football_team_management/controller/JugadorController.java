package org.example.football_team_management.controller;

import org.example.football_team_management.dto.JugadorDto;
import org.example.football_team_management.service.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores") // Ruta base de jugadores
public class JugadorController {

    private final JugadorService jugadorService;

    // Inyeccion por constructor
    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    // LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<JugadorDto>> listar() {
        List<JugadorDto> jugadores = jugadorService.listar();
        return ResponseEntity.ok(jugadores); // 200 OK
    }

    // GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<JugadorDto> guardar(@RequestBody JugadorDto dto) {
        JugadorDto guardado = jugadorService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 CREATED
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        jugadorService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<JugadorDto> actualizar(@PathVariable Long id, @RequestBody JugadorDto dto) {
        JugadorDto actualizado = jugadorService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado); // 200 OK
    }
}