package org.example.football_team_management.service;

import org.example.football_team_management.dto.PartidoDto;
import org.example.football_team_management.model.Partido;
import org.example.football_team_management.repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;

    // Inyeccion por constructor
    public PartidoServiceImpl(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

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
    public void eliminar(Long id) {
        partidoRepository.deleteById(id);
    }

    @Override
    public PartidoDto actualizar(Long id, PartidoDto dto) {
        Partido existente = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        // Actualiza los campos
        existente.setFecha(dto.getFecha());
        existente.setEstadio(dto.getEstadio());
        existente.setEquipoLocal(dto.getEquipoLocal());
        existente.setEquipoVisita(dto.getEquipoVisita());
        existente.setGolesLocal(dto.getGolesLocal());
        existente.setGolesVisita(dto.getGolesVisita());

        Partido actualizado = partidoRepository.save(existente);
        return convertirADto(actualizado);
    }

    // Convierte entidad a DTO
    private PartidoDto convertirADto(Partido partido) {
        return new PartidoDto(
                partido.getIdPartido(),
                partido.getFecha(),
                partido.getEstadio(),
                partido.getEquipoLocal(),
                partido.getEquipoVisita(),
                partido.getGolesLocal(),
                partido.getGolesVisita()
        );
    }

    // Convierte DTO a entidad
    private Partido convertirAEntity(PartidoDto dto) {
        Partido partido = new Partido();
        partido.setFecha(dto.getFecha());
        partido.setEstadio(dto.getEstadio());
        partido.setEquipoLocal(dto.getEquipoLocal());
        partido.setEquipoVisita(dto.getEquipoVisita());
        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisita(dto.getGolesVisita());
        return partido;
    }
}

