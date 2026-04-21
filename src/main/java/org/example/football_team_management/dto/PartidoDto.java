package org.example.football_team_management.dto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private Long idEquipoLocal;
    private Long idEquipoVisita;

    private Integer golesLocal;
    private Integer golesVisita;
}
