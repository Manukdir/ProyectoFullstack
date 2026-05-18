package com.example.ms_envios.service;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.mapper.SeguimientoMapper;
import com.example.ms_envios.model.Seguimiento;
import com.example.ms_envios.repository.SeguimientoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeguimientoService {

    private static final Logger log = LoggerFactory.getLogger(SeguimientoService.class);

        @Autowired
        private SeguimientoRepository seguimientoRepository;
    @Autowired
        private SeguimientoMapper seguimientoMapper;

    public List<SeguimientoDTO> listar() {
        log.info("Listando seguimientos");
        return seguimientoRepository.findAll().stream().map(seguimientoMapper::toDTO).collect(Collectors.toList());
    }

    public SeguimientoDTO obtenerPorId(Integer id) {
        log.info("Buscando seguimiento con id {}", id);
        Optional<Seguimiento> optional = seguimientoRepository.findById(id);
        return optional.map(seguimientoMapper::toDTO).orElse(null);
    }

    public SeguimientoDTO crear(SeguimientoRequestDTO dto) {
        try {
            

            Seguimiento seguimiento = seguimientoMapper.toEntity(dto);
            Seguimiento guardado = seguimientoRepository.save(seguimiento);
            log.info("Seguimiento creado con id {}", guardado.getId());
            return seguimientoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear seguimiento", e);
            return null;
        }
    }

    public SeguimientoDTO actualizar(Integer id, SeguimientoRequestDTO dto) {
        try {
            Optional<Seguimiento> optional = seguimientoRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Seguimiento seguimiento = optional.get();
            seguimientoMapper.updateEntity(seguimiento, dto);
            
            Seguimiento guardado = seguimientoRepository.save(seguimiento);
            log.info("Seguimiento actualizado con id {}", guardado.getId());
            return seguimientoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar seguimiento", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!seguimientoRepository.existsById(id)) {
            return false;
        }
        seguimientoRepository.deleteById(id);
        log.info("Seguimiento eliminado con id {}", id);
        return true;
    }

}
