package com.example.ms_envios.repository;

import com.example.ms_envios.model.Envio;
import com.example.ms_envios.model.Seguimiento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Test
    void guardaYBuscaEnvio() {
        Envio guardado = envioRepository.save(envio(false));

        assertTrue(envioRepository.findById(guardado.getId()).isPresent());
        assertEquals(1, guardado.getPedidoId());
        assertEquals(1, guardado.getUsuarioId());
    }

    @Test
    void buscaNoEntregadosPorRango() {
        envioRepository.save(envio(false));
        envioRepository.save(envio(true));

        assertEquals(1, envioRepository.buscarNoEntregadosPorRango(
                LocalDate.now().minusDays(2),
                LocalDate.now()).size());
    }

    @Test
    void guardaSeguimientoRelacionadoPorEnvioId() {
        Envio envio = envioRepository.save(envio(false));
        Seguimiento seguimiento = new Seguimiento(
                null,
                envio.getId(),
                "EN_TRANSITO",
                "Pedido recibido en centro de distribucion",
                "Santiago",
                1,
                true,
                LocalDateTime.now().minusHours(1));

        Seguimiento guardado = seguimientoRepository.save(seguimiento);

        assertTrue(seguimientoRepository.findById(guardado.getId()).isPresent());
        assertEquals(envio.getId(), guardado.getEnvioId());
    }

    private Envio envio(boolean entregado) {
        return new Envio(
                null,
                1,
                1,
                "ENV-REPO-" + entregado,
                "Av. Principal 123",
                "Chilexpress",
                new BigDecimal("3990.00"),
                entregado,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(3));
    }
}
