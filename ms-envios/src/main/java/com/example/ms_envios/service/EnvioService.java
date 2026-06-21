package com.example.ms_envios.service;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.mapper.EnvioMapper;
import com.example.ms_envios.model.Envio;
import com.example.ms_envios.repository.EnvioRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contiene las reglas de negocio y operaciones principales de los envíos.
 */
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
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id " + id));
        return envioMapper.toDTO(envio);
    }

    public EnvioDTO crear(EnvioRequestDTO dto) {
        Envio envio = envioMapper.toEntity(dto);
        Envio guardado = envioRepository.save(envio);
        log.info("Envío creado con id {}", guardado.getId());
        return envioMapper.toDTO(guardado);
    }

    public EnvioDTO actualizar(Integer id, EnvioRequestDTO dto) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con id " + id));
        envioMapper.updateEntity(envio, dto);
        Envio guardado = envioRepository.save(envio);
        log.info("Envío actualizado con id {}", guardado.getId());
        return envioMapper.toDTO(guardado);
    }

    public boolean eliminar(Integer id) {
        if (!envioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Envío no encontrado con id " + id);
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
