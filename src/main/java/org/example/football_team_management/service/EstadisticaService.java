package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;

import java.util.List;

public interface EstadisticaService {
    EstadisticasJugadorDto guardar(EstadisticasJugadorDto dto);
    List<EstadisticasJugadorDto> listar();
    Integer totalGolesEquipo(int idEquipo);
}


