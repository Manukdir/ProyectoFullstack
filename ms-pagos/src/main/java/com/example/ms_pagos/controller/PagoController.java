package com.example.ms_pagos.controller;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para administrar pagos.
 */
@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones para administrar pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar pagos por monto y estado")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    public ResponseEntity<List<PagoDTO>> buscarPorMontoYEstado(
            @Parameter(description = "Monto minimo del pago", example = "1000.00") @RequestParam BigDecimal montoMinimo,
            @Parameter(description = "Estado del pago", example = "APROBADO") @RequestParam String estadoPago) {
        return ResponseEntity.ok(pagoService.buscarPorMontoYEstado(montoMinimo, estadoPago));
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<PagoDTO>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pago por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<PagoDTO> obtenerPorId(
            @Parameter(description = "Id del pago", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un pago")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pago creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Pedido asociado no encontrado"),
            @ApiResponse(responseCode = "503", description = "Servicio de pedidos no disponible"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<PagoDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del pago",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = """
                            {
                              "pedidoId": 1,
                              "metodoPago": "TARJETA",
                              "estadoPago": "APROBADO",
                              "codigoTransaccion": "TRX-1001",
                              "numeroCuotas": 3,
                              "monto": 45990.00,
                              "pagado": true,
                              "fechaPago": "2026-06-20T10:30:00"
                            }
                            """)))
            @Valid @RequestBody PagoRequestDTO requestDTO) {
        PagoDTO creado = pagoService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pago")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pago actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Pago o pedido no encontrado"),
            @ApiResponse(responseCode = "503", description = "Servicio de pedidos no disponible"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<PagoDTO> actualizar(
            @Parameter(description = "Id del pago", example = "1") @PathVariable Integer id,
            @Valid @RequestBody PagoRequestDTO requestDTO) {
        return ResponseEntity.ok(pagoService.actualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pago eliminado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del pago", example = "1") @PathVariable Integer id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
