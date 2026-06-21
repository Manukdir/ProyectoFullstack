package com.example.ms_proveedores.service;

import com.example.ms_proveedores.dto.ContratoDTO;
import com.example.ms_proveedores.dto.ContratoRequestDTO;
import com.example.ms_proveedores.exception.ResourceNotFoundException;
import com.example.ms_proveedores.mapper.ContratoMapper;
import com.example.ms_proveedores.model.Contrato;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ContratoRepository;
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
class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;
    @Mock
    private ContratoMapper contratoMapper;
    @Mock
    private ProveedorRepository proveedorRepository;
    @InjectMocks
    private ContratoService contratoService;

    @Test
    void listarRetornaContratosConvertidos() {
        Contrato contrato = new Contrato();
        ContratoDTO dto = new ContratoDTO();
        when(contratoRepository.findAll()).thenReturn(List.of(contrato));
        when(contratoMapper.toDTO(contrato)).thenReturn(dto);

        assertEquals(List.of(dto), contratoService.listar());
    }

    @Test
    void obtenerPorIdLanzaExcepcionCuandoNoExiste() {
        when(contratoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> contratoService.obtenerPorId(99));
    }

    @Test
    void crearGuardaContratoCuandoProveedorExiste() {
        ContratoRequestDTO request = new ContratoRequestDTO();
        request.setProveedorId(1);
        Proveedor proveedor = new Proveedor();
        Contrato contrato = new Contrato();
        ContratoDTO esperado = new ContratoDTO();
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(contratoMapper.toEntity(request, proveedor)).thenReturn(contrato);
        when(contratoRepository.save(contrato)).thenReturn(contrato);
        when(contratoMapper.toDTO(contrato)).thenReturn(esperado);

        assertEquals(esperado, contratoService.crear(request));
    }

    @Test
    void crearLanzaExcepcionCuandoProveedorNoExiste() {
        ContratoRequestDTO request = new ContratoRequestDTO();
        request.setProveedorId(99);
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contratoService.crear(request));
    }

    @Test
    void actualizarModificaContratoExistente() {
        ContratoRequestDTO request = new ContratoRequestDTO();
        request.setProveedorId(1);
        Proveedor proveedor = new Proveedor();
        Contrato contrato = new Contrato();
        ContratoDTO esperado = new ContratoDTO();
        when(contratoRepository.findById(1)).thenReturn(Optional.of(contrato));
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(contratoRepository.save(contrato)).thenReturn(contrato);
        when(contratoMapper.toDTO(contrato)).thenReturn(esperado);

        assertEquals(esperado, contratoService.actualizar(1, request));
        verify(contratoMapper).updateEntity(contrato, request);
    }

    @Test
    void eliminarBorraContratoExistente() {
        when(contratoRepository.existsById(1)).thenReturn(true);

        assertTrue(contratoService.eliminar(1));
        verify(contratoRepository).deleteById(1);
    }
}
