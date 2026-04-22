package org.example.football_team_management.service;

import org.example.football_team_management.dto.EstadisticasJugadorDto;
import org.example.football_team_management.model.EstadisticaJugador;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.model.Partido;
import org.example.football_team_management.repository.EstadisticaJugadorRepository;
import org.example.football_team_management.repository.JugadorRepository;
import org.example.football_team_management.repository.PartidoRepository;
import org.example.football_team_management.service.EstadisticaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadisticaJugadorServiceImpl implements EstadisticaService {

    private final EstadisticaJugadorRepository estadisticaRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    // Inyección por constructor
    public EstadisticaJugadorServiceImpl(EstadisticaJugadorRepository estadisticaRepository,
                                         JugadorRepository jugadorRepository,
                                         PartidoRepository partidoRepository) {
        this.estadisticaRepository = estadisticaRepository;
        this.jugadorRepository = jugadorRepository;
        this.partidoRepository = partidoRepository;
    }

    // ========== CRUD ==========
    @Override
    public List<EstadisticasJugadorDto> listar() {
        return estadisticaRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }



    @Override
    public EstadisticasJugadorDto guardar(EstadisticasJugadorDto dto) {
        EstadisticaJugador entity = convertirAEntity(dto);
        EstadisticaJugador guardado = estadisticaRepository.save(entity);
        return convertirADto(guardado);
    }

    @Override
    public EstadisticasJugadorDto actualizar(Long id, EstadisticasJugadorDto dto) {
        if (!estadisticaRepository.existsById(id)) {
            throw new RuntimeException("Estadística no encontrada para actualizar");
        }
        dto.setIdEstadistica(id);
        return guardar(dto);
    }

    @Override
    public void eliminar(Long id) {
        if (!estadisticaRepository.existsById(id)) {
            throw new RuntimeException("Estadística no encontrada para eliminar");
        }
        estadisticaRepository.deleteById(id);
    }
    private EstadisticasJugadorDto convertirADto(EstadisticaJugador entity) {
        String nombreJugador = entity.getJugador() != null ? entity.getJugador().getNombre() : null;
        Long idJugador = entity.getJugador() != null ? entity.getJugador().getIdJugador() : null;
        Long idPartido = entity.getPartido() != null ? entity.getPartido().getIdPartido() : null;

        return new EstadisticasJugadorDto(
                entity.getIdEstadistica(),
                idJugador,
                nombreJugador,
                idPartido,
                entity.getMinutosJugados(),
                entity.getGoles(),
                entity.getAsistencias(),
                entity.getTarjetasAmarillas(),
                entity.getTarjetasRojas()
        );
    }

    private EstadisticaJugador convertirAEntity(EstadisticasJugadorDto dto) {
        EstadisticaJugador entity = new EstadisticaJugador();
        entity.setIdEstadistica(dto.getIdEstadistica());
        entity.setMinutosJugados(dto.getMinutosJugados());
        entity.setGoles(dto.getGoles());
        entity.setAsistencias(dto.getAsistencias());
        entity.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        entity.setTarjetasRojas(dto.getTarjetasRojas());

        // Cargar Jugador
        if (dto.getIdJugador() != null) {
            Jugador jugador = jugadorRepository.findById(dto.getIdJugador())
                    .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + dto.getIdJugador()));
            entity.setJugador(jugador);
        }
        // Cargar Partido
        if (dto.getIdPartido() != null) {
            Partido partido = partidoRepository.findById(dto.getIdPartido())
                    .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + dto.getIdPartido()));
            entity.setPartido(partido);
        }
        return entity;
    }
}
