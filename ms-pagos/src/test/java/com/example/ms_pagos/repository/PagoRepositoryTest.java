package com.example.ms_pagos.repository;

import com.example.ms_pagos.model.Pago;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class PagoRepositoryTest {

    @Autowired
    private PagoRepository pagoRepository;

    @Test
    void guardaYBuscaPago() {
        Pago pago = new Pago(
                null,
                1,
                "TARJETA",
                "APROBADO",
                "TRX-REPO-001",
                1,
                new BigDecimal("19990.00"),
                true,
                LocalDateTime.now().minusDays(1));

        Pago guardado = pagoRepository.save(pago);

        assertTrue(pagoRepository.findById(guardado.getId()).isPresent());
        assertEquals(1, guardado.getPedidoId());
    }

    @Test
    void buscaPorMontoYEstado() {
        Pago pago = new Pago(
                null,
                2,
                "TRANSFERENCIA",
                "APROBADO",
                "TRX-REPO-002",
                0,
                new BigDecimal("25000.00"),
                true,
                LocalDateTime.now().minusDays(1));
        pagoRepository.save(pago);

        assertEquals(1, pagoRepository.buscarPorMontoYEstado(new BigDecimal("10000.00"), "APROBADO").size());
    }
}
