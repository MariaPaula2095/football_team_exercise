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

    // Convierte entidad a DTO
    private JugadorDto convertirADto(Jugador jugador) {
        return new JugadorDto(
                jugador.getNombre(),
                jugador.getPosicion(),
                jugador.getDorsal(),
                jugador.getFechaNac(),
                jugador.getNacionalidad(),
                jugador.getEquipo()
        );
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
}

