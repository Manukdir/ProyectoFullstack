package com.example.ms_pedidos.service;

import com.example.ms_pedidos.dto.DetallePedidoDTO;
import com.example.ms_pedidos.dto.DetallePedidoRequestDTO;
import com.example.ms_pedidos.mapper.DetallePedidoMapper;
import com.example.ms_pedidos.model.DetallePedido;
import com.example.ms_pedidos.model.Pedido;
import com.example.ms_pedidos.repository.DetallePedidoRepository;
import com.example.ms_pedidos.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<DetallePedido> optional = detallePedidoRepository.findById(id);
        return optional.map(detallePedidoMapper::toDTO).orElse(null);
    }

    public DetallePedidoDTO crear(DetallePedidoRequestDTO dto) {
        try {
            Pedido pedido = pedidoRepository.findById(dto.getPedidoId()).orElse(null);
                        if (pedido == null) {
                            return null;
                        }

            DetallePedido detallePedido = detallePedidoMapper.toEntity(dto, pedido);
            DetallePedido guardado = detallePedidoRepository.save(detallePedido);
            log.info("DetallePedido creado con id {}", guardado.getId());
            return detallePedidoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear detallePedido", e);
            return null;
        }
    }

    public DetallePedidoDTO actualizar(Integer id, DetallePedidoRequestDTO dto) {
        try {
            Optional<DetallePedido> optional = detallePedidoRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            Pedido pedido = pedidoRepository.findById(dto.getPedidoId()).orElse(null);
                        if (pedido == null) {
                            return null;
                        }
            DetallePedido detallePedido = optional.get();
            detallePedidoMapper.updateEntity(detallePedido, dto);
            detallePedido.setPedido(pedido);
            DetallePedido guardado = detallePedidoRepository.save(detallePedido);
            log.info("DetallePedido actualizado con id {}", guardado.getId());
            return detallePedidoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar detallePedido", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!detallePedidoRepository.existsById(id)) {
            return false;
        }
        detallePedidoRepository.deleteById(id);
        log.info("DetallePedido eliminado con id {}", id);
        return true;
    }

}
