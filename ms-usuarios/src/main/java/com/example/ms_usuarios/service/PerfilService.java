package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.PerfilDTO;
import com.example.ms_usuarios.dto.PerfilRequestDTO;
import com.example.ms_usuarios.mapper.PerfilMapper;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.PerfilRepository;
import com.example.ms_usuarios.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private static final Logger log = LoggerFactory.getLogger(PerfilService.class);

        @Autowired
        private PerfilRepository perfilRepository;
    @Autowired
        private PerfilMapper perfilMapper;
    @Autowired
        private UsuarioRepository usuarioRepository;

    public List<PerfilDTO> listar() {
        log.info("Listando perfils");
        return perfilRepository.findAll().stream().map(perfilMapper::toDTO).collect(Collectors.toList());
    }

    public PerfilDTO obtenerPorId(Integer id) {
        log.info("Buscando perfil con id {}", id);
        Optional<Perfil> optional = perfilRepository.findById(id);
        return optional.map(perfilMapper::toDTO).orElse(null);
    }

    public PerfilDTO crear(PerfilRequestDTO dto) {
        try {
                        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
                        if (usuario == null) {
                            return null;
                        }

            Perfil perfil = perfilMapper.toEntity(dto, usuario);
            Perfil guardado = perfilRepository.save(perfil);
            log.info("Perfil creado con id {}", guardado.getId());
            return perfilMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear perfil", e);
            return null;
        }
    }

    public PerfilDTO actualizar(Integer id, PerfilRequestDTO dto) {
        try {
            Optional<Perfil> optional = perfilRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
                        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
                        if (usuario == null) {
                            return null;
                        }
            Perfil perfil = optional.get();
            perfilMapper.updateEntity(perfil, dto);
            perfil.setUsuario(usuario);
            Perfil guardado = perfilRepository.save(perfil);
            log.info("Perfil actualizado con id {}", guardado.getId());
            return perfilMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar perfil", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!perfilRepository.existsById(id)) {
            return false;
        }
        perfilRepository.deleteById(id);
        log.info("Perfil eliminado con id {}", id);
        return true;
    }


}
