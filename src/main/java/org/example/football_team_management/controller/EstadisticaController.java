package org.example.football_team_management.controller;

import org.example.football_team_management.dto.EstadisticasJugadorDto;
import org.example.football_team_management.service.EstadisticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadisticas") // Ruta base
public class EstadisticaController {

    private final EstadisticaService estadisticaService;

    // Inyeccion por constructor
    public EstadisticaController(EstadisticaService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    // LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasJugadorDto>> listar() {
        List<EstadisticasJugadorDto> lista = estadisticaService.listar();
        return ResponseEntity.ok(lista); // 200 OK
    }

    // GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<EstadisticasJugadorDto> guardar(@RequestBody EstadisticasJugadorDto dto) {
        EstadisticasJugadorDto guardado = estadisticaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 CREATED
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadisticaService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EstadisticasJugadorDto> actualizar(@PathVariable Long id,
                                                             @RequestBody EstadisticasJugadorDto dto) {
        EstadisticasJugadorDto actualizado = estadisticaService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado); // 200 OK
    }
}