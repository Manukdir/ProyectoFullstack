package com.example.ms_envios.service;

import com.example.ms_envios.client.PedidoClient;
import com.example.ms_envios.client.UsuarioClient;
import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.exception.RemoteServiceException;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.mapper.EnvioMapper;
import com.example.ms_envios.model.Envio;
import com.example.ms_envios.repository.EnvioRepository;
import feign.FeignException;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Contiene las reglas de negocio y operaciones principales de los envios.
 */
@Service
public class EnvioService {

    private static final Logger log = LoggerFactory.getLogger(EnvioService.class);

    private final EnvioRepository envioRepository;
    private final EnvioMapper envioMapper;
    private final PedidoClient pedidoClient;
    private final UsuarioClient usuarioClient;

    public EnvioService(
            EnvioRepository envioRepository,
            EnvioMapper envioMapper,
            PedidoClient pedidoClient,
            UsuarioClient usuarioClient) {
        this.envioRepository = envioRepository;
        this.envioMapper = envioMapper;
        this.pedidoClient = pedidoClient;
        this.usuarioClient = usuarioClient;
    }

    public List<EnvioDTO> listar() {
        log.info("Listando envios");
        return envioRepository.findAll().stream()
                .map(envioMapper::toDTO)
                .toList();
    }

    public EnvioDTO obtenerPorId(Integer id) {
        log.info("Buscando envio con id {}", id);
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con id " + id));
        return envioMapper.toDTO(envio);
    }

    public EnvioDTO crear(EnvioRequestDTO dto) {
        validarReferencias(dto);
        Envio envio = envioMapper.toEntity(dto);
        Envio guardado = envioRepository.save(envio);
        log.info("Envio creado con id {}", guardado.getId());
        return envioMapper.toDTO(guardado);
    }

    public EnvioDTO actualizar(Integer id, EnvioRequestDTO dto) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envio no encontrado con id " + id));
        validarReferencias(dto);
        envioMapper.updateEntity(envio, dto);
        Envio guardado = envioRepository.save(envio);
        log.info("Envio actualizado con id {}", guardado.getId());
        return envioMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!envioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Envio no encontrado con id " + id);
        }
        envioRepository.deleteById(id);
        log.info("Envio eliminado con id {}", id);
        return true;
    }

    public List<EnvioDTO> buscarNoEntregadosPorRango(LocalDate desde, LocalDate hasta) {
        log.info("Ejecutando consulta personalizada de envios");
        return envioRepository.buscarNoEntregadosPorRango(desde, hasta).stream()
                .map(envioMapper::toDTO)
                .toList();
    }

    private void validarReferencias(EnvioRequestDTO dto) {
        validarPedidoExiste(dto.getPedidoId());
        validarUsuarioExiste(dto.getUsuarioId());
    }

    private void validarPedidoExiste(Integer pedidoId) {
        try {
            pedidoClient.obtenerPedido(pedidoId);
        } catch (FeignException.NotFound exception) {
            throw new ResourceNotFoundException("Pedido no encontrado con id " + pedidoId);
        } catch (FeignException exception) {
            throw new RemoteServiceException("No fue posible validar el pedido con id " + pedidoId, exception);
        }
    }

    private void validarUsuarioExiste(Integer usuarioId) {
        try {
            usuarioClient.obtenerUsuario(usuarioId);
        } catch (FeignException.NotFound exception) {
            throw new ResourceNotFoundException("Usuario no encontrado con id " + usuarioId);
        } catch (FeignException exception) {
            throw new RemoteServiceException("No fue posible validar el usuario con id " + usuarioId, exception);
        }
    }
}
