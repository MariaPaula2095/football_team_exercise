package org.example.football_team_management.controller;

import org.example.football_team_management.dto.EquipoDto;
import org.example.football_team_management.service.EquipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos") // Ruta base de equipos
public class EquipoController {

    private final EquipoService equipoService;

    // Inyeccion por constructor
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<EquipoDto>> listar() {
        List<EquipoDto> equipos = equipoService.listar();
        return ResponseEntity.ok(equipos); // 200 OK
    }

    // GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<EquipoDto> guardar(@RequestBody EquipoDto dto) {
        EquipoDto guardado = equipoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 CREATED
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EquipoDto> actualizar(@PathVariable Long id, @RequestBody EquipoDto dto) {
        EquipoDto actualizado = equipoService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado); // 200 OK
    }
}