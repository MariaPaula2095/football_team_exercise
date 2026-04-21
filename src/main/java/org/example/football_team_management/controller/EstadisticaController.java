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

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasJugadorDto>> listar() {
        return ResponseEntity.ok(estadisticaService.listar());
    }

    // Asignar goles a un jugador en un partido
    @PostMapping("/guardar")
    public ResponseEntity<EstadisticasJugadorDto> guardar(@RequestBody EstadisticasJugadorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadisticaService.guardar(dto));
    }

    // Jugadores con más de X goles
    @GetMapping("/goles")
    public ResponseEntity<List<EstadisticasJugadorDto>> jugadoresConMasDeXGoles(@RequestParam int goles) {
        return ResponseEntity.ok(estadisticaService.jugadoresConMasDeXGoles(goles));
    }

    // Total goles de un equipo
    @GetMapping("/total-goles/{idEquipo}")
    public ResponseEntity<Integer> totalGolesEquipo(@PathVariable int idEquipo) {
        return ResponseEntity.ok(estadisticaService.totalGolesEquipo(idEquipo));
    }
}