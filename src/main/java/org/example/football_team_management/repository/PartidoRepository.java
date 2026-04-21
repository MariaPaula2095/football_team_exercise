package org.example.football_team_management.repository;

import org.example.football_team_management.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido,Long> {
    // Número total de goles marcados por un equipo en todos sus partidos
    @Query(value = "SELECT COALESCE(SUM(goles_local), 0) + COALESCE(SUM(goles_visita), 0) " +
            "FROM partido WHERE equipo_local = :equipoId OR equipo_visita = :equipoId",
            nativeQuery = true)
    Integer findTotalGolesByEquipoId(@Param("equipoId") Integer equipoId);

    // Resultados de todos los partidos con nombres de equipos
    @Query(value = "SELECT p.id_partido, p.fecha, p.estadio, " +
            "eq_local.nombre AS nombre_local, p.goles_local, " +
            "eq_visita.nombre AS nombre_visita, p.goles_visita " +
            "FROM partido p " +
            "JOIN equipo eq_local ON p.equipo_local = eq_local.id_equipo " +
            "JOIN equipo eq_visita ON p.equipo_visita = eq_visita.id_equipo",
            nativeQuery = true)
    List<Object[]> findResultadosConNombresEquipos();
}