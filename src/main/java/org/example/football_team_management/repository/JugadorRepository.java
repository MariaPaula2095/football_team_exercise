package org.example.football_team_management.repository;

import org.example.football_team_management.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    // Consulta nativa 1: jugadores de un equipo específico
    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :equipoId", nativeQuery = true)
    List<Jugador> findJugadoresByEquipoId(@Param("equipoId") Long equipoId);

    // Consulta nativa 2: jugadores con más de X goles (sumando desde estadisticas_jugador)
    @Query(value = "SELECT j.* FROM jugador j " +
            "INNER JOIN estadistica_jugador ej ON j.id_jugador = ej.id_jugador " +
            "GROUP BY j.id_jugador " +
            "HAVING SUM(ej.goles) > :golesMinimos", nativeQuery = true)
    List<Jugador> findJugadoresConGolesMayoresA(@Param("golesMinimos") Integer golesMinimos);
}