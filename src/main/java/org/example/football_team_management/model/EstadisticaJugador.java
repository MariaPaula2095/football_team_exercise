package org.example.football_team_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estadistica_jugador")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstadisticaJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadistica;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_partido")
    private Partido partido;

    private Integer minutosJugados;
    private Integer goles;
    private Integer asistencias;
    private Integer tarjetasAmarillas;
    private Integer tarjetasRojas;
}