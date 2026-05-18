package com.example.ms_usuarios.mapper;

import com.example.ms_usuarios.dto.request.UsuarioRequestDTO;
import com.example.ms_usuarios.dto.response.UsuarioResponseDTO;
import com.example.ms_usuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario entity = new Usuario();
        entity.setNombreCompleto(dto.getNombreCompleto());
        entity.setEmail(dto.getEmail());
        entity.setPuntosAcumulados(dto.getPuntosAcumulados());
        entity.setActivo(dto.isActivo());
        entity.setFechaRegistro(dto.getFechaRegistro());
        return entity;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario entity) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(entity.getId());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setEmail(entity.getEmail());
        dto.setPuntosAcumulados(entity.getPuntosAcumulados());
        dto.setActivo(entity.isActivo());
        dto.setFechaRegistro(entity.getFechaRegistro());
        return dto;
    }
}