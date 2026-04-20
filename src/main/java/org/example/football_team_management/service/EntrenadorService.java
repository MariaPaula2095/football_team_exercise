package org.example.football_team_management.service;

import org.example.football_team_management.dto.EntrenadorDto;

import java.util.List;

public interface EntrenadorService {

    // Lista todos los entrenadores
    List<EntrenadorDto> listar();

    // Guarda un nuevo entrenador
    EntrenadorDto guardar(EntrenadorDto entrenador);

    // Elimina un entrenador por id
    void eliminar(Long id);

    // Actualiza un entrenador existente
    EntrenadorDto actualizar(Long id, EntrenadorDto entrenador);
}