package com.example.ms_pagos.service;

import com.example.ms_pagos.client.PedidoClient;
import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.exception.RemoteServiceException;
import com.example.ms_pagos.exception.ResourceNotFoundException;
import com.example.ms_pagos.mapper.PagoMapper;
import com.example.ms_pagos.model.Pago;
import com.example.ms_pagos.repository.PagoRepository;
import feign.FeignException;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Contiene la logica principal de los pagos.
 */
@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final PedidoClient pedidoClient;

    public PagoService(PagoRepository pagoRepository, PagoMapper pagoMapper, PedidoClient pedidoClient) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
        this.pedidoClient = pedidoClient;
    }

    public List<PagoDTO> listar() {
        log.info("Listando pagos");
        return pagoRepository.findAll().stream()
                .map(pagoMapper::toDTO)
                .toList();
    }

    public PagoDTO obtenerPorId(Integer id) {
        log.info("Buscando pago con id {}", id);
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el pago con id " + id));
        return pagoMapper.toDTO(pago);
    }

    public PagoDTO crear(PagoRequestDTO dto) {
        validarPedidoExiste(dto.getPedidoId());
        Pago pago = pagoMapper.toEntity(dto);
        Pago guardado = pagoRepository.save(pago);
        log.info("Pago creado con id {}", guardado.getId());
        return pagoMapper.toDTO(guardado);
    }

    public PagoDTO actualizar(Integer id, PagoRequestDTO dto) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el pago con id " + id));
        validarPedidoExiste(dto.getPedidoId());
        pagoMapper.updateEntity(pago, dto);
        Pago guardado = pagoRepository.save(pago);
        log.info("Pago actualizado con id {}", guardado.getId());
        return pagoMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe el pago con id " + id);
        }
        pagoRepository.deleteById(id);
        log.info("Pago eliminado con id {}", id);
        return true;
    }

    public List<PagoDTO> buscarPorMontoYEstado(BigDecimal montoMinimo, String estadoPago) {
        log.info("Ejecutando consulta personalizada de pagos");
        return pagoRepository.buscarPorMontoYEstado(montoMinimo, estadoPago).stream()
                .map(pagoMapper::toDTO)
                .toList();
    }

    private void validarPedidoExiste(Integer pedidoId) {
        try {
            pedidoClient.obtenerPedido(pedidoId);
        } catch (FeignException.NotFound exception) {
            throw new ResourceNotFoundException("No existe el pedido con id " + pedidoId);
        } catch (FeignException exception) {
            throw new RemoteServiceException("No fue posible validar el pedido con id " + pedidoId, exception);
        }
    }
}
