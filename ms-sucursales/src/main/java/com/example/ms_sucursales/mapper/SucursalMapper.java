package com.example.ms_sucursales.mapper;

import com.example.ms_sucursales.dto.SucursalDTO;
import com.example.ms_sucursales.dto.SucursalRequestDTO;
import com.example.ms_sucursales.model.Region;
import com.example.ms_sucursales.model.Sucursal;

import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    // Convierte una entidad Sucursal en DTO para responder por la API.
    public SucursalDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) {
            return null;
        }

        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());

        // La entidad tiene un objeto Region; el DTO expone solo su ID.
        dto.setRegionId(
                sucursal.getRegion() != null
                        ? sucursal.getRegion().getId()
                        : null
        );

        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setCapacidadBodega(sucursal.getCapacidadBodega());
        dto.setVentasMensuales(sucursal.getVentasMensuales());
        dto.setActiva(sucursal.getActiva());
        dto.setFechaApertura(sucursal.getFechaApertura());

        return dto;
    }

    // Crea una entidad nueva usando el DTO y la Región encontrada por el Service.
    public Sucursal toEntity(SucursalRequestDTO dto, Region region) {
        Sucursal sucursal = new Sucursal();
        updateEntity(sucursal, dto, region);
        return sucursal;
    }

    // Actualiza los campos de una sucursal existente.
    public void updateEntity(Sucursal sucursal, SucursalRequestDTO dto, Region region) {
        sucursal.setRegion(region);
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCapacidadBodega(dto.getCapacidadBodega());
        sucursal.setVentasMensuales(dto.getVentasMensuales());
        sucursal.setActiva(dto.getActiva());
        sucursal.setFechaApertura(dto.getFechaApertura());
    }
}