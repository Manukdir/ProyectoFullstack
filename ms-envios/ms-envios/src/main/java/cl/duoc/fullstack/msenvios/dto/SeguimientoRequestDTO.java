package cl.duoc.fullstack.msenvios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class SeguimientoRequestDTO {

    @Positive(message = "El valor debe ser positivo")
    private Integer envioId;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 100, message = "Debe tener entre 3 y 100 caracteres")
    private String ubicacionActual;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 40, message = "Debe tener entre 3 y 40 caracteres")
    private String estado;

    @Min(value = 1, message = "El valor minimo es 1")
    private Integer ordenEvento;

    private boolean visibleCliente;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime fechaEvento;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 5, max = 150, message = "Debe tener entre 5 y 150 caracteres")
    private String comentario;

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
