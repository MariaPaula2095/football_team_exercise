package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;
import org.example.football_team_management.model.EstadisticaJugador;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.model.Partido;
import org.example.football_team_management.repository.EstadisticaJugadorRepository;
import org.example.football_team_management.repository.JugadorRepository;
import org.example.football_team_management.repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    private final EstadisticaJugadorRepository estadisticaRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    public EstadisticaServiceImpl(EstadisticaJugadorRepository estadisticaRepository,
                                  JugadorRepository jugadorRepository,
                                  PartidoRepository partidoRepository) {
        this.estadisticaRepository = estadisticaRepository;
        this.jugadorRepository = jugadorRepository;
        this.partidoRepository = partidoRepository;
    }

    @Override
    public EstadisticasJugadorDto guardar(EstadisticasJugadorDto dto) {
        EstadisticaJugador estadistica = new EstadisticaJugador();

        Jugador jugador = jugadorRepository.findById(dto.getIdJugador())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        Partido partido = partidoRepository.findById(dto.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        estadistica.setJugador(jugador);
        estadistica.setPartido(partido);
        estadistica.setMinutosJugados(dto.getMinutosJugados());
        estadistica.setGoles(dto.getGoles());
        estadistica.setAsistencias(dto.getAsistencias());
        estadistica.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        estadistica.setTarjetasRojas(dto.getTarjetasRojas());

        return convertirADto(estadisticaRepository.save(estadistica));
    }

    @Override
    public List<EstadisticasJugadorDto> listar() {
        return estadisticaRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }


    @Override
    public Integer totalGolesEquipo(int idEquipo) {
        Integer total = estadisticaRepository.totalGolesEquipo(idEquipo);
        return total != null ? total : 0;
    }

    private EstadisticasJugadorDto convertirADto(EstadisticaJugador e) {
        EstadisticasJugadorDto dto = new EstadisticasJugadorDto();
        dto.setIdEstadistica(e.getIdEstadistica());
        dto.setIdJugador(e.getJugador() != null ? e.getJugador().getIdJugador() : null);
        dto.setNombreJugador(e.getJugador() != null ? e.getJugador().getNombre() : null);
        dto.setIdPartido(e.getPartido() != null ? e.getPartido().getIdPartido() : null);
        dto.setMinutosJugados(e.getMinutosJugados());
        dto.setGoles(e.getGoles());
        dto.setAsistencias(e.getAsistencias());
        dto.setTarjetasAmarillas(e.getTarjetasAmarillas());
        dto.setTarjetasRojas(e.getTarjetasRojas());
        return dto;
    }
}