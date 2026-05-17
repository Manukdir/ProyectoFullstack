package cl.duoc.fullstack.mspedidos.mapper;

import cl.duoc.fullstack.mspedidos.dto.DetallePedidoDTO;
import cl.duoc.fullstack.mspedidos.dto.DetallePedidoRequestDTO;
import cl.duoc.fullstack.mspedidos.model.DetallePedido;

public class DetallePedidoMapper {

    private DetallePedidoMapper() {
    }

    public static DetallePedidoDTO toDTO(DetallePedido detallePedido) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detallePedido.getId());
        dto.setPedidoId(detallePedido.getPedido().getId());
        dto.setProductoId(detallePedido.getProductoId());
        dto.setNombreProducto(detallePedido.getNombreProducto());
        dto.setCantidad(detallePedido.getCantidad());
        dto.setPrecioUnitario(detallePedido.getPrecioUnitario());
        dto.setSubtotal(detallePedido.getSubtotal());
        dto.setActivo(detallePedido.isActivo());
        dto.setFechaRegistro(detallePedido.getFechaRegistro());
        return dto;
    }

    public static DetallePedido toEntity(DetallePedidoRequestDTO request) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setProductoId(request.getProductoId());
        detallePedido.setNombreProducto(request.getNombreProducto());
        detallePedido.setCantidad(request.getCantidad());
        detallePedido.setPrecioUnitario(request.getPrecioUnitario());
        detallePedido.setSubtotal(request.getSubtotal());
        detallePedido.setActivo(request.isActivo());
        detallePedido.setFechaRegistro(request.getFechaRegistro());
        return detallePedido;
    }
}
