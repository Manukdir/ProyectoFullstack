package com.example.ms_inventario.service;

import com.example.ms_inventario.dto.request.InventarioRequestDTO;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import com.example.ms_inventario.exception.ResourceNotFoundException;
import com.example.ms_inventario.mapper.InventarioMapper;
import com.example.ms_inventario.model.Inventario;
import com.example.ms_inventario.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;

    public List<InventarioResponseDTO> listarTodos() {
        log.info("Listando todos los inventarios");
        return inventarioRepository.findAll().stream()
                .map(inventarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public InventarioResponseDTO buscarPorId(Integer id) {
        log.info("Buscando inventario con ID: {}", id);
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + id));
        return inventarioMapper.toResponseDTO(inventario);
    }

    public InventarioResponseDTO guardar(InventarioRequestDTO dto) {
        try {
            log.info("Guardando nuevo inventario");
            Inventario inventario = inventarioMapper.toEntity(dto);
            return inventarioMapper.toResponseDTO(inventarioRepository.save(inventario));
        } catch (Exception e) {
            log.error("Error al guardar inventario: {}", e.getMessage());
            throw new RuntimeException("Error interno al guardar el inventario");
        }
    }

    public InventarioResponseDTO actualizar(Integer id, InventarioRequestDTO dto) {
        log.info("Actualizando inventario con ID: {}", id);
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + id));

        inventario.setCodigoBodega(dto.getCodigoBodega());
        inventario.setNombreBodega(dto.getNombreBodega());
        inventario.setCapacidadTotal(dto.getCapacidadTotal());
        inventario.setActivo(dto.isActivo());
        inventario.setFechaApertura(dto.getFechaApertura());

        return inventarioMapper.toResponseDTO(inventarioRepository.save(inventario));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando inventario con ID: {}", id);
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + id));
        inventarioRepository.delete(inventario);
    }
}