package org.example.football_team_management.service;

import org.example.football_team_management.dto.JugadorDto;

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
}