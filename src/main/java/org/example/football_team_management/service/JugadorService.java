package org.example.football_team_management.service;

import org.example.football_team_management.dto.JugadorDto;
import org.example.football_team_management.model.Jugador;

import java.util.List;

public interface JugadorService {

    // Lista todos los jugadores
    List<JugadorDto> listar();

    // Guarda un nuevo jugador
    JugadorDto guardar(JugadorDto jugador);

    // Elimina un jugador por id
    void eliminar(Long id);

    // Actualiza un jugador existente
    JugadorDto actualizar(Long id, JugadorDto jugador);

    //----------------------------------
    // 1. Jugadores por equipo
    List<JugadorDto> jugadoresPorEquipo(int idEquipo);

    // 2. Jugadores con más de X goles
    List<JugadorDto> jugadoresConMasDeXGoles(int goles);

    // 3. Total goles de un equipo
    Integer totalGolesEquipo(int idEquipo);
}