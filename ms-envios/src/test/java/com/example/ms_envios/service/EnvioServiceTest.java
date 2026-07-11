package com.example.ms_envios.service;

import com.example.ms_envios.client.PedidoClient;
import com.example.ms_envios.client.UsuarioClient;
import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.dto.PedidoDTO;
import com.example.ms_envios.dto.UsuarioDTO;
import com.example.ms_envios.exception.RemoteServiceException;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.mapper.EnvioMapper;
import com.example.ms_envios.model.Envio;
import com.example.ms_envios.repository.EnvioRepository;
import feign.FeignException;
import feign.Request;
import feign.Response;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
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
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;
    @Mock
    private EnvioMapper envioMapper;
    @Mock
    private PedidoClient pedidoClient;
    @Mock
    private UsuarioClient usuarioClient;
    @InjectMocks
    private EnvioService envioService;

    @Test
    void listarRetornaEnviosConvertidos() {
        Envio envio = new Envio();
        EnvioDTO dto = new EnvioDTO();
        when(envioRepository.findAll()).thenReturn(List.of(envio));
        when(envioMapper.toDTO(envio)).thenReturn(dto);

        assertEquals(List.of(dto), envioService.listar());
        verify(envioRepository).findAll();
    }

    @Test
    void obtenerPorIdRetornaEnvioCuandoExiste() {
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        assertEquals(esperado, envioService.obtenerPorId(1));
        verify(envioRepository).findById(1);
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(envioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> envioService.obtenerPorId(99));
    }

    @Test
    void crearValidaPedidoYUsuarioGuardaYRetornaEnvio() {
        EnvioRequestDTO request = requestEnvio(1, 1);
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();
        when(pedidoClient.obtenerPedido(1)).thenReturn(new PedidoDTO());
        when(usuarioClient.obtenerUsuario(1)).thenReturn(new UsuarioDTO());
        when(envioMapper.toEntity(request)).thenReturn(envio);
        when(envioRepository.save(envio)).thenReturn(envio);
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        assertEquals(esperado, envioService.crear(request));
        verify(pedidoClient).obtenerPedido(1);
        verify(usuarioClient).obtenerUsuario(1);
        verify(envioRepository).save(envio);
    }

    @Test
    void crearLanzaNotFoundCuandoPedidoNoExiste() {
        EnvioRequestDTO request = requestEnvio(99, 1);
        when(pedidoClient.obtenerPedido(99)).thenThrow(feignException(404));

        assertThrows(ResourceNotFoundException.class, () -> envioService.crear(request));
        verify(envioRepository, never()).save(any());
    }

    @Test
    void crearLanzaRemoteServiceCuandoPedidosNoResponde() {
        EnvioRequestDTO request = requestEnvio(1, 1);
        when(pedidoClient.obtenerPedido(1)).thenThrow(feignException(503));

        assertThrows(RemoteServiceException.class, () -> envioService.crear(request));
        verify(usuarioClient, never()).obtenerUsuario(any());
        verify(envioRepository, never()).save(any());
    }

    @Test
    void actualizarModificaYRetornaEnvio() {
        EnvioRequestDTO request = requestEnvio(1, 1);
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
        when(pedidoClient.obtenerPedido(1)).thenReturn(new PedidoDTO());
        when(usuarioClient.obtenerUsuario(1)).thenReturn(new UsuarioDTO());
        when(envioRepository.save(envio)).thenReturn(envio);
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        assertEquals(esperado, envioService.actualizar(1, request));
        verify(envioMapper).updateEntity(envio, request);
        verify(envioRepository).save(envio);
    }

    @Test
    void actualizarLanzaExcepcionCuandoNoExiste() {
        EnvioRequestDTO request = requestEnvio(1, 1);
        when(envioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> envioService.actualizar(99, request));
        verify(pedidoClient, never()).obtenerPedido(any());
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

    private EnvioRequestDTO requestEnvio(Integer pedidoId, Integer usuarioId) {
        return new EnvioRequestDTO(
                pedidoId,
                usuarioId,
                "ENV-1001",
                "Av. Principal 123",
                "Chilexpress",
                new BigDecimal("3990.00"),
                false,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(3));
    }

    private FeignException feignException(int status) {
        Request request = Request.create(
                Request.HttpMethod.GET,
                "/api/v1/remoto/1",
                Map.of(),
                null,
                StandardCharsets.UTF_8,
                null);
        Response response = Response.builder()
                .status(status)
                .reason("remote error")
                .request(request)
                .build();
        return FeignException.errorStatus("FeignClient#obtener", response);
    }
}
