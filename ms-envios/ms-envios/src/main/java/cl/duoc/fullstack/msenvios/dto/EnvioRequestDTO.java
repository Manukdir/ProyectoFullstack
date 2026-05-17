package cl.duoc.fullstack.msenvios.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class EnvioRequestDTO {

    @Positive(message = "El valor debe ser positivo")
    private Integer pedidoId;

    @Positive(message = "El valor debe ser positivo")
    private Integer usuarioId;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 40, message = "Debe tener entre 3 y 40 caracteres")
    private String codigoEnvio;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 5, max = 150, message = "Debe tener entre 5 y 150 caracteres")
    private String direccionEntrega;

    @Min(value = 0, message = "El valor minimo es 0")
    private Integer costoEnvio;

    private boolean entregado;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaEnvio;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 40, message = "Debe tener entre 3 y 40 caracteres")
    private String estadoEnvio;

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    public void setCodigoEnvio(String codigoEnvio) {
        this.codigoEnvio = codigoEnvio;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Integer getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Integer costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }
}
