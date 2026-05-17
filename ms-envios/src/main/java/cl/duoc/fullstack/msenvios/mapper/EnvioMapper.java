package cl.duoc.fullstack.msenvios.mapper;

import cl.duoc.fullstack.msenvios.dto.EnvioDTO;
import cl.duoc.fullstack.msenvios.dto.EnvioRequestDTO;
import cl.duoc.fullstack.msenvios.model.Envio;

public class EnvioMapper {

    private EnvioMapper() {
    }

    public static EnvioDTO toDTO(Envio envio) {
        EnvioDTO dto = new EnvioDTO();
        dto.setId(envio.getId());
        dto.setPedidoId(envio.getPedidoId());
        dto.setUsuarioId(envio.getUsuarioId());
        dto.setCodigoEnvio(envio.getCodigoEnvio());
        dto.setDireccionEntrega(envio.getDireccionEntrega());
        dto.setCostoEnvio(envio.getCostoEnvio());
        dto.setEntregado(envio.isEntregado());
        dto.setFechaEnvio(envio.getFechaEnvio());
        dto.setEstadoEnvio(envio.getEstadoEnvio());
        return dto;
    }

    public static Envio toEntity(EnvioRequestDTO request) {
        Envio envio = new Envio();
        envio.setPedidoId(request.getPedidoId());
        envio.setUsuarioId(request.getUsuarioId());
        envio.setCodigoEnvio(request.getCodigoEnvio());
        envio.setDireccionEntrega(request.getDireccionEntrega());
        envio.setCostoEnvio(request.getCostoEnvio());
        envio.setEntregado(request.isEntregado());
        envio.setFechaEnvio(request.getFechaEnvio());
        envio.setEstadoEnvio(request.getEstadoEnvio());
        return envio;
    }
}
