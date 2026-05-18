package com.example.ms_reportes.service;

import com.example.ms_reportes.dto.ReporteDTO;
import com.example.ms_reportes.dto.ReporteRequestDTO;
import com.example.ms_reportes.mapper.ReporteMapper;
import com.example.ms_reportes.model.Reporte;
import com.example.ms_reportes.repository.ReporteRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);

        @Autowired
        private ReporteRepository reporteRepository;
    @Autowired
        private ReporteMapper reporteMapper;

    public List<ReporteDTO> listar() {
        log.info("Listando reportes");
        return reporteRepository.findAll().stream().map(reporteMapper::toDTO).collect(Collectors.toList());
    }

    public ReporteDTO obtenerPorId(Integer id) {
        log.info("Buscando reporte con id {}", id);
        Optional<Reporte> optional = reporteRepository.findById(id);
        return optional.map(reporteMapper::toDTO).orElse(null);
    }

    public ReporteDTO crear(ReporteRequestDTO dto) {
        try {

            Reporte reporte = reporteMapper.toEntity(dto);
            Reporte guardado = reporteRepository.save(reporte);
            log.info("Reporte creado con id {}", guardado.getId());
            return reporteMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear reporte", e);
            return null;
        }
    }

    public ReporteDTO actualizar(Integer id, ReporteRequestDTO dto) {
        try {
            Optional<Reporte> optional = reporteRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Reporte reporte = optional.get();
            reporteMapper.updateEntity(reporte, dto);
            
            Reporte guardado = reporteRepository.save(reporte);
            log.info("Reporte actualizado con id {}", guardado.getId());
            return reporteMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar reporte", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!reporteRepository.existsById(id)) {
            return false;
        }
        reporteRepository.deleteById(id);
        log.info("Reporte eliminado con id {}", id);
        return true;
    }

}
