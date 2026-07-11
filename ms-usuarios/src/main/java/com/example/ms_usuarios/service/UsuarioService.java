package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.exception.ResourceNotFoundException;
import com.example.ms_usuarios.mapper.UsuarioMapper;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilService perfilService, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.perfilService = perfilService;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDTO).toList();
    }

    public UsuarioDTO obtenerPorId(Integer id) {
        return usuarioMapper.toDTO(buscarEntidad(id));
    }

    public UsuarioDTO crear(UsuarioRequestDTO dto) {
        Perfil perfil = perfilService.buscarEntidad(dto.getPerfilId());
        Usuario usuario = usuarioMapper.toEntity(dto, perfil);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO actualizar(Integer id, UsuarioRequestDTO dto) {
        Usuario usuario = buscarEntidad(id);
        Perfil perfil = perfilService.buscarEntidad(dto.getPerfilId());
        usuarioMapper.updateEntity(usuario, dto, perfil);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void eliminar(Integer id) {
        Usuario usuario = buscarEntidad(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarEntidad(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id " + id));
    }
}
