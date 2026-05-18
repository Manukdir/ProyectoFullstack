package com.example.ms_sucursales.mapper;

import com.example.ms_sucursales.dto.SucursalDTO;
import com.example.ms_sucursales.dto.SucursalRequestDTO;
import com.example.ms_sucursales.model.Sucursal;

import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public SucursalDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) {
            return null;
        }
        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
                dto.setRegionId(sucursal.getRegionId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setCapacidadBodega(sucursal.getCapacidadBodega());
        dto.setVentasMensuales(sucursal.getVentasMensuales());
        dto.setActiva(sucursal.getActiva());
        dto.setFechaApertura(sucursal.getFechaApertura());
        return dto;
    }

    public Sucursal toEntity(SucursalRequestDTO dto) {
        Sucursal sucursal = new Sucursal();
        updateEntity(sucursal, dto);
        
        return sucursal;
    }

    public void updateEntity(Sucursal sucursal, SucursalRequestDTO dto) {
                sucursal.setRegionId(dto.getRegionId());
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCapacidadBodega(dto.getCapacidadBodega());
        sucursal.setVentasMensuales(dto.getVentasMensuales());
        sucursal.setActiva(dto.getActiva());
        sucursal.setFechaApertura(dto.getFechaApertura());
    }
}
