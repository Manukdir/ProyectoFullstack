package com.example.ms_inventario.mapper;

import com.example.ms_inventario.dto.InventarioDTO;
import com.example.ms_inventario.dto.InventarioRequestDTO;
import com.example.ms_inventario.model.Inventario;

import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioDTO toDTO(Inventario inventario) {
        if (inventario == null) {
            return null;
        }
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
                dto.setProductoId(inventario.getProductoId());
        dto.setCodigoBodega(inventario.getCodigoBodega());
        dto.setUbicacion(inventario.getUbicacion());
        dto.setCantidadDisponible(inventario.getCantidadDisponible());
        dto.setCantidadMinima(inventario.getCantidadMinima());
        dto.setActivo(inventario.getActivo());
        dto.setFechaActualizacion(inventario.getFechaActualizacion());
        return dto;
    }

    public Inventario toEntity(InventarioRequestDTO dto) {
        Inventario inventario = new Inventario();
        updateEntity(inventario, dto);
        
        return inventario;
    }

    public void updateEntity(Inventario inventario, InventarioRequestDTO dto) {
                inventario.setProductoId(dto.getProductoId());
        inventario.setCodigoBodega(dto.getCodigoBodega());
        inventario.setUbicacion(dto.getUbicacion());
        inventario.setCantidadDisponible(dto.getCantidadDisponible());
        inventario.setCantidadMinima(dto.getCantidadMinima());
        inventario.setActivo(dto.getActivo());
        inventario.setFechaActualizacion(dto.getFechaActualizacion());
    }
}
