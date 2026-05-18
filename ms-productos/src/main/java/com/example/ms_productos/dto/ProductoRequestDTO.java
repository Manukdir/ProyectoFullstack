package com.example.ms_productos.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

        @NotNull(message = "El id de categoria es obligatorio")
        @Positive(message = "El id de categoria debe ser positivo")
        private Integer categoriaId;
    @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @NotBlank(message = "El campo sku es obligatorio")
        @Size(min = 2, max = 150, message = "El campo sku debe tener entre 2 y 150 caracteres")
        private String sku;
    @NotNull(message = "El campo precio es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo precio debe ser mayor que cero")
        private BigDecimal precio;
    @NotNull(message = "El campo stock es obligatorio")
        @Min(value = 0, message = "El campo stock no puede ser negativo")
        private Integer stock;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaIngreso es obligatorio")
        @PastOrPresent(message = "El campo fechaIngreso no puede ser futura")
        private LocalDate fechaIngreso;
}
