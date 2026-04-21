package org.example.football_team_management.repository;

import org.example.football_team_management.model.EstadisticaJugador;
import org.example.football_team_management.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstadisticaJugadorRepository extends JpaRepository  <EstadisticaJugador,Long>{

    @Query(value = """
SELECT j.*
FROM jugador j
JOIN estadisticas_jugador e ON j.id_jugador = e.id_jugador
GROUP BY j.id_jugador
HAVING SUM(e.goles) > :goles
""", nativeQuery = true)
    List<Jugador> jugadoresConMasDeXGoles(@Param("goles") int goles);
}
