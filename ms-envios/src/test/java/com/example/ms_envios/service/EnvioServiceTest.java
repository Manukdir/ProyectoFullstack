package com.example.ms_envios.service;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.mapper.EnvioMapper;
import com.example.ms_envios.model.Envio;
import com.example.ms_envios.repository.EnvioRepository;
import java.time.LocalDate;
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
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;
    @Mock
    private EnvioMapper envioMapper;
    @InjectMocks
    private EnvioService envioService;

    @Test
    void listarRetornaEnviosConvertidos() {
        Envio envio = new Envio();
        EnvioDTO dto = new EnvioDTO();
        when(envioRepository.findAll()).thenReturn(List.of(envio));
        when(envioMapper.toDTO(envio)).thenReturn(dto);

        assertEquals(List.of(dto), envioService.listar());
    }

    @Test
    void obtenerPorIdRetornaEnvioCuandoExiste() {
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        assertEquals(esperado, envioService.obtenerPorId(1));
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(envioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> envioService.obtenerPorId(99));
    }

    @Test
    void crearGuardaYRetornaEnvio() {
        EnvioRequestDTO request = new EnvioRequestDTO();
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();
        when(envioMapper.toEntity(request)).thenReturn(envio);
        when(envioRepository.save(envio)).thenReturn(envio);
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        assertEquals(esperado, envioService.crear(request));
    }

    @Test
    void actualizarModificaYRetornaEnvio() {
        EnvioRequestDTO request = new EnvioRequestDTO();
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
        when(envioRepository.save(envio)).thenReturn(envio);
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        assertEquals(esperado, envioService.actualizar(1, request));
        verify(envioMapper).updateEntity(envio, request);
    }

    @Test
    void eliminarBorraEnvioExistente() {
        when(envioRepository.existsById(1)).thenReturn(true);

        assertTrue(envioService.eliminar(1));
        verify(envioRepository).deleteById(1);
    }

    @Test
    void eliminarLanzaExcepcionCuandoNoExiste() {
        when(envioRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> envioService.eliminar(99));
    }

    @Test
    void buscarNoEntregadosRetornaConsultaConvertida() {
        LocalDate desde = LocalDate.of(2026, 6, 1);
        LocalDate hasta = LocalDate.of(2026, 6, 30);
        Envio envio = new Envio();
        EnvioDTO dto = new EnvioDTO();
        when(envioRepository.buscarNoEntregadosPorRango(desde, hasta)).thenReturn(List.of(envio));
        when(envioMapper.toDTO(envio)).thenReturn(dto);

        assertEquals(List.of(dto), envioService.buscarNoEntregadosPorRango(desde, hasta));
    }
}
