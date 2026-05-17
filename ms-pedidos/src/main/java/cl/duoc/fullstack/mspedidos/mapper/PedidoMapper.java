package cl.duoc.fullstack.mspedidos.mapper;

import cl.duoc.fullstack.mspedidos.dto.PedidoDTO;
import cl.duoc.fullstack.mspedidos.dto.PedidoRequestDTO;
import cl.duoc.fullstack.mspedidos.model.Pedido;

public class PedidoMapper {

    private PedidoMapper() {
    }

    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setNumeroPedido(pedido.getNumeroPedido());
        dto.setClienteId(pedido.getClienteId());
        dto.setTotal(pedido.getTotal());
        dto.setPagado(pedido.isPagado());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        dto.setObservacion(pedido.getObservacion());
        return dto;
    }

    public static Pedido toEntity(PedidoRequestDTO request) {
        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(request.getNumeroPedido());
        pedido.setClienteId(request.getClienteId());
        pedido.setTotal(request.getTotal());
        pedido.setPagado(request.isPagado());
        pedido.setFechaPedido(request.getFechaPedido());
        pedido.setEstado(request.getEstado());
        pedido.setObservacion(request.getObservacion());
        return pedido;
    }
}
