package org.example.football_team_management.service;

import org.example.football_team_management.dto.JugadorDto;
import java.util.List;

public interface JugadorService {
    // CRUD básico (opcional, pero útil)
    List<JugadorDto> listar();
    JugadorDto guardar(JugadorDto jugadorDto);
    JugadorDto actualizar(Long id, JugadorDto jugadorDto);
    void eliminar(Long id);

    // Consultas nativas requeridas
    List<JugadorDto> obtenerJugadoresPorEquipo(Long equipoId);
    List<JugadorDto> obtenerJugadoresConMasDeXGoles(Integer golesMinimos);
}