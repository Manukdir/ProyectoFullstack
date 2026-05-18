package com.example.ms_productos.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProductoRequestDTO {

    @NotBlank(message = "el nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El SKU es obligatorio")
    private String codigoSku;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "la fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser en el futuro")
    private LocalDate fechaIngreso;

    private boolean disponible = true;

    @NotNull(message = "el ID de la categoría es obligatorio")
    private Integer categoriaId;
}