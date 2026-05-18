package com.example.ms_proveedores.mapper;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.dto.ProveedorRequestDTO;
import com.example.ms_proveedores.model.Proveedor;

import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
                dto.setNombre(proveedor.getNombre());
        dto.setRut(proveedor.getRut());
        dto.setEmail(proveedor.getEmail());
        dto.setCalificacion(proveedor.getCalificacion());
        dto.setMontoCompras(proveedor.getMontoCompras());
        dto.setActivo(proveedor.getActivo());
        dto.setFechaRegistro(proveedor.getFechaRegistro());
        return dto;
    }

    public Proveedor toEntity(ProveedorRequestDTO dto) {
        Proveedor proveedor = new Proveedor();
        updateEntity(proveedor, dto);
        
        return proveedor;
    }

    public void updateEntity(Proveedor proveedor, ProveedorRequestDTO dto) {
                proveedor.setNombre(dto.getNombre());
        proveedor.setRut(dto.getRut());
        proveedor.setEmail(dto.getEmail());
        proveedor.setCalificacion(dto.getCalificacion());
        proveedor.setMontoCompras(dto.getMontoCompras());
        proveedor.setActivo(dto.getActivo());
        proveedor.setFechaRegistro(dto.getFechaRegistro());
    }
}
