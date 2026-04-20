package org.example.football_team_management.service;

import org.example.football_team_management.dto.EquipoDto;
import org.example.football_team_management.model.Equipo;
import org.example.football_team_management.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    // Inyeccion por constructor
    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public List<EquipoDto> listar() {
        return equipoRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDto guardar(EquipoDto dto) {
        Equipo equipo = convertirAEntity(dto);
        Equipo guardado = equipoRepository.save(equipo);
        return convertirADto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }

    @Override
    public EquipoDto actualizar(Long id, EquipoDto dto) {
        Equipo existente = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        // Actualiza los campos
        existente.setNombre(dto.getNombre());
        existente.setCiudad(dto.getCiudad());
        existente.setFundacion(dto.getFundacion());

        Equipo actualizado = equipoRepository.save(existente);
        return convertirADto(actualizado);
    }

    // Convierte entidad a DTO
    private EquipoDto convertirADto(Equipo equipo) {
        return new EquipoDto(
                equipo.getIdEquipo(),
                equipo.getNombre(),
                equipo.getCiudad(),
                equipo.getFundacion()
        );
    }

    // Convierte DTO a entidad
    private Equipo convertirAEntity(EquipoDto dto) {
        Equipo equipo = new Equipo();
        equipo.setNombre(dto.getNombre());
        equipo.setCiudad(dto.getCiudad());
        equipo.setFundacion(dto.getFundacion());
        return equipo;
    }
}
