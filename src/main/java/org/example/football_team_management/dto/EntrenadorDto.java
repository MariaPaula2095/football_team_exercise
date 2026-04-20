package org.example.football_team_management.dto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.football_team_management.model.Equipo;

/**
 * DTO de salida: define exactamente que devuelve la API. q va a ver el usuario
 * Aqui se puede exponer el id y omitir campos sensibles si los hubiera
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EntrenadorDto {
    private Long idEntrenador;
    private String nombre;
    private String especialidad;
    private Equipo equipo;
}
