package com.example.ms_inventario.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioRequestDTO {

        @NotNull(message = "El id de producto es obligatorio")
        @Positive(message = "El id de producto debe ser positivo")
        private Integer productoId;
    @NotBlank(message = "El campo codigoBodega es obligatorio")
        @Size(min = 2, max = 150, message = "El campo codigoBodega debe tener entre 2 y 150 caracteres")
        private String codigoBodega;
    @NotBlank(message = "El campo ubicacion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo ubicacion debe tener entre 2 y 150 caracteres")
        private String ubicacion;
    @NotNull(message = "El campo cantidadDisponible es obligatorio")
        @Min(value = 0, message = "El campo cantidadDisponible no puede ser negativo")
        private Integer cantidadDisponible;
    @NotNull(message = "El campo cantidadMinima es obligatorio")
        @Min(value = 0, message = "El campo cantidadMinima no puede ser negativo")
        private Integer cantidadMinima;
    @NotNull(message = "El campo activo es obligatorio")
        private Boolean activo;
    @NotNull(message = "El campo fechaActualizacion es obligatorio")
        @PastOrPresent(message = "El campo fechaActualizacion no puede ser futuro")
        private LocalDateTime fechaActualizacion;
}
