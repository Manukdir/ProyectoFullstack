package com.example.ms_productos.service;

import com.example.ms_productos.dto.ProductoDTO;
import com.example.ms_productos.dto.ProductoRequestDTO;
import com.example.ms_productos.mapper.ProductoMapper;
import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.model.Producto;
import com.example.ms_productos.repository.CategoriaRepository;
import com.example.ms_productos.repository.ProductoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

        @Autowired
        private ProductoRepository productoRepository;
    @Autowired
        private ProductoMapper productoMapper;
    @Autowired
        private CategoriaRepository categoriaRepository;

    public List<ProductoDTO> listar() {
        log.info("Listando productos");
        return productoRepository.findAll().stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Integer id) {
        log.info("Buscando producto con id {}", id);
        Optional<Producto> optional = productoRepository.findById(id);
        return optional.map(productoMapper::toDTO).orElse(null);
    }

    public ProductoDTO crear(ProductoRequestDTO dto) {
        try {
                        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);
                        if (categoria == null) {
                            return null;
                        }

            Producto producto = productoMapper.toEntity(dto, categoria);
            Producto guardado = productoRepository.save(producto);
            log.info("Producto creado con id {}", guardado.getId());
            return productoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear producto", e);
            return null;
        }
    }

    public ProductoDTO actualizar(Integer id, ProductoRequestDTO dto) {
        try {
            Optional<Producto> optional = productoRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
                        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);
                        if (categoria == null) {
                            return null;
                        }
            Producto producto = optional.get();
            productoMapper.updateEntity(producto, dto);
            producto.setCategoria(categoria);
            Producto guardado = productoRepository.save(producto);
            log.info("Producto actualizado con id {}", guardado.getId());
            return productoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar producto", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        log.info("Producto eliminado con id {}", id);
        return true;
    }

    public List<ProductoDTO> buscarPorNombreYPrecio(String nombre, BigDecimal precioMaximo) {
        log.info("Ejecutando consulta personalizada de productos");
        return productoRepository.findByNombreContainingIgnoreCaseAndPrecioLessThan(nombre, precioMaximo).stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }


}
