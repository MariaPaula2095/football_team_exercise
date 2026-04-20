package org.example.football_team_management.service;

import org.example.football_team_management.dto.EquipoDto;

import java.util.List;

public interface EquipoService {

    // Lista todos los equipos
    List<EquipoDto> listar();

    // Guarda un nuevo equipo
    EquipoDto guardar(EquipoDto equipo);

    // Elimina un equipo por id
    void eliminar(Long id);

    // Actualiza un equipo existente
    EquipoDto actualizar(Long id, EquipoDto equipo);
}