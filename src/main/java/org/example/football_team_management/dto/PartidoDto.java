package org.example.football_team_management.dto;
import lombok.*;
import org.example.football_team_management.model.Equipo;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoDto {
    private Long idPartido;
    private LocalDate fecha;
    private String estadio;
    private Equipo equipoLocal;
    private Equipo equipoVisita;
    private Integer golesLocal;
    private Integer golesVisita;
}
