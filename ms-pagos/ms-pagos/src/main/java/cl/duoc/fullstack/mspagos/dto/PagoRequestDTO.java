package cl.duoc.fullstack.mspagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoRequestDTO {

    @Positive(message = "El valor debe ser positivo")
    private Integer pedidoId;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 40, message = "Debe tener entre 3 y 40 caracteres")
    private String codigoTransaccion;

    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0.0")
    private BigDecimal monto;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 40, message = "Debe tener entre 3 y 40 caracteres")
    private String metodoPago;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 3, max = 40, message = "Debe tener entre 3 y 40 caracteres")
    private String estadoPago;

    private boolean confirmado;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime fechaPago;

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}
