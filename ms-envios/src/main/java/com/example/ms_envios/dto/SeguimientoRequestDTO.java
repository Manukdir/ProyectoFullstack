package com.example.ms_envios.dto;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Evento de seguimiento asociado a un envío")
public class SeguimientoRequestDTO {

    @Schema(description = "Identificador del envío", example = "1")
        @NotNull(message = "El id de envio es obligatorio")
        @Positive(message = "El id de envio debe ser positivo")
        private Integer envioId;
    @Schema(description = "Estado del evento", example = "EN_TRANSITO")
    @NotBlank(message = "El campo estado es obligatorio")
        @Size(min = 2, max = 150, message = "El campo estado debe tener entre 2 y 150 caracteres")
        private String estado;
    @Schema(description = "Descripción del evento", example = "Pedido recibido en centro de distribución")
    @NotBlank(message = "El campo descripcion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo descripcion debe tener entre 2 y 150 caracteres")
        private String descripcion;
    @Schema(description = "Ubicación actual", example = "Santiago")
    @NotBlank(message = "El campo ubicacion es obligatorio")
        @Size(min = 2, max = 150, message = "El campo ubicacion debe tener entre 2 y 150 caracteres")
        private String ubicacion;
    @Schema(description = "Orden del evento", example = "1")
    @NotNull(message = "El campo orden es obligatorio")
        @Min(value = 0, message = "El campo orden no puede ser negativo")
        private Integer orden;
    @Schema(description = "Indica si el cliente puede ver el evento", example = "true")
    @NotNull(message = "El campo visibleCliente es obligatorio")
        private Boolean visibleCliente;
    @Schema(description = "Fecha y hora del evento", example = "2026-06-20T15:30:00")
    @NotNull(message = "El campo fechaEvento es obligatorio")
        @PastOrPresent(message = "El campo fechaEvento no puede ser futuro")
        private LocalDateTime fechaEvento;
}
