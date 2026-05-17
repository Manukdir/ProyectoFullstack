package cl.duoc.fullstack.msenvios.mapper;

import cl.duoc.fullstack.msenvios.dto.SeguimientoDTO;
import cl.duoc.fullstack.msenvios.dto.SeguimientoRequestDTO;
import cl.duoc.fullstack.msenvios.model.Seguimiento;

public class SeguimientoMapper {

    private SeguimientoMapper() {
    }

    public static SeguimientoDTO toDTO(Seguimiento seguimiento) {
        SeguimientoDTO dto = new SeguimientoDTO();
        dto.setId(seguimiento.getId());
        dto.setEnvioId(seguimiento.getEnvio().getId());
        dto.setUbicacionActual(seguimiento.getUbicacionActual());
        dto.setEstado(seguimiento.getEstado());
        dto.setOrdenEvento(seguimiento.getOrdenEvento());
        dto.setVisibleCliente(seguimiento.isVisibleCliente());
        dto.setFechaEvento(seguimiento.getFechaEvento());
        dto.setComentario(seguimiento.getComentario());
        return dto;
    }

    public static Seguimiento toEntity(SeguimientoRequestDTO request) {
        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setUbicacionActual(request.getUbicacionActual());
        seguimiento.setEstado(request.getEstado());
        seguimiento.setOrdenEvento(request.getOrdenEvento());
        seguimiento.setVisibleCliente(request.isVisibleCliente());
        seguimiento.setFechaEvento(request.getFechaEvento());
        seguimiento.setComentario(request.getComentario());
        return seguimiento;
    }
}
