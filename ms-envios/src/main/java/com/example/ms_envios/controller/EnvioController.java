package com.example.ms_envios.controller;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expone el CRUD y las busquedas del recurso envio.
 */
@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envios", description = "CRUD y busqueda de envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar envios no entregados por rango de fechas")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    public ResponseEntity<List<EnvioDTO>> buscarNoEntregadosPorRango(
            @Parameter(description = "Fecha inicial", example = "2026-06-01") @RequestParam LocalDate desde,
            @Parameter(description = "Fecha final", example = "2026-06-30") @RequestParam LocalDate hasta) {
        return ResponseEntity.ok(envioService.buscarNoEntregadosPorRango(desde, hasta));
    }

    @GetMapping
    @Operation(summary = "Listar todos los envios")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<EnvioDTO>> listar() {
        return ResponseEntity.ok(envioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un envio por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envio encontrado"),
            @ApiResponse(responseCode = "404", description = "Envio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<EnvioDTO> obtenerPorId(
            @Parameter(description = "Id del envio", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(envioService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un envio")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Envio creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Pedido o usuario no encontrado"),
            @ApiResponse(responseCode = "503", description = "Servicio remoto no disponible"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<EnvioDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del envio",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = """
                            {
                              "pedidoId": 1,
                              "usuarioId": 1,
                              "codigoSeguimiento": "ENV-1001",
                              "direccionDestino": "Av. Principal 123",
                              "transportista": "Chilexpress",
                              "costoEnvio": 3990.00,
                              "entregado": false,
                              "fechaEnvio": "2026-06-20",
                              "fechaEstimadaEntrega": "2026-08-25"
                            }
                            """)))
            @Valid @RequestBody EnvioRequestDTO requestDTO) {
        EnvioDTO creado = envioService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envio actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos"),
            @ApiResponse(responseCode = "404", description = "Envio, pedido o usuario no encontrado"),
            @ApiResponse(responseCode = "503", description = "Servicio remoto no disponible"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<EnvioDTO> actualizar(
            @Parameter(description = "Id del envio", example = "1") @PathVariable Integer id,
            @Valid @RequestBody EnvioRequestDTO requestDTO) {
        return ResponseEntity.ok(envioService.actualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un envio")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Envio eliminado"),
            @ApiResponse(responseCode = "404", description = "Envio no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del envio", example = "1") @PathVariable Integer id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
