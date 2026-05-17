package cl.duoc.fullstack.mspagos.mapper;

import cl.duoc.fullstack.mspagos.dto.PagoDTO;
import cl.duoc.fullstack.mspagos.dto.PagoRequestDTO;
import cl.duoc.fullstack.mspagos.model.Pago;

public class PagoMapper {

    private PagoMapper() {
    }

    public static PagoDTO toDTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setPedidoId(pago.getPedidoId());
        dto.setCodigoTransaccion(pago.getCodigoTransaccion());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstadoPago(pago.getEstadoPago());
        dto.setConfirmado(pago.isConfirmado());
        dto.setFechaPago(pago.getFechaPago());
        return dto;
    }

    public static Pago toEntity(PagoRequestDTO request) {
        Pago pago = new Pago();
        pago.setPedidoId(request.getPedidoId());
        pago.setCodigoTransaccion(request.getCodigoTransaccion());
        pago.setMonto(request.getMonto());
        pago.setMetodoPago(request.getMetodoPago());
        pago.setEstadoPago(request.getEstadoPago());
        pago.setConfirmado(request.isConfirmado());
        pago.setFechaPago(request.getFechaPago());
        return pago;
    }
}
