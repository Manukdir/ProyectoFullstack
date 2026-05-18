package com.example.ms_inventario.service;

import com.example.ms_inventario.dto.InventarioDTO;
import com.example.ms_inventario.dto.InventarioRequestDTO;
import com.example.ms_inventario.mapper.InventarioMapper;
import com.example.ms_inventario.model.Inventario;
import com.example.ms_inventario.repository.InventarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    private static final Logger log = LoggerFactory.getLogger(InventarioService.class);

        @Autowired
        private InventarioRepository inventarioRepository;
    @Autowired
        private InventarioMapper inventarioMapper;

    public List<InventarioDTO> listar() {
        log.info("Listando inventarios");
        return inventarioRepository.findAll().stream().map(inventarioMapper::toDTO).collect(Collectors.toList());
    }

    public InventarioDTO obtenerPorId(Integer id) {
        log.info("Buscando inventario con id {}", id);
        Optional<Inventario> optional = inventarioRepository.findById(id);
        return optional.map(inventarioMapper::toDTO).orElse(null);
    }

    public InventarioDTO crear(InventarioRequestDTO dto) {
        try {

            Inventario inventario = inventarioMapper.toEntity(dto);
            Inventario guardado = inventarioRepository.save(inventario);
            log.info("Inventario creado con id {}", guardado.getId());
            return inventarioMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear inventario", e);
            return null;
        }
    }

    public InventarioDTO actualizar(Integer id, InventarioRequestDTO dto) {
        try {
            Optional<Inventario> optional = inventarioRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            Inventario inventario = optional.get();
            inventarioMapper.updateEntity(inventario, dto);
            
            Inventario guardado = inventarioRepository.save(inventario);
            log.info("Inventario actualizado con id {}", guardado.getId());
            return inventarioMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar inventario", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!inventarioRepository.existsById(id)) {
            return false;
        }
        inventarioRepository.deleteById(id);
        log.info("Inventario eliminado con id {}", id);
        return true;
    }

    public List<InventarioDTO> buscarDisponiblesSobreCantidad(Integer cantidadMinima) {
        log.info("Ejecutando consulta personalizada de inventarios");
        return inventarioRepository.findByCantidadDisponibleGreaterThanAndActivoTrue(cantidadMinima).stream().map(inventarioMapper::toDTO).collect(Collectors.toList());
    }

}
