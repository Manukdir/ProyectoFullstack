package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.request.UsuarioRequestDTO;
import com.example.ms_usuarios.dto.response.UsuarioResponseDTO;
import com.example.ms_usuarios.exception.ResourceNotFoundException;
import com.example.ms_usuarios.mapper.UsuarioMapper;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioResponseDTO> listarTodos() {
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Integer id) {
        log.info("Buscando usuario con ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        try {
            log.info("Guardando nuevo usuario");
            Usuario usuario = usuarioMapper.toEntity(dto);
            return usuarioMapper.toResponseDTO(usuarioRepository.save(usuario));
        } catch (Exception e) {
            log.error("Error al guardar usuario: {}", e.getMessage());
            throw new RuntimeException("Error interno al guardar el usuario");
        }
    }

    public UsuarioResponseDTO actualizar(Integer id, UsuarioRequestDTO dto) {
        log.info("Actualizando usuario con ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setEmail(dto.getEmail());
        usuario.setPuntosAcumulados(dto.getPuntosAcumulados());
        usuario.setActivo(dto.isActivo());
        usuario.setFechaRegistro(dto.getFechaRegistro());

        return usuarioMapper.toResponseDTO(usuarioRepository.save(usuario));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando usuario con ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        usuarioRepository.delete(usuario);
    }

    public List<UsuarioResponseDTO> buscarPorEmailYActivo(String email) {
        log.info("Buscando usuarios activos con email: {}", email);
        return usuarioRepository.findByEmailAndActivoTrue(email).stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}