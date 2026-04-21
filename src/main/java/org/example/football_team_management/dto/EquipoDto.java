package org.example.football_team_management.dto;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO de salida: define exactamente que devuelve la API. q va a ver el usuario
 * Aqui se puede exponer el id y omitir campos sensibles si los hubiera
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EquipoDto {
    private Long idEquipo;
    private String nombre;
    private String ciudad;
    private LocalDate fundacion;

}
