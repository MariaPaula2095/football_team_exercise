package org.example.football_team_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoPartidoDTO {
    private Long idPartido;
    private LocalDate fecha;
    private String estadio;
    private String equipoLocal;
    private Integer golesLocal;
    private String equipoVisitante;
    private Integer golesVisitante;
}