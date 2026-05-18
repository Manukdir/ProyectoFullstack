package com.example.ms_productos.mapper;

import com.example.ms_productos.dto.ProductoDTO;
import com.example.ms_productos.dto.ProductoRequestDTO;
import com.example.ms_productos.model.Producto;
import com.example.ms_productos.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
                if (producto.getCategoria() != null) {
                    dto.setCategoriaId(producto.getCategoria().getId());
                }
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setSku(producto.getSku());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setActivo(producto.getActivo());
        dto.setFechaIngreso(producto.getFechaIngreso());
        return dto;
    }

    public Producto toEntity(ProductoRequestDTO dto, Categoria categoria) {
        Producto producto = new Producto();
        updateEntity(producto, dto);
        producto.setCategoria(categoria);
        return producto;
    }

    public void updateEntity(Producto producto, ProductoRequestDTO dto) {
                producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setSku(dto.getSku());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setActivo(dto.getActivo());
        producto.setFechaIngreso(dto.getFechaIngreso());
    }
}
