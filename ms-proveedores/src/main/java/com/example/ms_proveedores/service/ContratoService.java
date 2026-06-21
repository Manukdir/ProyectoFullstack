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
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Gestiona contratos y comprueba que el proveedor relacionado exista.
 */
@Service
public class ContratoService {

    private static final Logger log = LoggerFactory.getLogger(ContratoService.class);

        @Autowired
        private ContratoRepository contratoRepository;
    @Autowired
        private ContratoMapper contratoMapper;
    @Autowired
        private ProveedorRepository proveedorRepository;

    public List<ContratoDTO> listar() {
        log.info("Listando contratos");
        return contratoRepository.findAll().stream().map(contratoMapper::toDTO).collect(Collectors.toList());
    }

    public ContratoDTO obtenerPorId(Integer id) {
        log.info("Buscando contrato con id {}", id);
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contrato no encontrado con id " + id));
        return contratoMapper.toDTO(contrato);
    }

    public ContratoDTO crear(ContratoRequestDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con id " + dto.getProveedorId()));
        Contrato contrato = contratoMapper.toEntity(dto, proveedor);
        Contrato guardado = contratoRepository.save(contrato);
        log.info("Contrato creado con id {}", guardado.getId());
        return contratoMapper.toDTO(guardado);
    }

    public ContratoDTO actualizar(Integer id, ContratoRequestDTO dto) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contrato no encontrado con id " + id));
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proveedor no encontrado con id " + dto.getProveedorId()));
        contratoMapper.updateEntity(contrato, dto);
        contrato.setProveedor(proveedor);
        Contrato guardado = contratoRepository.save(contrato);
        log.info("Contrato actualizado con id {}", guardado.getId());
        return contratoMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!contratoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrato no encontrado con id " + id);
        }
        contratoRepository.deleteById(id);
        log.info("Contrato eliminado con id {}", id);
        return true;
    }


}
