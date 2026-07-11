package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.PerfilDTO;
import com.example.ms_usuarios.dto.PerfilRequestDTO;
import com.example.ms_usuarios.exception.ResourceNotFoundException;
import com.example.ms_usuarios.mapper.PerfilMapper;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.repository.PerfilRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    public PerfilService(PerfilRepository perfilRepository, PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
    }

    public List<PerfilDTO> listar() {
        return perfilRepository.findAll().stream().map(perfilMapper::toDTO).toList();
    }

    public PerfilDTO obtenerPorId(Integer id) {
        return perfilMapper.toDTO(buscarEntidad(id));
    }

    public PerfilDTO crear(PerfilRequestDTO dto) {
        return perfilMapper.toDTO(perfilRepository.save(perfilMapper.toEntity(dto)));
    }

    public PerfilDTO actualizar(Integer id, PerfilRequestDTO dto) {
        Perfil perfil = buscarEntidad(id);
        perfilMapper.updateEntity(perfil, dto);
        return perfilMapper.toDTO(perfilRepository.save(perfil));
    }

    public void eliminar(Integer id) {
        Perfil perfil = buscarEntidad(id);
        perfilRepository.delete(perfil);
    }

    public Perfil buscarEntidad(Integer id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id " + id));
    }
}
