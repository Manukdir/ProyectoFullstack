package com.example.ms_inventario.mapper;

import com.example.ms_inventario.dto.request.InventarioRequestDTO;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import com.example.ms_inventario.model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public Inventario toEntity(InventarioRequestDTO dto) {
        Inventario entity = new Inventario();
        entity.setCodigoBodega(dto.getCodigoBodega());
        entity.setNombreBodega(dto.getNombreBodega());
        entity.setCapacidadTotal(dto.getCapacidadTotal());
        entity.setActivo(dto.isActivo());
        entity.setFechaApertura(dto.getFechaApertura());
        return entity;
    }

    public InventarioResponseDTO toResponseDTO(Inventario entity) {
        InventarioResponseDTO dto = new InventarioResponseDTO();
        dto.setId(entity.getId());
        dto.setCodigoBodega(entity.getCodigoBodega());
        dto.setNombreBodega(entity.getNombreBodega());
        dto.setCapacidadTotal(entity.getCapacidadTotal());
        dto.setActivo(entity.isActivo());
        dto.setFechaApertura(entity.getFechaApertura());
        return dto;
    }
}