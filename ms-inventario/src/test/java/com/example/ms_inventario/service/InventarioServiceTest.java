package com.example.ms_inventario.service;

import com.example.ms_inventario.client.ProductoClient;
import com.example.ms_inventario.dto.external.ProductoDTO;
import com.example.ms_inventario.dto.request.InventarioRequestDTO;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import com.example.ms_inventario.exception.ResourceNotFoundException;
import com.example.ms_inventario.mapper.InventarioMapper;
import com.example.ms_inventario.model.Inventario;
import com.example.ms_inventario.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @Mock
    private InventarioMapper inventarioMapper;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private InventarioService inventarioService;

    private Inventario inventarioMock;
    private InventarioRequestDTO requestDTOMock;
    private InventarioResponseDTO responseDTOMock;
    private ProductoDTO productoDTOMock;

    @BeforeEach
    void setUp() {
        inventarioMock = new Inventario();
        inventarioMock.setId(1);
        inventarioMock.setCodigoBodega("BOD-01");
        inventarioMock.setNombreBodega("Bodega Central");
        inventarioMock.setCapacidadTotal(100);
        inventarioMock.setActivo(true);
        inventarioMock.setProductoId(5);

        requestDTOMock = new InventarioRequestDTO();
        requestDTOMock.setCodigoBodega("BOD-01");
        requestDTOMock.setNombreBodega("Bodega Central");
        requestDTOMock.setCapacidadTotal(100);
        requestDTOMock.setActivo(true);
        requestDTOMock.setFechaApertura(LocalDate.now());
        requestDTOMock.setProductoId(5);

        responseDTOMock = new InventarioResponseDTO();
        responseDTOMock.setId(1);
        responseDTOMock.setCodigoBodega("BOD-01");
        responseDTOMock.setNombreBodega("Bodega Central");

        productoDTOMock = new ProductoDTO();
    }

    @Test
    void buscarPorId_DebeRetornarInventarioConProducto_CuandoExiste() {
        Integer idBuscado = 1;
        when(inventarioRepository.findById(idBuscado)).thenReturn(Optional.of(inventarioMock));
        when(inventarioMapper.toResponseDTO(inventarioMock)).thenReturn(responseDTOMock);
        when(productoClient.buscarPorId(5)).thenReturn(productoDTOMock);

        InventarioResponseDTO resultado = inventarioService.buscarPorId(idBuscado);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("BOD-01", resultado.getCodigoBodega());
        assertEquals(productoDTOMock, resultado.getProducto());

        verify(inventarioRepository, times(1)).findById(idBuscado);
        verify(productoClient, times(1)).buscarPorId(5);
    }

    @Test
    void buscarPorId_DebeLanzarExcepcion_CuandoNoExiste() {
        Integer idBuscado = 99;
        when(inventarioRepository.findById(idBuscado)).thenReturn(Optional.empty());

        ResourceNotFoundException excepcion = assertThrows(ResourceNotFoundException.class, () -> {
            inventarioService.buscarPorId(idBuscado);
        });

        assertEquals("Inventario no encontrado con ID: " + idBuscado, excepcion.getMessage());
        verify(inventarioRepository, times(1)).findById(idBuscado);
        verify(productoClient, never()).buscarPorId(anyInt());
    }

    @Test
    void guardar_DebeCrearYRetornarInventario() {
        when(inventarioMapper.toEntity(requestDTOMock)).thenReturn(inventarioMock);
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioMock);
        when(inventarioMapper.toResponseDTO(inventarioMock)).thenReturn(responseDTOMock);

        InventarioResponseDTO resultado = inventarioService.guardar(requestDTOMock);

        assertNotNull(resultado);
        assertEquals("BOD-01", resultado.getCodigoBodega());

        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    void eliminar_DebeBorrarInventario_CuandoExiste() {
        Integer idEliminar = 1;
        when(inventarioRepository.findById(idEliminar)).thenReturn(Optional.of(inventarioMock));

        inventarioService.eliminar(idEliminar);

        verify(inventarioRepository, times(1)).findById(idEliminar);
        verify(inventarioRepository, times(1)).delete(inventarioMock);
    }
}