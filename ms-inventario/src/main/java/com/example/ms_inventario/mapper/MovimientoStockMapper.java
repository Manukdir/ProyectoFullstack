package com.example.ms_inventario.mapper;

import com.example.ms_inventario.dto.MovimientoStockDTO;
import com.example.ms_inventario.dto.MovimientoStockRequestDTO;
import com.example.ms_inventario.model.MovimientoStock;
import com.example.ms_inventario.model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class MovimientoStockMapper {

    public MovimientoStockDTO toDTO(MovimientoStock movimientoStock) {
        if (movimientoStock == null) {
            return null;
        }
        MovimientoStockDTO dto = new MovimientoStockDTO();
        dto.setId(movimientoStock.getId());
                if (movimientoStock.getInventario() != null) {
                    dto.setInventarioId(movimientoStock.getInventario().getId());
                }
        dto.setTipoMovimiento(movimientoStock.getTipoMovimiento());
        dto.setMotivo(movimientoStock.getMotivo());
        dto.setDocumentoReferencia(movimientoStock.getDocumentoReferencia());
        dto.setCantidad(movimientoStock.getCantidad());
        dto.setCostoMovimiento(movimientoStock.getCostoMovimiento());
        dto.setActivo(movimientoStock.getActivo());
        dto.setFechaMovimiento(movimientoStock.getFechaMovimiento());
        return dto;
    }

    public MovimientoStock toEntity(MovimientoStockRequestDTO dto, Inventario inventario) {
        MovimientoStock movimientoStock = new MovimientoStock();
        updateEntity(movimientoStock, dto);
        movimientoStock.setInventario(inventario);
        return movimientoStock;
    }

    public void updateEntity(MovimientoStock movimientoStock, MovimientoStockRequestDTO dto) {
                movimientoStock.setTipoMovimiento(dto.getTipoMovimiento());
        movimientoStock.setMotivo(dto.getMotivo());
        movimientoStock.setDocumentoReferencia(dto.getDocumentoReferencia());
        movimientoStock.setCantidad(dto.getCantidad());
        movimientoStock.setCostoMovimiento(dto.getCostoMovimiento());
        movimientoStock.setActivo(dto.getActivo());
        movimientoStock.setFechaMovimiento(dto.getFechaMovimiento());
    }
}
