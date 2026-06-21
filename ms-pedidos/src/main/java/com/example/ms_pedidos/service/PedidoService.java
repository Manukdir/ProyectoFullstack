package com.example.ms_pedidos.service;

import com.example.ms_pedidos.dto.PedidoDTO;
import com.example.ms_pedidos.dto.PedidoRequestDTO;
import com.example.ms_pedidos.exception.ResourceNotFoundException;
import com.example.ms_pedidos.mapper.PedidoMapper;
import com.example.ms_pedidos.model.Pedido;
import com.example.ms_pedidos.repository.PedidoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contiene la lógica de negocio de pedidos y coordina repositorio y mapper.
 */
@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

        @Autowired
        private PedidoRepository pedidoRepository;
    @Autowired
        private PedidoMapper pedidoMapper;

    public List<PedidoDTO> listar() {
        log.info("Listando pedidos");
        return pedidoRepository.findAll().stream().map(pedidoMapper::toDTO).collect(Collectors.toList());
    }

    public PedidoDTO obtenerPorId(Integer id) {
        log.info("Buscando pedido con id {}", id);
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id " + id));
        return pedidoMapper.toDTO(pedido);
    }

    public PedidoDTO crear(PedidoRequestDTO dto) {
        Pedido pedido = pedidoMapper.toEntity(dto);
        Pedido guardado = pedidoRepository.save(pedido);
        log.info("Pedido creado con id {}", guardado.getId());
        return pedidoMapper.toDTO(guardado);
    }

    public PedidoDTO actualizar(Integer id, PedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id " + id));
        pedidoMapper.updateEntity(pedido, dto);
        Pedido guardado = pedidoRepository.save(pedido);
        log.info("Pedido actualizado con id {}", guardado.getId());
        return pedidoMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido no encontrado con id " + id);
        }
        pedidoRepository.deleteById(id);
        log.info("Pedido eliminado con id {}", id);
        return true;
    }

    public List<PedidoDTO> buscarPedidosPagados() {
        log.info("Ejecutando consulta personalizada de pedidos");
        return pedidoRepository.buscarPedidosPagadosOrdenados().stream().map(pedidoMapper::toDTO).collect(Collectors.toList());
    }

}
