package com.example.ms_pagos.mapper;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.model.Pago;

import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoDTO toDTO(Pago pago) {
        if (pago == null) {
            return null;
        }
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
                dto.setPedidoId(pago.getPedidoId());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstadoPago(pago.getEstadoPago());
        dto.setCodigoTransaccion(pago.getCodigoTransaccion());
        dto.setNumeroCuotas(pago.getNumeroCuotas());
        dto.setMonto(pago.getMonto());
        dto.setPagado(pago.getPagado());
        dto.setFechaPago(pago.getFechaPago());
        return dto;
    }

    public Pago toEntity(PagoRequestDTO dto) {
        Pago pago = new Pago();
        updateEntity(pago, dto);
        
        return pago;
    }

    public void updateEntity(Pago pago, PagoRequestDTO dto) {
                pago.setPedidoId(dto.getPedidoId());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstadoPago(dto.getEstadoPago());
        pago.setCodigoTransaccion(dto.getCodigoTransaccion());
        pago.setNumeroCuotas(dto.getNumeroCuotas());
        pago.setMonto(dto.getMonto());
        pago.setPagado(dto.getPagado());
        pago.setFechaPago(dto.getFechaPago());
    }
}
