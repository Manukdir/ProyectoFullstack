package com.example.ms_pagos.service;

import com.example.ms_pagos.client.PedidoClient;
import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.dto.PedidoDTO;
import com.example.ms_pagos.exception.RemoteServiceException;
import com.example.ms_pagos.exception.ResourceNotFoundException;
import com.example.ms_pagos.mapper.PagoMapper;
import com.example.ms_pagos.model.Pago;
import com.example.ms_pagos.repository.PagoRepository;
import feign.FeignException;
import feign.Request;
import feign.Response;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;
    @Mock
    private PagoMapper pagoMapper;
    @Mock
    private PedidoClient pedidoClient;
    @InjectMocks
    private PagoService pagoService;

    @Test
    void listarRetornaPagosConvertidos() {
        Pago pago = new Pago();
        PagoDTO dto = new PagoDTO();
        when(pagoRepository.findAll()).thenReturn(List.of(pago));
        when(pagoMapper.toDTO(pago)).thenReturn(dto);

        List<PagoDTO> resultado = pagoService.listar();

        assertEquals(List.of(dto), resultado);
        verify(pagoRepository).findAll();
    }

    @Test
    void obtenerPorIdRetornaPagoCuandoExiste() {
        Pago pago = new Pago();
        PagoDTO esperado = new PagoDTO();
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        when(pagoMapper.toDTO(pago)).thenReturn(esperado);

        PagoDTO resultado = pagoService.obtenerPorId(1);

        assertEquals(esperado, resultado);
        verify(pagoRepository).findById(1);
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(pagoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pagoService.obtenerPorId(99));
        verify(pagoRepository).findById(99);
    }

    @Test
    void crearValidaPedidoGuardaYRetornaPago() {
        PagoRequestDTO request = requestPago(1);
        Pago pago = new Pago();
        Pago guardado = new Pago();
        guardado.setId(1);
        PagoDTO esperado = new PagoDTO();
        when(pedidoClient.obtenerPedido(1)).thenReturn(new PedidoDTO());
        when(pagoMapper.toEntity(request)).thenReturn(pago);
        when(pagoRepository.save(pago)).thenReturn(guardado);
        when(pagoMapper.toDTO(guardado)).thenReturn(esperado);

        PagoDTO resultado = pagoService.crear(request);

        assertEquals(esperado, resultado);
        verify(pedidoClient).obtenerPedido(1);
        verify(pagoRepository).save(pago);
    }

    @Test
    void crearLanzaNotFoundCuandoPedidoNoExiste() {
        PagoRequestDTO request = requestPago(99);
        when(pedidoClient.obtenerPedido(99)).thenThrow(feignException(404));

        assertThrows(ResourceNotFoundException.class, () -> pagoService.crear(request));
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void crearLanzaRemoteServiceCuandoPedidosNoResponde() {
        PagoRequestDTO request = requestPago(1);
        when(pedidoClient.obtenerPedido(1)).thenThrow(feignException(503));

        assertThrows(RemoteServiceException.class, () -> pagoService.crear(request));
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void actualizarModificaYRetornaPago() {
        PagoRequestDTO request = requestPago(1);
        Pago pago = new Pago();
        PagoDTO esperado = new PagoDTO();
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        when(pedidoClient.obtenerPedido(1)).thenReturn(new PedidoDTO());
        when(pagoRepository.save(pago)).thenReturn(pago);
        when(pagoMapper.toDTO(pago)).thenReturn(esperado);

        PagoDTO resultado = pagoService.actualizar(1, request);

        assertEquals(esperado, resultado);
        verify(pagoMapper).updateEntity(pago, request);
        verify(pagoRepository).save(pago);
    }

    @Test
    void actualizarLanzaExcepcionCuandoNoExiste() {
        PagoRequestDTO request = requestPago(1);
        when(pagoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pagoService.actualizar(99, request));
        verify(pedidoClient, never()).obtenerPedido(any());
    }

    @Test
    void eliminarBorraPagoExistente() {
        when(pagoRepository.existsById(1)).thenReturn(true);

        boolean resultado = pagoService.eliminar(1);

        assertTrue(resultado);
        verify(pagoRepository).deleteById(1);
    }

    @Test
    void eliminarLanzaExcepcionCuandoNoExiste() {
        when(pagoRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> pagoService.eliminar(99));
    }

    @Test
    void buscarPorMontoYEstadoRetornaConsultaConvertida() {
        BigDecimal monto = new BigDecimal("1000");
        Pago pago = new Pago();
        PagoDTO dto = new PagoDTO();
        when(pagoRepository.buscarPorMontoYEstado(monto, "APROBADO"))
                .thenReturn(List.of(pago));
        when(pagoMapper.toDTO(pago)).thenReturn(dto);

        List<PagoDTO> resultado = pagoService.buscarPorMontoYEstado(monto, "APROBADO");

        assertEquals(List.of(dto), resultado);
        verify(pagoRepository).buscarPorMontoYEstado(monto, "APROBADO");
    }

    private PagoRequestDTO requestPago(Integer pedidoId) {
        return new PagoRequestDTO(
                pedidoId,
                "TARJETA",
                "APROBADO",
                "TRX-1001",
                1,
                new BigDecimal("19990.00"),
                true,
                LocalDateTime.now().minusDays(1));
    }

    private FeignException feignException(int status) {
        Request request = Request.create(
                Request.HttpMethod.GET,
                "/api/v1/pedidos/1",
                Map.of(),
                null,
                StandardCharsets.UTF_8,
                null);
        Response response = Response.builder()
                .status(status)
                .reason("remote error")
                .request(request)
                .build();
        return FeignException.errorStatus("PedidoClient#obtenerPedido", response);
    }
}
