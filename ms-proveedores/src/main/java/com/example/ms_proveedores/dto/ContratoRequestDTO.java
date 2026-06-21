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
@Schema(description = "Datos de un contrato asociado a un proveedor")
public class ContratoRequestDTO {

    @Schema(description = "Identificador del proveedor", example = "1")
        @NotNull(message = "El id de proveedor es obligatorio")
        @Positive(message = "El id de proveedor debe ser positivo")
        private Integer proveedorId;
    @Schema(description = "Código del contrato", example = "CONT-2026-001")
    @NotBlank(message = "El campo codigoContrato es obligatorio")
        @Size(min = 2, max = 150, message = "El campo codigoContrato debe tener entre 2 y 150 caracteres")
        private String codigoContrato;
    @Schema(description = "Descripción del contrato", example = "Suministro anual de productos")
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @Schema(description = "Duración en meses", example = "12")
    @NotNull(message = "El campo duracionMeses es obligatorio")
        @Min(value = 0, message = "El campo duracionMeses no puede ser negativo")
        private Integer duracionMeses;
    @Schema(description = "Monto mensual", example = "350000.00")
    @NotNull(message = "El campo montoMensual es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo montoMensual debe ser mayor que cero")
        private BigDecimal montoMensual;
    @Schema(description = "Indica si el contrato está activo", example = "true")
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @Schema(description = "Fecha de inicio", example = "2026-06-20")
    @NotNull(message = "El campo fechaInicio es obligatorio")
        @PastOrPresent(message = "El campo fechaInicio no puede ser futura")
        private LocalDate fechaInicio;
    @Schema(description = "Fecha de término", example = "2027-06-20")
    @NotNull(message = "El campo fechaTermino es obligatorio")
        @Future(message = "El campo fechaTermino debe ser futura")
        private LocalDate fechaTermino;
}
