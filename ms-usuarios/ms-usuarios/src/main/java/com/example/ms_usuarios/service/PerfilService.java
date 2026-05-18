package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.request.PerfilRequestDTO;
import com.example.ms_usuarios.dto.response.PerfilResponseDTO;
import com.example.ms_usuarios.exception.ResourceNotFoundException;
import com.example.ms_usuarios.mapper.PerfilMapper;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.PerfilRepository;
import com.example.ms_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;

    public List<PerfilResponseDTO> listarTodos() {
        log.info("Listando todos los perfiles");
        return perfilRepository.findAll().stream()
                .map(perfilMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PerfilResponseDTO buscarPorId(Integer id) {
        log.info("Buscando perfil con ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con ID: " + id));
        return perfilMapper.toResponseDTO(perfil);
    }

    public PerfilResponseDTO guardar(PerfilRequestDTO dto) {
        try {
            log.info("Guardando nuevo perfil");
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

            Perfil perfil = perfilMapper.toEntity(dto);
            perfil.setUsuario(usuario);
            return perfilMapper.toResponseDTO(perfilRepository.save(perfil));
        } catch (ResourceNotFoundException e) {
            log.error("Error de validacion al guardar perfil: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar perfil: {}", e.getMessage());
            throw new RuntimeException("Error interno al guardar el perfil");
        }
    }

    public PerfilResponseDTO actualizar(Integer id, PerfilRequestDTO dto) {
        log.info("Actualizando perfil con ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        perfil.setNombre(dto.getNombre());
        perfil.setDescripcion(dto.getDescripcion());
        perfil.setNivelAcceso(dto.getNivelAcceso());
        perfil.setHabilitado(dto.isHabilitado());
        perfil.setFechaCreacion(dto.getFechaCreacion());
        perfil.setUsuario(usuario);

        return perfilMapper.toResponseDTO(perfilRepository.save(perfil));
    }

    public void eliminar(Integer id) {
        log.info("Eliminando perfil con ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con ID: " + id));
        perfilRepository.delete(perfil);
    }
}