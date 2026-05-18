package com.example.ms_proveedores.service;

import com.example.ms_proveedores.dto.ContratoDTO;
import com.example.ms_proveedores.dto.ContratoRequestDTO;
import com.example.ms_proveedores.mapper.ContratoMapper;
import com.example.ms_proveedores.model.Contrato;
import com.example.ms_proveedores.model.Proveedor;
import com.example.ms_proveedores.repository.ContratoRepository;
import com.example.ms_proveedores.repository.ProveedorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Contrato> optional = contratoRepository.findById(id);
        return optional.map(contratoMapper::toDTO).orElse(null);
    }

    public ContratoDTO crear(ContratoRequestDTO dto) {
        try {
                        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
                        if (proveedor == null) {
                            return null;
                        }

            Contrato contrato = contratoMapper.toEntity(dto, proveedor);
            Contrato guardado = contratoRepository.save(contrato);
            log.info("Contrato creado con id {}", guardado.getId());
            return contratoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear contrato", e);
            return null;
        }
    }

    public ContratoDTO actualizar(Integer id, ContratoRequestDTO dto) {
        try {
            Optional<Contrato> optional = contratoRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
                        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
                        if (proveedor == null) {
                            return null;
                        }
            Contrato contrato = optional.get();
            contratoMapper.updateEntity(contrato, dto);
            contrato.setProveedor(proveedor);
            Contrato guardado = contratoRepository.save(contrato);
            log.info("Contrato actualizado con id {}", guardado.getId());
            return contratoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar contrato", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!contratoRepository.existsById(id)) {
            return false;
        }
        contratoRepository.deleteById(id);
        log.info("Contrato eliminado con id {}", id);
        return true;
    }


}
