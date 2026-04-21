package org.example.football_team_management.controller;

import org.example.football_team_management.dto.JugadorDto;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.service.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }
    // ================= CRUD =================

    // LISTAR TODOS
    @GetMapping ("/listar")
    public ResponseEntity<List<JugadorDto>> listar() {
        return ResponseEntity.ok(jugadorService.listar());
    }

    // GUARDAR
    @PostMapping ("/guardar")
    public ResponseEntity<JugadorDto> guardar(@RequestBody JugadorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jugadorService.guardar(dto));
    }

    // ELIMINAR ("/guardar")
    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        jugadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ACTUALIZAR
    @PutMapping ("/actualizar/{id}")
    public ResponseEntity<JugadorDto> actualizar(@PathVariable Long id,
                                                 @RequestBody JugadorDto dto) {
        return ResponseEntity.ok(jugadorService.actualizar(id, dto));
    }

    // CONSULTAS NATIVAS

    // GET /api/jugadores/equipo/{equipoId}
    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Jugador>> getJugadoresByEquipo(@PathVariable Integer equipoId) {
        return ResponseEntity.ok(jugadorService.getJugadoresByEquipo(equipoId));
    }

    // GET /api/jugadores/goles-mayores?minGoles=5
    @GetMapping("/goles-mayores")
    public ResponseEntity<List<Jugador>> getJugadoresConGolesMayores(@RequestParam Integer minGoles) {
        return ResponseEntity.ok(jugadorService.getJugadoresWithGoalsGreaterThan(minGoles));
    }
}