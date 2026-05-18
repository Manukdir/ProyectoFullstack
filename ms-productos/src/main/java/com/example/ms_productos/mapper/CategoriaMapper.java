package com.example.ms_productos.mapper;

import com.example.ms_productos.dto.CategoriaDTO;
import com.example.ms_productos.dto.CategoriaRequestDTO;
import com.example.ms_productos.model.Categoria;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
                dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        dto.setPrioridad(categoria.getPrioridad());
        dto.setMargenGanancia(categoria.getMargenGanancia());
        dto.setActiva(categoria.getActiva());
        dto.setFechaCreacion(categoria.getFechaCreacion());
        return dto;
    }

    public Categoria toEntity(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        updateEntity(categoria, dto);
        
        return categoria;
    }

    public void updateEntity(Categoria categoria, CategoriaRequestDTO dto) {
                categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setPrioridad(dto.getPrioridad());
        categoria.setMargenGanancia(dto.getMargenGanancia());
        categoria.setActiva(dto.getActiva());
        categoria.setFechaCreacion(dto.getFechaCreacion());
    }
}
