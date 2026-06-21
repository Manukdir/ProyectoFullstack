package com.example.ms_pedidos.mapper;

import com.example.ms_pedidos.dto.DetallePedidoDTO;
import com.example.ms_pedidos.dto.DetallePedidoRequestDTO;
import com.example.ms_pedidos.model.DetallePedido;
import com.example.ms_pedidos.model.Pedido;
import org.springframework.stereotype.Component;

/**
 * Separa el modelo de base de datos de los datos enviados al cliente.
 */
@Component
public class DetallePedidoMapper {

    public DetallePedidoDTO toDTO(DetallePedido detallePedido) {
        if (detallePedido == null) {
            return null;
        }
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detallePedido.getId());
                if (detallePedido.getPedido() != null) {
                    dto.setPedidoId(detallePedido.getPedido().getId());
                }
        dto.setProductoId(detallePedido.getProductoId());
        dto.setNombreProducto(detallePedido.getNombreProducto());
        dto.setObservacion(detallePedido.getObservacion());
        dto.setCantidad(detallePedido.getCantidad());
        dto.setPrecioUnitario(detallePedido.getPrecioUnitario());
        dto.setSubtotal(detallePedido.getSubtotal());
        dto.setActivo(detallePedido.getActivo());
        dto.setFechaRegistro(detallePedido.getFechaRegistro());
        return dto;
    }

    public DetallePedido toEntity(DetallePedidoRequestDTO dto, Pedido pedido) {
        DetallePedido detallePedido = new DetallePedido();
        updateEntity(detallePedido, dto);
        detallePedido.setPedido(pedido);
        return detallePedido;
    }

    public void updateEntity(DetallePedido detallePedido, DetallePedidoRequestDTO dto) {
                detallePedido.setProductoId(dto.getProductoId());
        detallePedido.setNombreProducto(dto.getNombreProducto());
        detallePedido.setObservacion(dto.getObservacion());
        detallePedido.setCantidad(dto.getCantidad());
        detallePedido.setPrecioUnitario(dto.getPrecioUnitario());
        detallePedido.setSubtotal(dto.getSubtotal());
        detallePedido.setActivo(dto.getActivo());
        detallePedido.setFechaRegistro(dto.getFechaRegistro());
    }
}
