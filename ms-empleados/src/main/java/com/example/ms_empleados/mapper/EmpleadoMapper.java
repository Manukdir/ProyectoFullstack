package com.example.ms_empleados.mapper;

import com.example.ms_empleados.dto.EmpleadoDTO;
import com.example.ms_empleados.dto.EmpleadoRequestDTO;
import com.example.ms_empleados.model.Empleado;

import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null) {
            return null;
        }
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
                dto.setSucursalId(empleado.getSucursalId());
        dto.setNombre(empleado.getNombre());
        dto.setEmail(empleado.getEmail());
        dto.setCargo(empleado.getCargo());
        dto.setHorasSemanales(empleado.getHorasSemanales());
        dto.setSueldo(empleado.getSueldo());
        dto.setActivo(empleado.getActivo());
        dto.setFechaIngreso(empleado.getFechaIngreso());
        return dto;
    }

    public Empleado toEntity(EmpleadoRequestDTO dto) {
        Empleado empleado = new Empleado();
        updateEntity(empleado, dto);
        
        return empleado;
    }

    public void updateEntity(Empleado empleado, EmpleadoRequestDTO dto) {
                empleado.setSucursalId(dto.getSucursalId());
        empleado.setNombre(dto.getNombre());
        empleado.setEmail(dto.getEmail());
        empleado.setCargo(dto.getCargo());
        empleado.setHorasSemanales(dto.getHorasSemanales());
        empleado.setSueldo(dto.getSueldo());
        empleado.setActivo(dto.getActivo());
        empleado.setFechaIngreso(dto.getFechaIngreso());
    }
}
