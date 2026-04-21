package org.example.football_team_management.service;

import org.example.football_team_management.dto.JugadorDto;
import org.example.football_team_management.model.Equipo;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorServiceImpl(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    // ================= CRUD =================

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

        existente.setNombre(dto.getNombre());
        existente.setPosicion(dto.getPosicion());
        existente.setDorsal(dto.getDorsal());
        existente.setFechaNac(dto.getFechaNac());
        existente.setNacionalidad(dto.getNacionalidad());
        if (dto.getIdEquipo() != null) {
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(dto.getIdEquipo());
            existente.setEquipo(equipo);
        }

        Jugador actualizado = jugadorRepository.save(existente);

        return convertirADto(actualizado);
    }


    // ================= CONSULTAS =================

    @Override
    public List<Jugador> getJugadoresByEquipo(Integer equipoId) {
        return jugadorRepository.findJugadoresByEquipoId(equipoId);
    }

    @Override
    public List<Jugador> getJugadoresWithGoalsGreaterThan(Integer goles) {
        return jugadorRepository.findJugadoresConGolesMayoresA(goles);
    }



    // ================= CONVERSIONES =================

    private JugadorDto convertirADto(Jugador j) {
        JugadorDto dto = new JugadorDto();

        dto.setIdJugador(j.getIdJugador());
        dto.setNombre(j.getNombre());
        dto.setPosicion(j.getPosicion());
        dto.setDorsal(j.getDorsal());
        dto.setFechaNac(j.getFechaNac());
        dto.setNacionalidad(j.getNacionalidad());

        if (j.getEquipo() != null) {
            dto.setIdEquipo(j.getEquipo().getIdEquipo());
        }

        return dto;
    }
    private Jugador convertirAEntity(JugadorDto dto) {
        Jugador jugador = new Jugador();

        jugador.setNombre(dto.getNombre());
        jugador.setPosicion(dto.getPosicion());
        jugador.setDorsal(dto.getDorsal());
        jugador.setFechaNac(dto.getFechaNac());
        jugador.setNacionalidad(dto.getNacionalidad());

        if (dto.getIdEquipo() != null) {
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(dto.getIdEquipo());
            jugador.setEquipo(equipo);
        }

        return jugador;
    }
}