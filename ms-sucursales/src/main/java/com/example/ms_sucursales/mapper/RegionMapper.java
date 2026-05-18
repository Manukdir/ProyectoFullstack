package com.example.ms_sucursales.mapper;

import com.example.ms_sucursales.dto.RegionDTO;
import com.example.ms_sucursales.dto.RegionRequestDTO;
import com.example.ms_sucursales.model.Region;

import org.springframework.stereotype.Component;

@Component
public class RegionMapper {

    public RegionDTO toDTO(Region region) {
        if (region == null) {
            return null;
        }
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
                dto.setNombre(region.getNombre());
        dto.setCodigo(region.getCodigo());
        dto.setCantidadComunas(region.getCantidadComunas());
        dto.setCostoDespachoBase(region.getCostoDespachoBase());
        dto.setActiva(region.getActiva());
        dto.setFechaCreacion(region.getFechaCreacion());
        return dto;
    }

    public Region toEntity(RegionRequestDTO dto) {
        Region region = new Region();
        updateEntity(region, dto);
        
        return region;
    }

    public void updateEntity(Region region, RegionRequestDTO dto) {
                region.setNombre(dto.getNombre());
        region.setCodigo(dto.getCodigo());
        region.setCantidadComunas(dto.getCantidadComunas());
        region.setCostoDespachoBase(dto.getCostoDespachoBase());
        region.setActiva(dto.getActiva());
        region.setFechaCreacion(dto.getFechaCreacion());
    }
}
