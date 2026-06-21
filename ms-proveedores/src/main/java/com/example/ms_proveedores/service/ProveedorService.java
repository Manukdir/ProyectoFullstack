package com.example.ms_proveedores.service;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.dto.ProveedorRequestDTO;
import com.example.ms_proveedores.exception.ResourceNotFoundException;
import com.example.ms_proveedores.mapper.ProveedorMapper;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ProveedorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementa la lógica de negocio de los proveedores.
 */
@Service
public class ProveedorService {

    private static final Logger log = LoggerFactory.getLogger(ProveedorService.class);

        @Autowired
        private ProveedorRepository proveedorRepository;
    @Autowired
        private ProveedorMapper proveedorMapper;

    public List<ProveedorDTO> listar() {
        log.info("Listando proveedors");
        return proveedorRepository.findAll().stream().map(proveedorMapper::toDTO).collect(Collectors.toList());
    }

    public ProveedorDTO obtenerPorId(Integer id) {
        log.info("Buscando proveedor con id {}", id);
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con id " + id));
        return proveedorMapper.toDTO(proveedor);
    }

    public ProveedorDTO crear(ProveedorRequestDTO dto) {
        Proveedor proveedor = proveedorMapper.toEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        log.info("Proveedor creado con id {}", guardado.getId());
        return proveedorMapper.toDTO(guardado);
    }

    public ProveedorDTO actualizar(Integer id, ProveedorRequestDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con id " + id));
        proveedorMapper.updateEntity(proveedor, dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        log.info("Proveedor actualizado con id {}", guardado.getId());
        return proveedorMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!proveedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado con id " + id);
        }
        proveedorRepository.deleteById(id);
        log.info("Proveedor eliminado con id {}", id);
        return true;
    }

    public List<ProveedorDTO> buscarProveedoresActivos() {
        log.info("Ejecutando consulta personalizada de proveedors");
        return proveedorRepository.buscarProveedoresActivosOrdenados().stream().map(proveedorMapper::toDTO).collect(Collectors.toList());
    }


}
