package com.example.ms_productos.service;

import com.example.ms_productos.dto.request.CategoriaRequestDTO;
import com.example.ms_productos.dto.response.CategoriaResponseDTO;
import com.example.ms_productos.exception.ResourceNotFoundException;
import com.example.ms_productos.mapper.CategoriaMapper;
import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponseDTO> listarTodos() {
        log.info("Listando todas las categorias");
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO buscarPorId(Integer id) {
        log.info("Buscando categoria con ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
        return categoriaMapper.toResponseDTO(categoria);
    }

    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto) {
        try {
            log.info("Guardando nueva categoria");
            Categoria categoria = categoriaMapper.toEntity(dto);
            return categoriaMapper.toResponseDTO(categoriaRepository.save(categoria));
        } catch (Exception e) {
            log.error("Error al guardar categoria: {}", e.getMessage());
            throw new RuntimeException("Error interno al guardar la categoria");
        }
    }

    public CategoriaResponseDTO actualizar(Integer id, CategoriaRequestDTO dto) {
        log.info("Actualizando categoria con ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setCodigoAlterno(dto.getCodigoAlterno());
        categoria.setPrioridadVisualizacion(dto.getPrioridadVisualizacion());
        categoria.setActivo(dto.isActivo());
        categoria.setFechaCreacion(dto.getFechaCreacion());

        return categoriaMapper.toResponseDTO(categoriaRepository.save(categoria));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando categoria con ID: {}", id);
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con ID: " + id));
        categoriaRepository.delete(categoria);
    }
}