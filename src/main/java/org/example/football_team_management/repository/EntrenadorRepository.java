package org.example.football_team_management.repository;

import org.example.football_team_management.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository extends JpaRepository<Entrenador,Long> {
}
