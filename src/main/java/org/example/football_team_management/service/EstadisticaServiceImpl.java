package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;
import org.example.football_team_management.model.EstadisticaJugador;
import org.example.football_team_management.repository.EstadisticaJugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    private final EstadisticaJugadorRepository estadisticaJugadorRepository;

    // Inyeccion por constructor
    public EstadisticaServiceImpl(EstadisticaJugadorRepository estadisticaJugadorRepository) {
        this.estadisticaJugadorRepository = estadisticaJugadorRepository;
    }

    @Override
    public List<EstadisticasJugadorDto> listar() {
        return estadisticaJugadorRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public EstadisticasJugadorDto guardar(EstadisticasJugadorDto dto) {
        EstadisticaJugador estadistica = convertirAEntity(dto);
        EstadisticaJugador guardada = estadisticaJugadorRepository.save(estadistica);
        return convertirADto(guardada);
    }

    @Override
    public void eliminar(Long id) {
        estadisticaJugadorRepository.deleteById(id);
    }

    @Override
    public EstadisticasJugadorDto actualizar(Long id, EstadisticasJugadorDto dto) {
        EstadisticaJugador existente = estadisticaJugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadistica no encontrada"));

        // Actualiza los campos
        existente.setJugador(dto.getJugador());
        existente.setPartido(dto.getPartido());
        existente.setMinutosJugados(dto.getMinutosJugados());
        existente.setGoles(dto.getGoles());
        existente.setAsistencias(dto.getAsistencias());
        existente.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        existente.setTarjetasRojas(dto.getTarjetasRojas());

        EstadisticaJugador actualizada = estadisticaJugadorRepository.save(existente);
        return convertirADto(actualizada);
    }

    // Convierte entidad a DTO
    private EstadisticasJugadorDto convertirADto(EstadisticaJugador estadistica) {
        return new EstadisticasJugadorDto(
                estadistica.getIdEstadistica(),
                estadistica.getJugador(),
                estadistica.getPartido(),
                estadistica.getMinutosJugados(),
                estadistica.getGoles(),
                estadistica.getAsistencias(),
                estadistica.getTarjetasAmarillas(),
                estadistica.getTarjetasRojas()
        );
    }

    // Convierte DTO a entidad
    private EstadisticaJugador convertirAEntity(EstadisticasJugadorDto dto) {
        EstadisticaJugador estadistica = new EstadisticaJugador();
        estadistica.setJugador(dto.getJugador());
        estadistica.setPartido(dto.getPartido());
        estadistica.setMinutosJugados(dto.getMinutosJugados());
        estadistica.setGoles(dto.getGoles());
        estadistica.setAsistencias(dto.getAsistencias());
        estadistica.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        estadistica.setTarjetasRojas(dto.getTarjetasRojas());
        return estadistica;
    }
}


