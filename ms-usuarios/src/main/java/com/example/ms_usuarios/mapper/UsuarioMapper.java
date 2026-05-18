package com.example.ms_usuarios.mapper;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.model.Usuario;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
                dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccionEntrega(usuario.getDireccionEntrega());
        dto.setPuntosFidelidad(usuario.getPuntosFidelidad());
        dto.setActivo(usuario.getActivo());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        return dto;
    }

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        updateEntity(usuario, dto);
        
        return usuario;
    }

    public void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {
                usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccionEntrega(dto.getDireccionEntrega());
        usuario.setPuntosFidelidad(dto.getPuntosFidelidad());
        usuario.setActivo(dto.getActivo());
        usuario.setFechaRegistro(dto.getFechaRegistro());
    }
}
