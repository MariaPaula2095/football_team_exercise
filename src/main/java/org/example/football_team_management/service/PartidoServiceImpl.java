package org.example.football_team_management.service;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.dto.ResultadoPartidoDTO;
import org.example.football_team_management.model.Partido;
import org.example.football_team_management.model.Equipo;
import org.example.football_team_management.repository.PartidoRepository;
import org.example.football_team_management.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;

    // Inyección por constructor
    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoRepository equipoRepository) {
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
    }

    // ========== CRUD ==========
    @Override
    public List<PartidoDto> listar() {
        return partidoRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }


    @Override
    public PartidoDto guardar(PartidoDto dto) {
        Partido partido = convertirAEntity(dto);
        Partido guardado = partidoRepository.save(partido);
        return convertirADto(guardado);
    }

    @Override
    public PartidoDto actualizar(Long id, PartidoDto dto) {
        Partido existente = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado para actualizar"));

        existente.setFecha(dto.getFecha());
        existente.setEstadio(dto.getEstadio());
        existente.setGolesLocal(dto.getGolesLocal());
        existente.setGolesVisita(dto.getGolesVisita());

        if (dto.getIdEquipoLocal() != null) {
            Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                    .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
            existente.setEquipoLocal(local);
        } else {
            existente.setEquipoLocal(null);
        }

        if (dto.getIdEquipoVisita() != null) {
            Equipo visitante = equipoRepository.findById(dto.getIdEquipoVisita())
                    .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));
            existente.setEquipoVisita(visitante);
        } else {
            existente.setEquipoVisita(null);
        }

        Partido actualizado = partidoRepository.save(existente);
        return convertirADto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        if (!partidoRepository.existsById(id)) {
            throw new RuntimeException("Partido no encontrado para eliminar");
        }
        partidoRepository.deleteById(id);
    }

    // ========== Consultas nativas ==========
    @Override
    public Integer obtenerTotalGolesEquipo(Long equipoId) {
        return partidoRepository.findTotalGolesByEquipoId(equipoId);
    }


    @Override
    public List<ResultadoPartidoDTO> obtenerResultadosConNombres() {
        List<Object[]> resultados = partidoRepository.findResultadosConNombresEquipos();
        return resultados.stream()
                .map(row -> new ResultadoPartidoDTO(
                        ((Number) row[0]).longValue(),   // idPartido
                        (LocalDate) row[1],              // fecha
                        (String) row[2],                 // estadio
                        (String) row[3],                 // equipoLocal
                        (Integer) row[4],                // golesLocal
                        (String) row[5],                 // equipoVisitante
                        (Integer) row[6]                 // golesVisitante
                ))
                .collect(Collectors.toList());
    }

    // ========== Métodos privados de conversión ==========
    private PartidoDto convertirADto(Partido partido) {
        Long idLocal = partido.getEquipoLocal() != null ? partido.getEquipoLocal().getIdEquipo() : null;
        Long idVisitante = partido.getEquipoVisita() != null ? partido.getEquipoVisita().getIdEquipo() : null;

        return new PartidoDto(
                partido.getIdPartido(),
                partido.getFecha(),
                partido.getEstadio(),
                idLocal,
                idVisitante,
                partido.getGolesLocal(),
                partido.getGolesVisita()
        );
    }

    private Partido convertirAEntity(PartidoDto dto) {
        Partido partido = new Partido();
        partido.setIdPartido(dto.getIdPartido());
        partido.setFecha(dto.getFecha());
        partido.setEstadio(dto.getEstadio());
        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisita(dto.getGolesVisita());

        if (dto.getIdEquipoLocal() != null) {
            Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                    .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
            partido.setEquipoLocal(local);
        }
        if (dto.getIdEquipoVisita() != null) {
            Equipo visitante = equipoRepository.findById(dto.getIdEquipoVisita())
                    .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));
            partido.setEquipoVisita(visitante);
        }
        return partido;
    }
}