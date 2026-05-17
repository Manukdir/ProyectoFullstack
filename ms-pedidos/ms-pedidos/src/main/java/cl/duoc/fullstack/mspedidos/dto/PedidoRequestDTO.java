package cl.duoc.fullstack.mspedidos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoRequestDTO {

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 30, message = "Debe tener entre 3 y 30 caracteres")
    private String numeroPedido;

    @Positive(message = "El valor debe ser positivo")
    private Integer clienteId;

    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0.0")
    private BigDecimal total;

    private boolean pagado;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime fechaPedido;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 30, message = "Debe tener entre 3 y 30 caracteres")
    private String estado;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 150, message = "Debe tener entre 3 y 150 caracteres")
    private String observacion;

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
