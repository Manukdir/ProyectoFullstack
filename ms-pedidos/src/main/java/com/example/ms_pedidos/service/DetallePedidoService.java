package com.example.ms_pedidos.service;

import com.example.ms_pedidos.dto.DetallePedidoDTO;
import com.example.ms_pedidos.dto.DetallePedidoRequestDTO;
import com.example.ms_pedidos.exception.ResourceNotFoundException;
import com.example.ms_pedidos.mapper.DetallePedidoMapper;
import com.example.ms_pedidos.model.DetallePedido;
import com.example.ms_pedidos.model.Pedido;
import com.example.ms_pedidos.repository.DetallePedidoRepository;
import com.example.ms_pedidos.repository.PedidoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Gestiona los detalles y valida que el pedido relacionado exista.
 */
@Service
public class DetallePedidoService {

    private static final Logger log = LoggerFactory.getLogger(DetallePedidoService.class);

        @Autowired
        private DetallePedidoRepository detallePedidoRepository;
    @Autowired
        private DetallePedidoMapper detallePedidoMapper;
    @Autowired
        private PedidoRepository pedidoRepository;

    public List<DetallePedidoDTO> listar() {
        log.info("Listando detallePedidos");
        return detallePedidoRepository.findAll().stream().map(detallePedidoMapper::toDTO).collect(Collectors.toList());
    }

    public DetallePedidoDTO obtenerPorId(Integer id) {
        log.info("Buscando detallePedido con id {}", id);
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Detalle de pedido no encontrado con id " + id));
        return detallePedidoMapper.toDTO(detalle);
    }

    public DetallePedidoDTO crear(DetallePedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedido no encontrado con id " + dto.getPedidoId()));
        DetallePedido detallePedido = detallePedidoMapper.toEntity(dto, pedido);
        DetallePedido guardado = detallePedidoRepository.save(detallePedido);
        log.info("DetallePedido creado con id {}", guardado.getId());
        return detallePedidoMapper.toDTO(guardado);
    }

    public DetallePedidoDTO actualizar(Integer id, DetallePedidoRequestDTO dto) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Detalle de pedido no encontrado con id " + id));
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedido no encontrado con id " + dto.getPedidoId()));
        detallePedidoMapper.updateEntity(detallePedido, dto);
        detallePedido.setPedido(pedido);
        DetallePedido guardado = detallePedidoRepository.save(detallePedido);
        log.info("DetallePedido actualizado con id {}", guardado.getId());
        return detallePedidoMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Detalle de pedido no encontrado con id " + id);
        }
        detallePedidoRepository.deleteById(id);
        log.info("DetallePedido eliminado con id {}", id);
        return true;
    }

}
