package cl.duoc.fullstack.mspedidos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetallePedidoRequestDTO {

    @Positive(message = "El valor debe ser positivo")
    private Integer pedidoId;

    @Positive(message = "El valor debe ser positivo")
    private Integer productoId;

    @NotBlank(message = "El campo es obligatorio")
    @Size(min = 2, max = 100, message = "Debe tener entre 2 y 100 caracteres")
    private String nombreProducto;

    @Positive(message = "El valor debe ser positivo")
    private Integer cantidad;

    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0.0")
    private BigDecimal precioUnitario;

    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0.0")
    private BigDecimal subtotal;

    private boolean activo;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDateTime fechaRegistro;

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
