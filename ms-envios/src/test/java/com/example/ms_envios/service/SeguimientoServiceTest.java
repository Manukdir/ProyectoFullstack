package com.example.ms_envios.service;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.mapper.SeguimientoMapper;
import com.example.ms_envios.model.Seguimiento;
import com.example.ms_envios.repository.SeguimientoRepository;
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
class SeguimientoServiceTest {

    @Mock
    private SeguimientoRepository seguimientoRepository;
    @Mock
    private SeguimientoMapper seguimientoMapper;
    @InjectMocks
    private SeguimientoService seguimientoService;

    @Test
    void listarRetornaSeguimientosConvertidos() {
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO dto = new SeguimientoDTO();
        when(seguimientoRepository.findAll()).thenReturn(List.of(seguimiento));
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(dto);

        assertEquals(List.of(dto), seguimientoService.listar());
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(seguimientoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> seguimientoService.obtenerPorId(99));
    }

    @Test
    void crearGuardaYRetornaSeguimiento() {
        SeguimientoRequestDTO request = new SeguimientoRequestDTO();
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO esperado = new SeguimientoDTO();
        when(seguimientoMapper.toEntity(request)).thenReturn(seguimiento);
        when(seguimientoRepository.save(seguimiento)).thenReturn(seguimiento);
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(esperado);

        assertEquals(esperado, seguimientoService.crear(request));
    }

    @Test
    void actualizarModificaSeguimiento() {
        SeguimientoRequestDTO request = new SeguimientoRequestDTO();
        Seguimiento seguimiento = new Seguimiento();
        SeguimientoDTO esperado = new SeguimientoDTO();
        when(seguimientoRepository.findById(1)).thenReturn(Optional.of(seguimiento));
        when(seguimientoRepository.save(seguimiento)).thenReturn(seguimiento);
        when(seguimientoMapper.toDTO(seguimiento)).thenReturn(esperado);

        assertEquals(esperado, seguimientoService.actualizar(1, request));
        verify(seguimientoMapper).updateEntity(seguimiento, request);
    }

    @Test
    void eliminarBorraSeguimientoExistente() {
        when(seguimientoRepository.existsById(1)).thenReturn(true);

        assertTrue(seguimientoService.eliminar(1));
        verify(seguimientoRepository).deleteById(1);
    }
}
