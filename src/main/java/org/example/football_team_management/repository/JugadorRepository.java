
package org.example.football_team_management.repository;

import org.example.football_team_management.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    // 1. Jugadores por equipo
    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :idEquipo", nativeQuery = true)
    List<Jugador> jugadoresPorEquipo(@Param("idEquipo") int idEquipo);


    // 2. Total goles de un equipo
    @Query(value = """
    SELECT SUM(e.goles)
    FROM estadistica_jugador e
    JOIN jugador j ON e.id_jugador = j.id_jugador
    WHERE j.id_equipo = :idEquipo
""", nativeQuery = true)
    Integer totalGolesEquipo(@Param("idEquipo") int idEquipo);


    // 3. Jugadores con más de X goles
    @Query(value = """
    SELECT j.id_jugador, j.nombre, j.posicion, j.dorsal, SUM(e.goles) AS total_goles
    FROM estadistica_jugador e
    JOIN jugador j ON e.id_jugador = j.id_jugador
    GROUP BY j.id_jugador, j.nombre, j.posicion, j.dorsal
    HAVING SUM(e.goles) > :goles
""", nativeQuery = true)
    List<Object[]> jugadoresConMasDeXGoles(@Param("goles") int goles);
    }