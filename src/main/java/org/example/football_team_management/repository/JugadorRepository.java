package org.example.football_team_management.repository;

import org.example.football_team_management.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador,Long> {
}
