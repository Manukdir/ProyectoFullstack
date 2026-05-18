package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.mapper.UsuarioMapper;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

        @Autowired
        private UsuarioRepository usuarioRepository;
    @Autowired
        private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> listar() {
        log.info("Listando usuarios");
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Integer id) {
        log.info("Buscando usuario con id {}", id);
        Optional<Usuario> optional = usuarioRepository.findById(id);
        return optional.map(usuarioMapper::toDTO).orElse(null);
    }

    public UsuarioDTO crear(UsuarioRequestDTO dto) {
        try {
            

            Usuario usuario = usuarioMapper.toEntity(dto);
            Usuario guardado = usuarioRepository.save(usuario);
            log.info("Usuario creado con id {}", guardado.getId());
            return usuarioMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear usuario", e);
            return null;
        }
    }

    public UsuarioDTO actualizar(Integer id, UsuarioRequestDTO dto) {
        try {
            Optional<Usuario> optional = usuarioRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            
            Usuario usuario = optional.get();
            usuarioMapper.updateEntity(usuario, dto);
            
            Usuario guardado = usuarioRepository.save(usuario);
            log.info("Usuario actualizado con id {}", guardado.getId());
            return usuarioMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar usuario", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        log.info("Usuario eliminado con id {}", id);
        return true;
    }

    public List<UsuarioDTO> buscarPorEmailActivo(String email) {
        log.info("Ejecutando consulta personalizada de usuarios");
        return usuarioRepository.findByEmailAndActivoTrue(email).stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }


}
