package com.example.ms_usuarios.mapper;

import com.example.ms_usuarios.dto.request.PerfilRequestDTO;
import com.example.ms_usuarios.dto.response.PerfilResponseDTO;
import com.example.ms_usuarios.model.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public Perfil toEntity(PerfilRequestDTO dto) {
        Perfil entity = new Perfil();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setNivelAcceso(dto.getNivelAcceso());
        entity.setHabilitado(dto.isHabilitado());
        entity.setFechaCreacion(dto.getFechaCreacion());
        return entity;
    }

    public PerfilResponseDTO toResponseDTO(Perfil entity) {
        PerfilResponseDTO dto = new PerfilResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setNivelAcceso(entity.getNivelAcceso());
        dto.setHabilitado(entity.isHabilitado());
        dto.setFechaCreacion(entity.getFechaCreacion());
        return dto;
    }
}