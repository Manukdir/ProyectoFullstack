package com.example.ms_proveedores.service;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.mapper.ProveedorMapper;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ProveedorRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void obtenerPorIdRetornaProveedorCuandoExiste() {
        Proveedor proveedor = new Proveedor();
        ProveedorDTO esperado = new ProveedorDTO();

        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorMapper.toDTO(proveedor)).thenReturn(esperado);

        ProveedorDTO resultado = proveedorService.obtenerPorId(1);

        assertEquals(esperado, resultado);
    }
}
