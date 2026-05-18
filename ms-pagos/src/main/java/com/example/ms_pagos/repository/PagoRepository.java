package com.example.ms_pagos.repository;

import com.example.ms_pagos.model.Pago;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    @Query("SELECT p FROM Pago p WHERE p.monto > :montoMinimo AND p.estadoPago = :estadoPago")
    List<Pago> buscarPorMontoYEstado(@Param("montoMinimo") BigDecimal montoMinimo, @Param("estadoPago") String estadoPago);
}
