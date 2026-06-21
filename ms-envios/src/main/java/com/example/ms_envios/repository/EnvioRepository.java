package com.example.ms_envios.repository;

import com.example.ms_envios.model.Envio;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio Spring Data para consultar y guardar envíos.
 */
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    @Query("SELECT e FROM Envio e WHERE e.fechaEnvio BETWEEN :desde AND :hasta AND e.entregado = false")
    List<Envio> buscarNoEntregadosPorRango(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}
