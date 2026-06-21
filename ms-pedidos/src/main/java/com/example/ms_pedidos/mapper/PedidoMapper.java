package com.example.ms_pedidos.mapper;

import com.example.ms_pedidos.dto.PedidoDTO;
import com.example.ms_pedidos.dto.PedidoRequestDTO;
import com.example.ms_pedidos.model.Pedido;

import org.springframework.stereotype.Component;

/**
 * Convierte entre la entidad Pedido y los DTO usados por la API.
 */
@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
                dto.setUsuarioId(pedido.getUsuarioId());
        dto.setNumeroPedido(pedido.getNumeroPedido());
        dto.setEstado(pedido.getEstado());
        dto.setDireccionEntrega(pedido.getDireccionEntrega());
        dto.setCantidadProductos(pedido.getCantidadProductos());
        dto.setTotal(pedido.getTotal());
        dto.setPagado(pedido.getPagado());
        dto.setFechaPedido(pedido.getFechaPedido());
        return dto;
    }

    public Pedido toEntity(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        updateEntity(pedido, dto);
        
        return pedido;
    }

    public void updateEntity(Pedido pedido, PedidoRequestDTO dto) {
                pedido.setUsuarioId(dto.getUsuarioId());
        pedido.setNumeroPedido(dto.getNumeroPedido());
        pedido.setEstado(dto.getEstado());
        pedido.setDireccionEntrega(dto.getDireccionEntrega());
        pedido.setCantidadProductos(dto.getCantidadProductos());
        pedido.setTotal(dto.getTotal());
        pedido.setPagado(dto.getPagado());
        pedido.setFechaPedido(dto.getFechaPedido());
    }
}
