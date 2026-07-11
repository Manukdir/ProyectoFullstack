package com.example.ms_usuarios.mapper;

import com.example.ms_usuarios.dto.PerfilDTO;
import com.example.ms_usuarios.dto.PerfilRequestDTO;
import com.example.ms_usuarios.model.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public PerfilDTO toDTO(Perfil perfil) {
        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
        dto.setNombre(perfil.getNombre());
        dto.setDescripcion(perfil.getDescripcion());
        return dto;
    }

    public Perfil toEntity(PerfilRequestDTO dto) {
        Perfil perfil = new Perfil();
        updateEntity(perfil, dto);
        return perfil;
    }

    public void updateEntity(Perfil perfil, PerfilRequestDTO dto) {
        perfil.setNombre(dto.getNombre());
        perfil.setDescripcion(dto.getDescripcion());
    }
}
