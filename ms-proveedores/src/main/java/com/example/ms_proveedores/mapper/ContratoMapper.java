package com.example.ms_proveedores.mapper;

import com.example.ms_proveedores.dto.ContratoDTO;
import com.example.ms_proveedores.dto.ContratoRequestDTO;
import com.example.ms_proveedores.model.Contrato;
import com.example.ms_proveedores.model.Proveedor;
import org.springframework.stereotype.Component;

/**
 * Convierte contratos y mantiene la relación con su proveedor.
 */
@Component
public class ContratoMapper {

    public ContratoDTO toDTO(Contrato contrato) {
        if (contrato == null) {
            return null;
        }
        ContratoDTO dto = new ContratoDTO();
        dto.setId(contrato.getId());
                if (contrato.getProveedor() != null) {
                    dto.setProveedorId(contrato.getProveedor().getId());
                }
        dto.setCodigoContrato(contrato.getCodigoContrato());
        dto.setDescripcion(contrato.getDescripcion());
        dto.setDuracionMeses(contrato.getDuracionMeses());
        dto.setMontoMensual(contrato.getMontoMensual());
        dto.setActivo(contrato.getActivo());
        dto.setFechaInicio(contrato.getFechaInicio());
        dto.setFechaTermino(contrato.getFechaTermino());
        return dto;
    }

    public Contrato toEntity(ContratoRequestDTO dto, Proveedor proveedor) {
        Contrato contrato = new Contrato();
        updateEntity(contrato, dto);
        contrato.setProveedor(proveedor);
        return contrato;
    }

    public void updateEntity(Contrato contrato, ContratoRequestDTO dto) {
                contrato.setCodigoContrato(dto.getCodigoContrato());
        contrato.setDescripcion(dto.getDescripcion());
        contrato.setDuracionMeses(dto.getDuracionMeses());
        contrato.setMontoMensual(dto.getMontoMensual());
        contrato.setActivo(dto.getActivo());
        contrato.setFechaInicio(dto.getFechaInicio());
        contrato.setFechaTermino(dto.getFechaTermino());
    }
}
