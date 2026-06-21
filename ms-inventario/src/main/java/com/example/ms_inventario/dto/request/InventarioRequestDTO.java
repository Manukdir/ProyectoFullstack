package com.example.ms_inventario.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class InventarioRequestDTO {

    @NotBlank(message = "El codigo de bodega es obligatorio")
    private String codigoBodega;

    @NotBlank(message = "El nombre de la bodega es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombreBodega;

    @NotNull(message = "La capacidad total es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidadTotal;

    private boolean activo = true;

    @NotNull(message = "La fecha de apertura es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaApertura;


    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productoId;
}