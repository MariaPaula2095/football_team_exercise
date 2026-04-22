package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;

import java.util.List;

public interface EstadisticaService {

    List<EstadisticasJugadorDto> listar();

    // Guarda un nuevo equipo
    EstadisticasJugadorDto guardar(EstadisticasJugadorDto estadisticasJugadorDto);

    // Elimina un equipo por id
    void eliminar(Long id);

    // Actualiza un equipo existente
    EstadisticasJugadorDto actualizar(Long id, EstadisticasJugadorDto estadisticasJugadorDto);
}


