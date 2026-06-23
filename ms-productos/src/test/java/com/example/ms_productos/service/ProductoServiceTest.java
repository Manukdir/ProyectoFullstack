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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias de la capa Service de Producto.
 * Se mockean Repository, Mapper y el FeignClient: el objetivo es probar
 * SOLO la logica de ProductoService, sin levantar base de datos real
 * ni hacer llamadas HTTP reales a otro microservicio.
 */
@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProductoMapper productoMapper;

    @Mock
    private InventarioClient inventarioClient;

    @InjectMocks
    private ProductoService productoService;

    @Test
    @DisplayName("listarTodos debe retornar la lista de productos convertidos a DTO")
    void listarTodos_debeRetornarListaDeProductos() {
        // Given: el repositorio tiene un producto guardado
        Producto producto = new Producto();
        producto.setId(1);
        ProductoResponseDTO dtoEsperado = new ProductoResponseDTO();
        dtoEsperado.setId(1);

        when(productoRepository.findAll()).thenReturn(List.of(producto));
        when(productoMapper.toResponseDTO(producto)).thenReturn(dtoEsperado);

        // When: se listan todos los productos
        List<ProductoResponseDTO> resultado = productoService.listarTodos();

        // Then: se obtiene la lista con el DTO mapeado
        assertEquals(1, resultado.size());
        assertEquals(dtoEsperado, resultado.get(0));
        verify(productoRepository).findAll();
    }

    @Test
    @DisplayName("buscarPorId debe retornar el producto cuando existe")
    void buscarPorId_debeRetornarProducto_cuandoExiste() {
        // Given: el producto con ID 1 existe en el repositorio
        Producto producto = new Producto();
        producto.setId(1);
        ProductoResponseDTO dtoEsperado = new ProductoResponseDTO();
        dtoEsperado.setId(1);

        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(productoMapper.toResponseDTO(producto)).thenReturn(dtoEsperado);

        // When: se busca el producto por ID
        ProductoResponseDTO resultado = productoService.buscarPorId(1);

        // Then: se retorna el DTO correspondiente
        assertEquals(dtoEsperado, resultado);
    }

    @Test
    @DisplayName("buscarPorId debe lanzar ResourceNotFoundException cuando el producto no existe")
    void buscarPorId_debeLanzarExcepcion_cuandoNoExiste() {
        // Given: no existe ningun producto con ID 99
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then: se lanza la excepcion esperada
        assertThrows(ResourceNotFoundException.class, () -> productoService.buscarPorId(99));
    }

    @Test
    @DisplayName("guardar debe crear el producto cuando la categoria existe")
    void guardar_debeCrearProducto_cuandoCategoriaExiste() {
        // Given: una categoria valida y un request de producto
        ProductoRequestDTO request = new ProductoRequestDTO();
        request.setCategoriaId(1);

        Categoria categoria = new Categoria();
        categoria.setId(1);

        Producto productoSinGuardar = new Producto();
        Producto productoGuardado = new Producto();
        productoGuardado.setId(1);

        ProductoResponseDTO dtoEsperado = new ProductoResponseDTO();
        dtoEsperado.setId(1);

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(productoMapper.toEntity(request)).thenReturn(productoSinGuardar);
        when(productoRepository.save(productoSinGuardar)).thenReturn(productoGuardado);
        when(productoMapper.toResponseDTO(productoGuardado)).thenReturn(dtoEsperado);

        // When: se guarda el producto
        ProductoResponseDTO resultado = productoService.guardar(request);

        // Then: el producto queda asociado a la categoria y se retorna el DTO
        assertEquals(dtoEsperado, resultado);
        assertEquals(categoria, productoSinGuardar.getCategoria());
        verify(productoRepository).save(productoSinGuardar);
    }

    @Test
    @DisplayName("guardar debe lanzar ResourceNotFoundException cuando la categoria no existe")
    void guardar_debeLanzarExcepcion_cuandoCategoriaNoExiste() {
        // Given: un request que referencia una categoria inexistente
        ProductoRequestDTO request = new ProductoRequestDTO();
        request.setCategoriaId(99);

        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then: se lanza la excepcion y nunca se intenta guardar
        assertThrows(ResourceNotFoundException.class, () -> productoService.guardar(request));
        verify(productoRepository, never()).save(any());
    }

    @Test
    @DisplayName("actualizar debe modificar y retornar el producto cuando existe")
    void actualizar_debeModificarProducto_cuandoExiste() {
        // Given: un producto y una categoria existentes, y nuevos datos
        Producto producto = new Producto();
        producto.setId(1);

        Categoria categoria = new Categoria();
        categoria.setId(2);

        ProductoRequestDTO request = new ProductoRequestDTO();
        request.setNombre("Mouse Inalambrico");
        request.setCodigoSku("SKU-2000");
        request.setPrecio(15000.0);
        request.setStock(30);
        request.setFechaIngreso(LocalDate.now());
        request.setDisponible(true);
        request.setCategoriaId(2);

        ProductoResponseDTO dtoEsperado = new ProductoResponseDTO();
        dtoEsperado.setId(1);

        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(producto)).thenReturn(producto);
        when(productoMapper.toResponseDTO(producto)).thenReturn(dtoEsperado);

        // When: se actualiza el producto
        ProductoResponseDTO resultado = productoService.actualizar(1, request);

        // Then: los campos del producto quedan actualizados y se retorna el DTO
        assertEquals(dtoEsperado, resultado);
        assertEquals("Mouse Inalambrico", producto.getNombre());
        assertEquals(categoria, producto.getCategoria());
    }

    @Test
    @DisplayName("eliminar debe borrar el producto cuando existe")
    void eliminar_debeBorrarProducto_cuandoExiste() {
        // Given: el producto con ID 1 existe
        Producto producto = new Producto();
        producto.setId(1);
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // When: se elimina el producto
        productoService.eliminar(1);

        // Then: se llama a delete con el producto encontrado
        verify(productoRepository).delete(producto);
    }

    @Test
    @DisplayName("eliminar debe lanzar ResourceNotFoundException cuando el producto no existe")
    void eliminar_debeLanzarExcepcion_cuandoNoExiste() {
        // Given: no existe el producto con ID 99
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then: se lanza la excepcion esperada
        assertThrows(ResourceNotFoundException.class, () -> productoService.eliminar(99));
    }

    @Test
    @DisplayName("buscarPorNombreYPrecio debe retornar los productos que cumplen el filtro")
    void buscarPorNombreYPrecio_debeRetornarProductosFiltrados() {
        // Given: el repositorio tiene un producto que cumple el filtro
        Producto producto = new Producto();
        ProductoResponseDTO dtoEsperado = new ProductoResponseDTO();

        when(productoRepository.findByNombreContainingIgnoreCaseAndPrecioLessThan("mouse", 20000.0))
                .thenReturn(List.of(producto));
        when(productoMapper.toResponseDTO(producto)).thenReturn(dtoEsperado);

        // When: se buscan productos por nombre y precio
        List<ProductoResponseDTO> resultado = productoService.buscarPorNombreYPrecio("mouse", 20000.0);

        // Then: se retorna la lista filtrada
        assertEquals(1, resultado.size());
        assertEquals(dtoEsperado, resultado.get(0));
    }

    @Test
    @DisplayName("buscarInventarioDeProducto debe retornar el inventario cuando el producto existe y Feign responde")
    void buscarInventarioDeProducto_debeRetornarInventario_cuandoTodoFunciona() {
        // Given: el producto existe y ms-inventario responde con una bodega
        Producto producto = new Producto();
        producto.setId(1);
        InventarioDTO inventario = new InventarioDTO();
        inventario.setId(10);

        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(inventarioClient.buscarPorProductoId(1)).thenReturn(List.of(inventario));

        // When: se consulta el inventario del producto
        List<InventarioDTO> resultado = productoService.buscarInventarioDeProducto(1);

        // Then: se retorna la lista entregada por ms-inventario
        assertEquals(1, resultado.size());
        assertEquals(inventario, resultado.get(0));
    }

    @Test
    @DisplayName("buscarInventarioDeProducto debe retornar lista vacia cuando ms-inventario falla")
    void buscarInventarioDeProducto_debeRetornarListaVacia_cuandoFeignFalla() {
        // Given: el producto existe pero ms-inventario no responde (simulamos una caida)
        Producto producto = new Producto();
        producto.setId(1);

        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(inventarioClient.buscarPorProductoId(1)).thenThrow(new RuntimeException("ms-inventario no disponible"));

        // When: se consulta el inventario del producto
        List<InventarioDTO> resultado = productoService.buscarInventarioDeProducto(1);

        // Then: no se propaga el error, se retorna una lista vacia
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarInventarioDeProducto debe lanzar ResourceNotFoundException si el producto no existe")
    void buscarInventarioDeProducto_debeLanzarExcepcion_cuandoProductoNoExiste() {
        // Given: no existe el producto con ID 99
        when(productoRepository.findById(99)).thenReturn(Optional.empty());

        // When / Then: se lanza la excepcion y nunca se llama a Feign
        assertThrows(ResourceNotFoundException.class, () -> productoService.buscarInventarioDeProducto(99));
        verify(inventarioClient, never()).buscarPorProductoId(any());
    }
}
