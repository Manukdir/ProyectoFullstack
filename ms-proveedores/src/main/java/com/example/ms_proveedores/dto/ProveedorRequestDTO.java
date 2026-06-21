package com.example.ms_proveedores.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos necesarios para crear o actualizar un proveedor")
public class ProveedorRequestDTO {

    @Schema(description = "Nombre del proveedor", example = "Tecnología SpA")
        @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @Schema(description = "RUT del proveedor", example = "76.123.456-7")
    @NotBlank(message = "El campo rut es obligatorio")
        @Size(min = 2, max = 150, message = "El campo rut debe tener entre 2 y 150 caracteres")
        private String rut;
    @Schema(description = "Correo de contacto", example = "ventas@tecnologia.cl")
    @NotBlank(message = "El campo email es obligatorio")
        @Email(message = "El campo email debe tener formato de email")
        @Size(max = 120, message = "El campo email no puede superar 120 caracteres")
        private String email;
    @Schema(description = "Calificación del proveedor", example = "5")
    @NotNull(message = "El campo calificacion es obligatorio")
        @Min(value = 0, message = "El campo calificacion no puede ser negativo")
        private Integer calificacion;
    @Schema(description = "Monto acumulado de compras", example = "1250000.00")
    @NotNull(message = "El campo montoCompras es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo montoCompras debe ser mayor que cero")
        private BigDecimal montoCompras;
    @Schema(description = "Indica si el proveedor está activo", example = "true")
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @Schema(description = "Fecha de registro", example = "2026-06-20")
    @NotNull(message = "El campo fechaRegistro es obligatorio")
        @PastOrPresent(message = "El campo fechaRegistro no puede ser futura")
        private LocalDate fechaRegistro;
}
