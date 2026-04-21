package org.example.football_team_management.service;

import org.example.football_team_management.dto.JugadorDto;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;

    // Inyeccion por constructor
    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public List<JugadorDto> listar() {
        return jugadorRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public JugadorDto guardar(JugadorDto dto) {
        Jugador jugador = convertirAEntity(dto);
        Jugador guardado = jugadorRepository.save(jugador);
        return convertirADto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }

    @Override
    public JugadorDto actualizar(Long id, JugadorDto dto) {
        Jugador existente = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        // Actualiza los campos
        existente.setNombre(dto.getNombre());
        existente.setPosicion(dto.getPosicion());
        existente.setDorsal(dto.getDorsal());
        existente.setFechaNac(dto.getFechaNac());
        existente.setNacionalidad(dto.getNacionalidad());
        existente.setEquipo(dto.getEquipo());

        Jugador actualizado = jugadorRepository.save(existente);
        return convertirADto(actualizado);
    }



    // Convierte DTO a entidad
    private Jugador convertirAEntity(JugadorDto dto) {
        Jugador jugador = new Jugador();
        jugador.setNombre(dto.getNombre());
        jugador.setPosicion(dto.getPosicion());
        jugador.setDorsal(dto.getDorsal());
        jugador.setFechaNac(dto.getFechaNac());
        jugador.setNacionalidad(dto.getNacionalidad());
        jugador.setEquipo(dto.getEquipo());
        return jugador;
    }
    private JugadorDto convertirADto(Jugador j) {
        JugadorDto dto = new JugadorDto();
        dto.setIdJugador(j.getIdJugador());
        dto.setNombre(j.getNombre());
        dto.setPosicion(j.getPosicion());
        dto.setDorsal(j.getDorsal());
        dto.setNacionalidad(j.getNacionalidad());
        dto.setEquipo(equipo.getNombre());
        return dto;
    }

    public List<JugadorDto> jugadoresPorEquipo(int idEquipo) {
        return jugadorRepository.jugadoresPorEquipo(idEquipo)
                .stream()
                .map(this::convertirADto)
                .toList();
    }

    public List<JugadorDto> jugadoresConMasDeXGoles(int goles) {
        return jugadorRepository.jugadoresConMasDeXGoles(goles)
                .stream()
                .map(this::convertirADto)
                .toList();
    }

    public Integer totalGolesEquipo(int idEquipo) {
        return jugadorRepository.totalGolesEquipo(idEquipo);
    }

}

