package com.example.ms_productos.service;

import com.example.ms_productos.dto.CategoriaDTO;
import com.example.ms_productos.dto.CategoriaRequestDTO;
import com.example.ms_productos.mapper.CategoriaMapper;
import com.example.ms_productos.model.Categoria;
import com.example.ms_productos.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

        @Autowired
        private CategoriaRepository categoriaRepository;
    @Autowired
        private CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> listar() {
        log.info("Listando categorias");
        return categoriaRepository.findAll().stream().map(categoriaMapper::toDTO).collect(Collectors.toList());
    }

    public CategoriaDTO obtenerPorId(Integer id) {
        log.info("Buscando categoria con id {}", id);
        Optional<Categoria> optional = categoriaRepository.findById(id);
        return optional.map(categoriaMapper::toDTO).orElse(null);
    }

    public CategoriaDTO crear(CategoriaRequestDTO dto) {
        try {
            

            Categoria categoria = categoriaMapper.toEntity(dto);
            Categoria guardado = categoriaRepository.save(categoria);
            log.info("Categoria creado con id {}", guardado.getId());
            return categoriaMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear categoria", e);
            return null;
        }
    }

    public CategoriaDTO actualizar(Integer id, CategoriaRequestDTO dto) {
        try {
            Optional<Categoria> optional = categoriaRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Categoria categoria = optional.get();
            categoriaMapper.updateEntity(categoria, dto);
            
            Categoria guardado = categoriaRepository.save(categoria);
            log.info("Categoria actualizado con id {}", guardado.getId());
            return categoriaMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar categoria", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }
        categoriaRepository.deleteById(id);
        log.info("Categoria eliminado con id {}", id);
        return true;
    }


}
