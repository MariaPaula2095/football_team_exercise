package org.example.football_team_management.dto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.football_team_management.model.Equipo;

import java.time.LocalDate;

/**
 * DTO de entrada: define exactamente qué campos acepta la API.
 * Las validaciones se activan con @Valid en el controller.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder

public class JugadorDto {

    private Long idJugador;
    private String nombre;
    private String posicion;
    private Integer dorsal;
    private LocalDate fechaNac;
    private String nacionalidad;


    private Long idEquipo;
}
