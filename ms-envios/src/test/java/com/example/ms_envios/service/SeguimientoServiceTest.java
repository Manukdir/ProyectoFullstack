package com.example.ms_envios.service;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.mapper.SeguimientoMapper;
import com.example.ms_envios.model.Seguimiento;
import com.example.ms_envios.repository.EnvioRepository;
import com.example.ms_envios.repository.SeguimientoRepository;
import java.time.LocalDateTime;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeguimientoServiceTest {

    @Mock
    private SeguimientoRepository seguimientoRepository;
    @Mock
    private SeguimientoMapper seguimientoMapper;
    @Mock
    private EnvioRepository envioRepository;
    @InjectMocks
    private SeguimientoService seguimientoService;

    @Test
    void listarRetornaSeguimientosConvertidos() {
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO dto = new SeguimientoDTO();
        when(seguimientoRepository.findAll()).thenReturn(List.of(seguimiento));
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(dto);

        assertEquals(List.of(dto), seguimientoService.listar());
        verify(seguimientoRepository).findAll();
    }

    @Test
    void obtenerPorIdRetornaSeguimientoCuandoExiste() {
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO esperado = new SeguimientoDTO();
        when(seguimientoRepository.findById(1)).thenReturn(Optional.of(seguimiento));
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(esperado);

        assertEquals(esperado, seguimientoService.obtenerPorId(1));
        verify(seguimientoRepository).findById(1);
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(seguimientoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> seguimientoService.obtenerPorId(99));
    }

    @Test
    void crearValidaEnvioGuardaYRetornaSeguimiento() {
        SeguimientoRequestDTO request = requestSeguimiento(1);
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO esperado = new SeguimientoDTO();
        when(envioRepository.existsById(1)).thenReturn(true);
        when(seguimientoMapper.toEntity(request)).thenReturn(seguimiento);
        when(seguimientoRepository.save(seguimiento)).thenReturn(seguimiento);
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(esperado);

        assertEquals(esperado, seguimientoService.crear(request));
        verify(envioRepository).existsById(1);
        verify(seguimientoRepository).save(seguimiento);
    }

    @Test
    void crearLanzaExcepcionCuandoEnvioNoExiste() {
        SeguimientoRequestDTO request = requestSeguimiento(99);
        when(envioRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> seguimientoService.crear(request));
        verify(seguimientoRepository, never()).save(any());
    }

    @Test
    void actualizarModificaSeguimiento() {
        SeguimientoRequestDTO request = requestSeguimiento(1);
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO esperado = new SeguimientoDTO();
        when(seguimientoRepository.findById(1)).thenReturn(Optional.of(seguimiento));
        when(envioRepository.existsById(1)).thenReturn(true);
        when(seguimientoRepository.save(seguimiento)).thenReturn(seguimiento);
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(esperado);

        assertEquals(esperado, seguimientoService.actualizar(1, request));
        verify(seguimientoMapper).updateEntity(seguimiento, request);
        verify(seguimientoRepository).save(seguimiento);
    }

    @Test
    void actualizarLanzaExcepcionCuandoSeguimientoNoExiste() {
        SeguimientoRequestDTO request = requestSeguimiento(1);
        when(seguimientoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> seguimientoService.actualizar(99, request));
        verify(envioRepository, never()).existsById(any());
    }

    @Test
    void eliminarBorraSeguimientoExistente() {
        when(seguimientoRepository.existsById(1)).thenReturn(true);

        assertTrue(seguimientoService.eliminar(1));
        verify(seguimientoRepository).deleteById(1);
    }

    @Test
    void eliminarLanzaExcepcionCuandoNoExiste() {
        when(seguimientoRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> seguimientoService.eliminar(99));
    }

    private SeguimientoRequestDTO requestSeguimiento(Integer envioId) {
        return new SeguimientoRequestDTO(
                envioId,
                "EN_TRANSITO",
                "Pedido recibido en centro de distribucion",
                "Santiago",
                1,
                true,
                LocalDateTime.now().minusHours(1));
    }
}
