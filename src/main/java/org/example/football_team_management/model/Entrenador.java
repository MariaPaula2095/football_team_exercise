package org.example.football_team_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrenador")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrenador;

    private String nombre;
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}