package org.example.football_team_management.dto;
import lombok.*;
import org.example.football_team_management.model.Jugador;
import org.example.football_team_management.model.Partido;

/**
 * DTO de salida: define exactamente que devuelve la API. q va a ver el usuario
 * Aqui se puede exponer el id y omitir campos sensibles si los hubiera
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EstadisticasJugadorDto {
    private Long idEstadistica;
    private Long idJugador;
    private String nombreJugador;
    private Long idPartido;
    private Integer minutosJugados;
    private Integer goles;
    private Integer asistencias;
    private Integer tarjetasAmarillas;
    private Integer tarjetasRojas;
}
