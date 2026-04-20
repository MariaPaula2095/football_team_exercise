package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;

import java.util.List;

public interface EstadisticaService {
    // Lista todas las estadisticas
    List<EstadisticasJugadorDto> listar();

    // Guarda una nueva estadistica
    EstadisticasJugadorDto guardar(EstadisticasJugadorDto estadistica);

    // Elimina una estadistica por id
    void eliminar(Long id);

    // Actualiza una estadistica existente
    EstadisticasJugadorDto actualizar(Long id, EstadisticasJugadorDto estadistica);
}


