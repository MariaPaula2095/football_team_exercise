package org.example.football_team_management.service;

import org.example.football_team_management.dto.EntrenadorDto;
import org.example.football_team_management.model.Entrenador;
import org.example.football_team_management.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    // Inyeccion por constructor
    public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public List<EntrenadorDto> listar() {
        return entrenadorRepository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public EntrenadorDto guardar(EntrenadorDto dto) {
        Entrenador entrenador = convertirAEntity(dto);
        Entrenador guardado = entrenadorRepository.save(entrenador);
        return convertirADto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        entrenadorRepository.deleteById(id);
    }

    @Override
    public EntrenadorDto actualizar(Long id, EntrenadorDto dto) {
        Entrenador existente = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        // Actualiza los campos
        existente.setNombre(dto.getNombre());
        existente.setEspecialidad(dto.getEspecialidad());
        existente.setEquipo(dto.getEquipo());

        Entrenador actualizado = entrenadorRepository.save(existente);
        return convertirADto(actualizado);
    }

    // Convierte entidad a DTO
    private EntrenadorDto convertirADto(Entrenador entrenador) {
        return new EntrenadorDto(
                entrenador.getIdEntrenador(),
                entrenador.getNombre(),
                entrenador.getEspecialidad(),
                entrenador.getEquipo()
        );
    }

    // Convierte DTO a entidad
    private Entrenador convertirAEntity(EntrenadorDto dto) {
        Entrenador entrenador = new Entrenador();
        entrenador.setNombre(dto.getNombre());
        entrenador.setEspecialidad(dto.getEspecialidad());
        entrenador.setEquipo(dto.getEquipo());
        return entrenador;
    }
}
