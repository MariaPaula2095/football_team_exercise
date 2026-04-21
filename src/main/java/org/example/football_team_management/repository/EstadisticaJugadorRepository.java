package org.example.football_team_management.repository;

import org.example.football_team_management.model.EstadisticaJugador;
import org.example.football_team_management.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadisticaJugadorRepository extends JpaRepository<EstadisticaJugador, Long> {

    // Jugadores con más de X goles
    @Query(value = """
        SELECT j.id_jugador, j.nombre, j.posicion, j.dorsal, SUM(e.goles) AS total_goles
        FROM estadistica_jugador e
        JOIN jugador j ON j.id_jugador = e.id_jugador
        GROUP BY j.id_jugador, j.nombre, j.posicion, j.dorsal
        HAVING SUM(e.goles) > :goles
    """, nativeQuery = true)
    List<Object[]> jugadoresConMasDeXGoles(@Param("goles") int goles);

    // Total goles de un equipo en todos sus partidos
    @Query(value = """
        SELECT SUM(e.goles)
        FROM estadistica_jugador e
        JOIN jugador j ON j.id_jugador = e.id_jugador
        WHERE j.id_equipo = :idEquipo
    """, nativeQuery = true)
    Integer totalGolesEquipo(@Param("idEquipo") int idEquipo);
}