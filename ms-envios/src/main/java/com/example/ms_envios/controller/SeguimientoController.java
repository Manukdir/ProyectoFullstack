package com.example.ms_envios.controller;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.service.SeguimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Administra los eventos que permiten seguir el recorrido de un envio.
 */
@RestController
@RequestMapping("/api/v1/seguimientos")
@Tag(name = "Seguimientos", description = "CRUD de eventos de seguimiento")
public class SeguimientoController {

    private final SeguimientoService seguimientoService;

    public SeguimientoController(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    @GetMapping
    @Operation(summary = "Listar seguimientos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<SeguimientoDTO>> listar() {
        return ResponseEntity.ok(seguimientoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un seguimiento por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seguimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Seguimiento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<SeguimientoDTO> obtenerPorId(
            @Parameter(description = "Id del seguimiento", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(seguimientoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un seguimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Seguimiento creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Envio asociado no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<SeguimientoDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del seguimiento",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = """
                            {
                              "envioId": 1,
                              "estado": "EN_TRANSITO",
                              "descripcion": "Pedido recibido en centro de distribucion",
                              "ubicacion": "Santiago",
                              "orden": 1,
                              "visibleCliente": true,
                              "fechaEvento": "2026-06-20T15:30:00"
                            }
                            """)))
            @Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        SeguimientoDTO creado = seguimientoService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un seguimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seguimiento actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Seguimiento o envio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<SeguimientoDTO> actualizar(
            @Parameter(description = "Id del seguimiento", example = "1") @PathVariable Integer id,
            @Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        return ResponseEntity.ok(seguimientoService.actualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un seguimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Seguimiento eliminado"),
            @ApiResponse(responseCode = "404", description = "Seguimiento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del seguimiento", example = "1") @PathVariable Integer id) {
        seguimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
