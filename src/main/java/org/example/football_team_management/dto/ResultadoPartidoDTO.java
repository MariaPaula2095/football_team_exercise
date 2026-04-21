package org.example.football_team_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoPartidoDTO {
    private Integer idPartido;
    private Date fecha;
    private String estadio;
    private String equipoLocal;
    private Integer golesLocal;
    private String equipoVisitante;
    private Integer golesVisitante;

}
