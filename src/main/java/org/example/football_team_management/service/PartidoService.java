package org.example.football_team_management.service;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.dto.ResultadoPartidoDTO;
import java.util.List;
import java.util.Map;

public interface PartidoService {
    List<PartidoDto> listar();
    PartidoDto guardar(PartidoDto dto);
    PartidoDto actualizar(Long id, PartidoDto dto);
    void eliminar(Long id);

    // Consultas nativas
    Integer obtenerTotalGolesEquipo(Long equipoId);
    List<ResultadoPartidoDTO> obtenerResultadosConNombres();
}