package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;

import java.util.List;

public interface EstadisticaService {
    EstadisticasJugadorDto guardar(EstadisticasJugadorDto dto);
    List<EstadisticasJugadorDto> listar();
    List<EstadisticasJugadorDto> jugadoresConMasDeXGoles(int goles);
    Integer totalGolesEquipo(int idEquipo);
}


