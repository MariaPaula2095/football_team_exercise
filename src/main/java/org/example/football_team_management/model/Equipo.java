package org.example.football_team_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "equipo")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;

    private String nombre;
    private String ciudad;
    private LocalDate fundacion;
}