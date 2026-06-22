package com.example.ms_proveedores.service;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.dto.ProveedorRequestDTO;
import com.example.ms_proveedores.mapper.ProveedorMapper;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ProveedorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Proveedor> optional = proveedorRepository.findById(id);
        return optional.map(proveedorMapper::toDTO).orElse(null);
    }

    public ProveedorDTO crear(ProveedorRequestDTO dto) {
        try {
            

            Proveedor proveedor = proveedorMapper.toEntity(dto);
            Proveedor guardado = proveedorRepository.save(proveedor);
            log.info("Proveedor creado con id {}", guardado.getId());
            return proveedorMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear proveedor", e);
            return null;
        }
    }

    public ProveedorDTO actualizar(Integer id, ProveedorRequestDTO dto) {
        try {
            Optional<Proveedor> optional = proveedorRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Proveedor proveedor = optional.get();
            proveedorMapper.updateEntity(proveedor, dto);
            
            Proveedor guardado = proveedorRepository.save(proveedor);
            log.info("Proveedor actualizado con id {}", guardado.getId());
            return proveedorMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar proveedor", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!proveedorRepository.existsById(id)) {
            return false;
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
