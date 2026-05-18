package com.example.ms_reportes.mapper;

import com.example.ms_reportes.dto.ReporteDTO;
import com.example.ms_reportes.dto.ReporteRequestDTO;
import com.example.ms_reportes.model.Reporte;

import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {

    public ReporteDTO toDTO(Reporte reporte) {
        if (reporte == null) {
            return null;
        }
        ReporteDTO dto = new ReporteDTO();
        dto.setId(reporte.getId());
                dto.setNombre(reporte.getNombre());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setResumen(reporte.getResumen());
        dto.setCantidadPedidos(reporte.getCantidadPedidos());
        dto.setTotalVentas(reporte.getTotalVentas());
        dto.setActivo(reporte.getActivo());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        return dto;
    }

    public Reporte toEntity(ReporteRequestDTO dto) {
        Reporte reporte = new Reporte();
        updateEntity(reporte, dto);
        
        return reporte;
    }

    public void updateEntity(Reporte reporte, ReporteRequestDTO dto) {
                reporte.setNombre(dto.getNombre());
        reporte.setTipoReporte(dto.getTipoReporte());
        reporte.setResumen(dto.getResumen());
        reporte.setCantidadPedidos(dto.getCantidadPedidos());
        reporte.setTotalVentas(dto.getTotalVentas());
        reporte.setActivo(dto.getActivo());
        reporte.setFechaGeneracion(dto.getFechaGeneracion());
    }
}
