package com.example.ms_envios.service;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.mapper.EnvioMapper;
import com.example.ms_envios.model.Envio;
import com.example.ms_envios.repository.EnvioRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    private static final Logger log = LoggerFactory.getLogger(EnvioService.class);

        @Autowired
        private EnvioRepository envioRepository;
    @Autowired
        private EnvioMapper envioMapper;

    public List<EnvioDTO> listar() {
        log.info("Listando envios");
        return envioRepository.findAll().stream().map(envioMapper::toDTO).collect(Collectors.toList());
    }

    public EnvioDTO obtenerPorId(Integer id) {
        log.info("Buscando envio con id {}", id);
        Optional<Envio> optional = envioRepository.findById(id);
        return optional.map(envioMapper::toDTO).orElse(null);
    }

    public EnvioDTO crear(EnvioRequestDTO dto) {
        try {

            Envio envio = envioMapper.toEntity(dto);
            Envio guardado = envioRepository.save(envio);
            log.info("Envio creado con id {}", guardado.getId());
            return envioMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear envio", e);
            return null;
        }
    }

    public EnvioDTO actualizar(Integer id, EnvioRequestDTO dto) {
        try {
            Optional<Envio> optional = envioRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            Envio envio = optional.get();
            envioMapper.updateEntity(envio, dto);
            
            Envio guardado = envioRepository.save(envio);
            log.info("Envio actualizado con id {}", guardado.getId());
            return envioMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar envio", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!envioRepository.existsById(id)) {
            return false;
        }
        envioRepository.deleteById(id);
        log.info("Envio eliminado con id {}", id);
        return true;
    }

    public List<EnvioDTO> buscarNoEntregadosPorRango(LocalDate desde, LocalDate hasta) {
        log.info("Ejecutando consulta personalizada de envios");
        return envioRepository.buscarNoEntregadosPorRango(desde, hasta).stream().map(envioMapper::toDTO).collect(Collectors.toList());
    }

}
