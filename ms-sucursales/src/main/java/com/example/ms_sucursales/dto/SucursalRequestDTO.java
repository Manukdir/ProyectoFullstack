package com.example.ms_sucursales.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalRequestDTO {

        @NotNull(message = "El id de region es obligatorio")
        @Positive(message = "El id de region debe ser positivo")
        private Integer regionId;
    @NotBlank(message = "El campo nombre es obligatorio")
        @Size(min = 2, max = 150, message = "El campo nombre debe tener entre 2 y 150 caracteres")
        private String nombre;
    @NotBlank(message = "El campo direccion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo direccion debe tener entre 2 y 150 caracteres")
        private String direccion;
    @NotNull(message = "El campo capacidadBodega es obligatorio")
        @Min(value = 0, message = "El campo capacidadBodega no puede ser negativo")
        private Integer capacidadBodega;
    @NotNull(message = "El campo ventasMensuales es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El campo ventasMensuales debe ser mayor que cero")
        private BigDecimal ventasMensuales;
    @NotNull(message = "El campo activa es obligatorio")
        private Boolean activa;
    @NotNull(message = "El campo fechaApertura es obligatorio")
        @PastOrPresent(message = "El campo fechaApertura no puede ser futura")
        private LocalDate fechaApertura;
}
