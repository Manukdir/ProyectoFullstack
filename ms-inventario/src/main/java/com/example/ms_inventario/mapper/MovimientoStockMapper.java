package com.example.ms_inventario.mapper;

import com.example.ms_inventario.dto.request.MovimientoStockRequestDTO;
import com.example.ms_inventario.dto.response.MovimientoStockResponseDTO;
import com.example.ms_inventario.model.MovimientoStock;
import org.springframework.stereotype.Component;

@Component
public class MovimientoStockMapper {

    public MovimientoStock toEntity(MovimientoStockRequestDTO dto) {
        MovimientoStock entity = new MovimientoStock();
        entity.setTipoMovimiento(dto.getTipoMovimiento());
        entity.setMotivo(dto.getMotivo());
        entity.setCantidad(dto.getCantidad());
        entity.setAprobado(dto.isAprobado());
        entity.setFechaMovimiento(dto.getFechaMovimiento());
        entity.setProductoId(dto.getProductoId());
        return entity;
    }

    public MovimientoStockResponseDTO toResponseDTO(MovimientoStock entity) {
        MovimientoStockResponseDTO dto = new MovimientoStockResponseDTO();
        dto.setId(entity.getId());
        dto.setTipoMovimiento(entity.getTipoMovimiento());
        dto.setMotivo(entity.getMotivo());
        dto.setCantidad(entity.getCantidad());
        dto.setAprobado(entity.isAprobado());


        dto.setFechaMovimiento(entity.getFechaMovimiento());
        dto.setProductoId(entity.getProductoId());

        return dto;
    }
}