package com.example.ms_usuarios.mapper;

import com.example.ms_usuarios.dto.PerfilDTO;
import com.example.ms_usuarios.dto.PerfilRequestDTO;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public PerfilDTO toDTO(Perfil perfil) {
        if (perfil == null) {
            return null;
        }
        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
                if (perfil.getUsuario() != null) {
                    dto.setUsuarioId(perfil.getUsuario().getId());
                }
        dto.setNombrePerfil(perfil.getNombrePerfil());
        dto.setDescripcion(perfil.getDescripcion());
        dto.setNivelAcceso(perfil.getNivelAcceso());
        dto.setActivo(perfil.getActivo());
        dto.setFechaCreacion(perfil.getFechaCreacion());
        dto.setPermisosEspeciales(perfil.getPermisosEspeciales());
        return dto;
    }

    public Perfil toEntity(PerfilRequestDTO dto, Usuario usuario) {
        Perfil perfil = new Perfil();
        updateEntity(perfil, dto);
        perfil.setUsuario(usuario);
        return perfil;
    }

    public void updateEntity(Perfil perfil, PerfilRequestDTO dto) {
                perfil.setNombrePerfil(dto.getNombrePerfil());
        perfil.setDescripcion(dto.getDescripcion());
        perfil.setNivelAcceso(dto.getNivelAcceso());
        perfil.setActivo(dto.getActivo());
        perfil.setFechaCreacion(dto.getFechaCreacion());
        perfil.setPermisosEspeciales(dto.getPermisosEspeciales());
    }
}
