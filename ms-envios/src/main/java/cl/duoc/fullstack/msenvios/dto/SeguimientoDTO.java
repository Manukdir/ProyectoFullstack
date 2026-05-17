package cl.duoc.fullstack.msenvios.dto;

import java.time.LocalDateTime;

public class SeguimientoDTO {

    private Integer id;

    private Integer envioId;

    private String ubicacionActual;

    private String estado;

    private Integer ordenEvento;

    private boolean visibleCliente;

    private LocalDateTime fechaEvento;

    private String comentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnvioId() {
        return envioId;
    }

    public void setEnvioId(Integer envioId) {
        this.envioId = envioId;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getOrdenEvento() {
        return ordenEvento;
    }

    public void setOrdenEvento(Integer ordenEvento) {
        this.ordenEvento = ordenEvento;
    }

    public boolean isVisibleCliente() {
        return visibleCliente;
    }

    public void setVisibleCliente(boolean visibleCliente) {
        this.visibleCliente = visibleCliente;
    }

    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDateTime fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
