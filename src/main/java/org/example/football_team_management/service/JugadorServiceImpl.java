package org.example.football_team_management.service;

import org.example.football_team_management.dto.JugadorDto;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.model.Equipo;
import org.example.football_team_management.repository.JugadorRepository;
import org.example.football_team_management.repository.EquipoRepository;
import org.example.football_team_management.service.JugadorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;

    public JugadorServiceImpl(JugadorRepository jugadorRepository, EquipoRepository equipoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
    }

    private JugadorDto convertirADto(Jugador j) {
        return new JugadorDto(
                j.getIdJugador(), j.getNombre(), j.getPosicion(), j.getDorsal(),
                j.getFechaNac(), j.getNacionalidad(),
                j.getEquipo() != null ? j.getEquipo().getIdEquipo() : null
        );
    }

    private Jugador convertirAEntity(JugadorDto dto) {
        Jugador j = new Jugador();
        j.setIdJugador(dto.getIdJugador());
        j.setNombre(dto.getNombre());
        j.setPosicion(dto.getPosicion());
        j.setDorsal(dto.getDorsal());
        j.setFechaNac(dto.getFechaNac());
        j.setNacionalidad(dto.getNacionalidad());
        if (dto.getIdEquipo() != null) {
            Equipo e = equipoRepository.findById(dto.getIdEquipo()).orElseThrow();
            j.setEquipo(e);
        }
        return j;
    }

    @Override
    public List<JugadorDto> listar() {
        return jugadorRepository.findAll().stream().map(this::convertirADto).collect(Collectors.toList());
    }


    @Override
    public JugadorDto guardar(JugadorDto dto) {
        return convertirADto(jugadorRepository.save(convertirAEntity(dto)));
    }

    @Override
    public JugadorDto actualizar(Long id, JugadorDto dto) {
        if (!jugadorRepository.existsById(id)) throw new RuntimeException("No existe");
        dto.setIdJugador(id);
        return guardar(dto);
    }

    @Override
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }

    // ---------- Consultas nativas ----------
    @Override
    public List<JugadorDto> obtenerJugadoresPorEquipo(Long equipoId) {
        return jugadorRepository.findJugadoresByEquipoId(equipoId)
                .stream().map(this::convertirADto).collect(Collectors.toList());
    }

    @Override
    public List<JugadorDto> obtenerJugadoresConMasDeXGoles(Integer golesMinimos) {
        return jugadorRepository.findJugadoresConGolesMayoresA(golesMinimos)
                .stream().map(this::convertirADto).collect(Collectors.toList());
    }
}