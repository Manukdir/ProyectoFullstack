package com.example.ms_usuarios.mapper;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setActivo(usuario.getActivo());
        if (usuario.getPerfil() != null) {
            dto.setPerfilId(usuario.getPerfil().getId());
            dto.setPerfilNombre(usuario.getPerfil().getNombre());
        }
        return dto;
    }

    public Usuario toEntity(UsuarioRequestDTO dto, Perfil perfil) {
        Usuario usuario = new Usuario();
        updateEntity(usuario, dto, perfil);
        return usuario;
    }

    public void updateEntity(Usuario usuario, UsuarioRequestDTO dto, Perfil perfil) {
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setActivo(dto.getActivo());
        usuario.setPerfil(perfil);
    }
}
