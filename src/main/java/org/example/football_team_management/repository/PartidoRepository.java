package org.example.football_team_management.repository;

import org.example.football_team_management.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido,Long> {
}
