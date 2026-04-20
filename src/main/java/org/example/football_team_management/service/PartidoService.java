package org.example.football_team_management.service;

import org.example.football_team_management.dto.PartidoDto;

import java.util.List;

public interface PartidoService {

    // Lista todos los partidos
    List<PartidoDto> listar();

    // Guarda un nuevo partido
    PartidoDto guardar(PartidoDto partido);

    // Elimina un partido por id
    void eliminar(Long id);

    // Actualiza un partido existente
    PartidoDto actualizar(Long id, PartidoDto partido);
}