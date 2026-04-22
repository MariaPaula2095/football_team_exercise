package org.example.football_team_management.repository;

import org.example.football_team_management.dto.ResultadoPartidoDTO;
import org.example.football_team_management.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

    // Consulta nativa #3: total de goles de un equipo
    @Query(value = """
        SELECT COALESCE(SUM(
            CASE 
                WHEN p.equipo_local = :equipoId THEN p.goles_local
                WHEN p.equipo_visita = :equipoId THEN p.goles_visita
            END
        ), 0)
        FROM partido p
        WHERE p.equipo_local = :equipoId OR p.equipo_visita = :equipoId
    """, nativeQuery = true)
    Integer findTotalGolesByEquipoId(@Param("equipoId") Long equipoId);

    // Consulta nativa #4: resultados con nombres de equipos
    @Query(value = "SELECT p.id_partido, p.fecha, p.estadio, " +
            "eq_local.nombre AS nombre_local, p.goles_local, " +
            "eq_visita.nombre AS nombre_visitante, p.goles_visita " +
            "FROM partido p " +
            "INNER JOIN equipo eq_local ON p.equipo_local = eq_local.id_equipo " +
            "INNER JOIN equipo eq_visita ON p.equipo_visita = eq_visita.id_equipo",
            nativeQuery = true)
    List<Object[]> findResultadosConNombresEquipos();

}