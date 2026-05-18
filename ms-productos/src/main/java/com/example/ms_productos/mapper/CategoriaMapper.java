package com.example.ms_productos.mapper;

import com.example.ms_productos.dto.request.CategoriaRequestDTO;
import com.example.ms_productos.dto.response.CategoriaResponseDTO;
import com.example.ms_productos.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria entity = new Categoria();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCodigoAlterno(dto.getCodigoAlterno());
        entity.setPrioridadVisualizacion(dto.getPrioridadVisualizacion());
        entity.setActivo(dto.isActivo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        return entity;
    }

    public CategoriaResponseDTO toResponseDTO(Categoria entity) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCodigoAlterno(entity.getCodigoAlterno());
        dto.setPrioridadVisualizacion(entity.getPrioridadVisualizacion());
        dto.setActivo(entity.isActivo());
        dto.setFechaCreacion(entity.getFechaCreacion());
        return dto;
    }
}