package org.example.football_team_management.controller;

import org.example.football_team_management.dto.EntrenadorDto;
import org.example.football_team_management.service.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores") // Ruta base de entrenadores
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    // Inyeccion por constructor
    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    // LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<EntrenadorDto>> listar() {
        List<EntrenadorDto> entrenadores = entrenadorService.listar();
        return ResponseEntity.ok(entrenadores); // 200 OK
    }

    // GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<EntrenadorDto> guardar(@RequestBody EntrenadorDto dto) {
        EntrenadorDto guardado = entrenadorService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 CREATED
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entrenadorService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EntrenadorDto> actualizar(@PathVariable Long id, @RequestBody EntrenadorDto dto) {
        EntrenadorDto actualizado = entrenadorService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado); // 200 OK
    }
}