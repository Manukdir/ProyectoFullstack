package com.example.ms_envios.mapper;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.model.Envio;

import org.springframework.stereotype.Component;

@Component
public class EnvioMapper {

    public EnvioDTO toDTO(Envio envio) {
        if (envio == null) {
            return null;
        }
        EnvioDTO dto = new EnvioDTO();
        dto.setId(envio.getId());
                dto.setPedidoId(envio.getPedidoId());
        dto.setUsuarioId(envio.getUsuarioId());
        dto.setCodigoSeguimiento(envio.getCodigoSeguimiento());
        dto.setDireccionDestino(envio.getDireccionDestino());
        dto.setTransportista(envio.getTransportista());
        dto.setCostoEnvio(envio.getCostoEnvio());
        dto.setEntregado(envio.getEntregado());
        dto.setFechaEnvio(envio.getFechaEnvio());
        dto.setFechaEstimadaEntrega(envio.getFechaEstimadaEntrega());
        return dto;
    }

    public Envio toEntity(EnvioRequestDTO dto) {
        Envio envio = new Envio();
        updateEntity(envio, dto);
        
        return envio;
    }

    public void updateEntity(Envio envio, EnvioRequestDTO dto) {
                envio.setPedidoId(dto.getPedidoId());
        envio.setUsuarioId(dto.getUsuarioId());
        envio.setCodigoSeguimiento(dto.getCodigoSeguimiento());
        envio.setDireccionDestino(dto.getDireccionDestino());
        envio.setTransportista(dto.getTransportista());
        envio.setCostoEnvio(dto.getCostoEnvio());
        envio.setEntregado(dto.getEntregado());
        envio.setFechaEnvio(dto.getFechaEnvio());
        envio.setFechaEstimadaEntrega(dto.getFechaEstimadaEntrega());
    }
}
