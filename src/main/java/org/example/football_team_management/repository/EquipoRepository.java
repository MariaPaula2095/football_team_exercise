package org.example.football_team_management.repository;

import org.example.football_team_management.model.Entrenador;
import org.example.football_team_management.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EquipoRepository extends JpaRepository<Equipo,Long> {
}
