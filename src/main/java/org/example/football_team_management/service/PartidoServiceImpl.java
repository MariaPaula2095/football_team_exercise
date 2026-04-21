package org.example.football_team_management.service;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.model.Equipo;
import org.example.football_team_management.model.Partido;
import org.example.football_team_management.repository.EquipoRepository;
import org.example.football_team_management.repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;

    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoRepository equipoRepository) {
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
    }

    // ================= CRUD =================

    @Override
    public List<PartidoDto> listar() {
        return partidoRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public PartidoDto guardar(PartidoDto dto) {
        if (dto == null) throw new IllegalArgumentException("El DTO no puede ser nulo");
        Partido partido = convertirAEntity(dto);
        Partido guardado = partidoRepository.save(partido);
        return convertirADto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        if (!partidoRepository.existsById(id)) {
            throw new RuntimeException("Partido con id " + id + " no encontrado");
        }
        partidoRepository.deleteById(id);
    }

    @Override
    public PartidoDto actualizar(Long id, PartidoDto dto) {
        if (dto == null) throw new IllegalArgumentException("El DTO no puede ser nulo");

        Partido existente = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));

        existente.setFecha(dto.getFecha());
        existente.setEstadio(dto.getEstadio());
        existente.setGolesLocal(dto.getGolesLocal());
        existente.setGolesVisita(dto.getGolesVisita());

        // ✅ Buscar entidades Equipo correctamente
        Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
        Equipo visita = equipoRepository.findById(dto.getIdEquipoVisita())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));

        existente.setEquipoLocal(local);
        existente.setEquipoVisita(visita);

        Partido actualizado = partidoRepository.save(existente);
        return convertirADto(actualizado);
    }

    // ================= CONSULTA NATIVA =================

    @Override
    public List<PartidoDto> resultadosPartidos() {
        List<Object[]> datos = partidoRepository.resultadosPartidos();

        return datos.stream().map(obj -> {
            PartidoDto dto = new PartidoDto();

            // ✅ Null check ANTES del cast
            dto.setFecha(obj[0] != null
                    ? ((java.sql.Date) obj[0]).toLocalDate()
                    : null);

            // ✅ Usar Number para evitar ClassCastException
            dto.setIdEquipoLocal(obj[1] != null ? ((Number) obj[1]).longValue() : null);
            dto.setIdEquipoVisita(obj[2] != null ? ((Number) obj[2]).longValue() : null);

            dto.setGolesLocal(obj[3] != null ? ((Number) obj[3]).intValue() : 0);
            dto.setGolesVisita(obj[4] != null ? ((Number) obj[4]).intValue() : 0);

            return dto;
        }).collect(Collectors.toList());
    }

    // ================= CONVERSIONES =================

    private PartidoDto convertirADto(Partido partido) {
        if (partido == null) return null;

        return new PartidoDto(
                partido.getIdPartido(),
                partido.getFecha(),
                partido.getEstadio(),
                // ✅ Null-safe para relaciones
                partido.getEquipoLocal() != null ? partido.getEquipoLocal().getIdEquipo() : null,
                partido.getEquipoVisita() != null ? partido.getEquipoVisita().getIdEquipo() : null,
                partido.getGolesLocal(),
                partido.getGolesVisita()
        );
    }

    private Partido convertirAEntity(PartidoDto dto) {
        Partido partido = new Partido();

        partido.setFecha(dto.getFecha());
        partido.setEstadio(dto.getEstadio());

        // ✅ Resolver relaciones desde repositorio
        Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
        Equipo visita = equipoRepository.findById(dto.getIdEquipoVisita())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));

        partido.setEquipoLocal(local);
        partido.setEquipoVisita(visita);

        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisita(dto.getGolesVisita());

        return partido;
    }
}