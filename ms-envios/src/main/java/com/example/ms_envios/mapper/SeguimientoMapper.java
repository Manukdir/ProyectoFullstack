package com.example.ms_envios.mapper;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.model.Seguimiento;

import org.springframework.stereotype.Component;

/**
 * Convierte los datos de seguimiento sin exponer directamente la entidad JPA.
 */
@Component
public class SeguimientoMapper {

    public SeguimientoDTO toDTO(Seguimiento seguimiento) {
        if (seguimiento == null) {
            return null;
        }
        SeguimientoDTO dto = new SeguimientoDTO();
        dto.setId(seguimiento.getId());
                dto.setEnvioId(seguimiento.getEnvioId());
        dto.setEstado(seguimiento.getEstado());
        dto.setDescripcion(seguimiento.getDescripcion());
        dto.setUbicacion(seguimiento.getUbicacion());
        dto.setOrden(seguimiento.getOrden());
        dto.setVisibleCliente(seguimiento.getVisibleCliente());
        dto.setFechaEvento(seguimiento.getFechaEvento());
        return dto;
    }

    public Seguimiento toEntity(SeguimientoRequestDTO dto) {
        Seguimiento seguimiento = new Seguimiento();
        updateEntity(seguimiento, dto);
        
        return seguimiento;
    }

    public void updateEntity(Seguimiento seguimiento, SeguimientoRequestDTO dto) {
                seguimiento.setEnvioId(dto.getEnvioId());
        seguimiento.setEstado(dto.getEstado());
        seguimiento.setDescripcion(dto.getDescripcion());
        seguimiento.setUbicacion(dto.getUbicacion());
        seguimiento.setOrden(dto.getOrden());
        seguimiento.setVisibleCliente(dto.getVisibleCliente());
        seguimiento.setFechaEvento(dto.getFechaEvento());
    }
}
