package com.example.ms_productos.service;

import com.example.ms_productos.client.InventarioClient;
import com.example.ms_productos.dto.external.InventarioDTO;
import com.example.ms_productos.dto.request.ProductoRequestDTO;
import com.example.ms_productos.dto.response.ProductoResponseDTO;
import com.example.ms_productos.exception.ResourceNotFoundException;
import com.example.ms_productos.mapper.ProductoMapper;
import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.model.Producto;
import com.example.ms_productos.repository.CategoriaRepository;
import com.example.ms_productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;
    private final InventarioClient inventarioClient;

    public List<ProductoResponseDTO> listarTodos() {
        log.info("Listando todos los productos");
        return productoRepository.findAll().stream()
                .map(productoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO buscarPorId(Integer id) {
        log.info("Buscando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return productoMapper.toResponseDTO(producto);
    }

    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {
        try {
            log.info("Guardando nuevo producto");
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + dto.getCategoriaId()));

            Producto producto = productoMapper.toEntity(dto);
            producto.setCategoria(categoria);
            return productoMapper.toResponseDTO(productoRepository.save(producto));
        } catch (ResourceNotFoundException e) {
            log.error("Error de validacion al guardar producto: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar producto: {}", e.getMessage());
            throw new RuntimeException("Error interno al guardar el producto");
        }
    }

    public ProductoResponseDTO actualizar(Integer id, ProductoRequestDTO dto) {
        log.info("Actualizando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + dto.getCategoriaId()));

        producto.setNombre(dto.getNombre());
        producto.setCodigoSku(dto.getCodigoSku());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setFechaIngreso(dto.getFechaIngreso());
        producto.setDisponible(dto.isDisponible());
        producto.setCategoria(categoria);

        return productoMapper.toResponseDTO(productoRepository.save(producto));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        productoRepository.delete(producto);
    }

    public List<ProductoResponseDTO> buscarPorNombreYPrecio(String nombre, Double precio) {
        log.info("Buscando productos por nombre: {} y precio menor a: {}", nombre, precio);
        return productoRepository.findByNombreContainingIgnoreCaseAndPrecioLessThan(nombre, precio).stream()
                .map(productoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Comunicacion entre microservicios via OpenFeign.
     * Busca el producto en la base local y, ademas, consulta a ms-inventario
     * para saber en que bodegas hay stock registrado de ese producto.
     * Si ms-inventario no responde, no se rompe la respuesta: simplemente
     * se devuelve la lista de bodegas vacia y se deja un log del error.
     */
    public List<InventarioDTO> buscarInventarioDeProducto(Integer id) {
        log.info("Consultando a ms-inventario el stock del producto con ID: {}", id);

        // Primero verificamos que el producto exista en nuestra propia base.
        productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        try {
            return inventarioClient.buscarPorProductoId(id);
        } catch (Exception e) {
            log.error("No se pudo contactar a ms-inventario para el producto {}: {}", id, e.getMessage());
            return Collections.emptyList();
        }
    }
}