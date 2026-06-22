package com.example.ms_productos.mapper;

import com.example.ms_productos.dto.request.ProductoRequestDTO;
import com.example.ms_productos.dto.response.ProductoResponseDTO;
import com.example.ms_productos.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto entity = new Producto();
        entity.setNombre(dto.getNombre());
        entity.setCodigoSku(dto.getCodigoSku());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setDisponible(dto.isDisponible());
        return entity;
    }

    public ProductoResponseDTO toResponseDTO(Producto entity) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCodigoSku(entity.getCodigoSku());
        dto.setPrecio(entity.getPrecio());
        dto.setStock(entity.getStock());
        dto.setFechaIngreso(entity.getFechaIngreso());
        dto.setDisponible(entity.isDisponible());

        if (entity.getCategoria() != null) {
            dto.setCategoriaId(entity.getCategoria().getId());
            dto.setCategoriaNombre(entity.getCategoria().getNombre());
        }

        return dto;
    }
}