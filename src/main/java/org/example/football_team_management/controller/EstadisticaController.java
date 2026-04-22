package org.example.football_team_management.controller;

import org.example.football_team_management.dto.EstadisticasJugadorDto;
import org.example.football_team_management.service.EstadisticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaController {

    private final EstadisticaService estadisticaService;

    public EstadisticaController(EstadisticaService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    // CRUD
    @GetMapping ("/Listar/partido-jugador")
    public ResponseEntity<List<EstadisticasJugadorDto>> listar() {
        return ResponseEntity.ok(estadisticaService.listar());
    }


    @PostMapping ("/Guardar/partido-jugador")
    public ResponseEntity<EstadisticasJugadorDto> crear(@RequestBody EstadisticasJugadorDto dto) {
        return new ResponseEntity<>(estadisticaService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/Actualizar/partido-jugador/{id}")
    public ResponseEntity<EstadisticasJugadorDto> actualizar(@PathVariable Long id, @RequestBody EstadisticasJugadorDto dto) {
        return ResponseEntity.ok(estadisticaService.actualizar(id, dto));
    }

    @DeleteMapping("(/Eliminar/partido-jugador/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadisticaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
