package com.example.ms_proveedores.service;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.dto.ProveedorRequestDTO;
import com.example.ms_proveedores.exception.ResourceNotFoundException;
import com.example.ms_proveedores.mapper.ProveedorMapper;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ProveedorRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;
    @Mock
    private ProveedorMapper proveedorMapper;
    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void listarRetornaProveedoresConvertidos() {
        Proveedor proveedor = new Proveedor();
        ProveedorDTO dto = new ProveedorDTO();
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));
        when(proveedorMapper.toDTO(proveedor)).thenReturn(dto);

        assertEquals(List.of(dto), proveedorService.listar());
    }

    @Test
    void obtenerPorIdRetornaProveedorCuandoExiste() {
        Proveedor proveedor = new Proveedor();
        ProveedorDTO esperado = new ProveedorDTO();
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorMapper.toDTO(proveedor)).thenReturn(esperado);

        assertEquals(esperado, proveedorService.obtenerPorId(1));
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> proveedorService.obtenerPorId(99));
    }

    @Test
    void crearGuardaYRetornaProveedor() {
        ProveedorRequestDTO request = new ProveedorRequestDTO();
        Proveedor proveedor = new Proveedor();
        ProveedorDTO esperado = new ProveedorDTO();
        when(proveedorMapper.toEntity(request)).thenReturn(proveedor);
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        when(proveedorMapper.toDTO(proveedor)).thenReturn(esperado);

        assertEquals(esperado, proveedorService.crear(request));
    }

    @Test
    void actualizarModificaProveedor() {
        ProveedorRequestDTO request = new ProveedorRequestDTO();
        Proveedor proveedor = new Proveedor();
        ProveedorDTO esperado = new ProveedorDTO();
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        when(proveedorMapper.toDTO(proveedor)).thenReturn(esperado);

        assertEquals(esperado, proveedorService.actualizar(1, request));
        verify(proveedorMapper).updateEntity(proveedor, request);
    }

    @Test
    void eliminarBorraProveedorExistente() {
        when(proveedorRepository.existsById(1)).thenReturn(true);

        assertTrue(proveedorService.eliminar(1));
        verify(proveedorRepository).deleteById(1);
    }

    @Test
    void eliminarLanzaExcepcionCuandoNoExiste() {
        when(proveedorRepository.existsById(99)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> proveedorService.eliminar(99));
    }

    @Test
    void buscarActivosRetornaConsultaConvertida() {
        Proveedor proveedor = new Proveedor();
        ProveedorDTO dto = new ProveedorDTO();
        when(proveedorRepository.buscarProveedoresActivosOrdenados())
                .thenReturn(List.of(proveedor));
        when(proveedorMapper.toDTO(proveedor)).thenReturn(dto);

        assertEquals(List.of(dto), proveedorService.buscarProveedoresActivos());
    }
}
