package com.example.ms_pagos.service;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.exception.ResourceNotFoundException;
import com.example.ms_pagos.mapper.PagoMapper;
import com.example.ms_pagos.model.Pago;
import com.example.ms_pagos.repository.PagoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;
    @Mock
    private PagoMapper pagoMapper;
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
    }

    @Test
    void obtenerPorIdRetornaPagoCuandoExiste() {
        Pago pago = new Pago();
        PagoDTO esperado = new PagoDTO();
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        when(pagoMapper.toDTO(pago)).thenReturn(esperado);

        PagoDTO resultado = pagoService.obtenerPorId(1);

        assertEquals(esperado, resultado);
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(pagoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pagoService.obtenerPorId(99));
    }

    @Test
    void crearGuardaYRetornaPago() {
        PagoRequestDTO request = new PagoRequestDTO();
        Pago pago = new Pago();
        Pago guardado = new Pago();
        guardado.setId(1);
        PagoDTO esperado = new PagoDTO();
        when(pagoMapper.toEntity(request)).thenReturn(pago);
        when(pagoRepository.save(pago)).thenReturn(guardado);
        when(pagoMapper.toDTO(guardado)).thenReturn(esperado);

        PagoDTO resultado = pagoService.crear(request);

        assertEquals(esperado, resultado);
        verify(pagoRepository).save(pago);
    }

    @Test
    void actualizarModificaYRetornaPago() {
        PagoRequestDTO request = new PagoRequestDTO();
        Pago pago = new Pago();
        PagoDTO esperado = new PagoDTO();
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(pago)).thenReturn(pago);
        when(pagoMapper.toDTO(pago)).thenReturn(esperado);

        PagoDTO resultado = pagoService.actualizar(1, request);

        assertEquals(esperado, resultado);
        verify(pagoMapper).updateEntity(pago, request);
    }

    @Test
    void actualizarLanzaExcepcionCuandoNoExiste() {
        PagoRequestDTO request = new PagoRequestDTO();
        when(pagoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pagoService.actualizar(99, request));
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
    }
}
