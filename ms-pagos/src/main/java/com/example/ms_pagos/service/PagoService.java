package com.example.ms_pagos.service;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.mapper.PagoMapper;
import com.example.ms_pagos.model.Pago;
import com.example.ms_pagos.repository.PagoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

        @Autowired
        private PagoRepository pagoRepository;
    @Autowired
        private PagoMapper pagoMapper;

    public List<PagoDTO> listar() {
        log.info("Listando pagos");
        return pagoRepository.findAll().stream().map(pagoMapper::toDTO).collect(Collectors.toList());
    }

    public PagoDTO obtenerPorId(Integer id) {
        log.info("Buscando pago con id {}", id);
        Optional<Pago> optional = pagoRepository.findById(id);
        return optional.map(pagoMapper::toDTO).orElse(null);
    }

    public PagoDTO crear(PagoRequestDTO dto) {
        try {

            Pago pago = pagoMapper.toEntity(dto);
            Pago guardado = pagoRepository.save(pago);
            log.info("Pago creado con id {}", guardado.getId());
            return pagoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al crear pago", e);
            return null;
        }
    }

    public PagoDTO actualizar(Integer id, PagoRequestDTO dto) {
        try {
            Optional<Pago> optional = pagoRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            Pago pago = optional.get();
            pagoMapper.updateEntity(pago, dto);
            
            Pago guardado = pagoRepository.save(pago);
            log.info("Pago actualizado con id {}", guardado.getId());
            return pagoMapper.toDTO(guardado);
        } catch (Exception e) {
            log.error("Error al actualizar pago", e);
            return null;
        }
    }

    public boolean eliminar(Integer id) {
        if (!pagoRepository.existsById(id)) {
            return false;
        }
        pagoRepository.deleteById(id);
        log.info("Pago eliminado con id {}", id);
        return true;
    }

    public List<PagoDTO> buscarPorMontoYEstado(BigDecimal montoMinimo, String estadoPago) {
        log.info("Ejecutando consulta personalizada de pagos");
        return pagoRepository.buscarPorMontoYEstado(montoMinimo, estadoPago).stream().map(pagoMapper::toDTO).collect(Collectors.toList());
    }

}
