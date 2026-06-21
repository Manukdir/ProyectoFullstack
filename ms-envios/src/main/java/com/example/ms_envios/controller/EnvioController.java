package com.example.ms_envios.controller;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Expone el CRUD y las búsquedas del recurso envío.
 */
@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envíos", description = "CRUD y búsqueda de envíos")
public class EnvioController {

    @Autowired
    private EnvioService envioService;


    @GetMapping("/buscar")
    @Operation(summary = "Buscar envíos no entregados por rango de fechas")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
        public ResponseEntity<List<EnvioDTO>> buscarNoEntregadosPorRango(
                @Parameter(description = "Fecha inicial", example = "2026-06-01") @RequestParam LocalDate desde,
                @Parameter(description = "Fecha final", example = "2026-06-30") @RequestParam LocalDate hasta) {
            return ResponseEntity.ok(envioService.buscarNoEntregadosPorRango(desde, hasta));
        }

    @GetMapping
    @Operation(summary = "Listar todos los envíos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<EnvioDTO>> listar() {
        return ResponseEntity.ok(envioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un envío por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envío encontrado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<EnvioDTO> obtenerPorId(
            @Parameter(description = "Id del envío", example = "1") @PathVariable Integer id) {
        EnvioDTO dto = envioService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un envío")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Envío creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<EnvioDTO> crear(@Valid @RequestBody EnvioRequestDTO requestDTO) {
        EnvioDTO creado = envioService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envío")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envío actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<EnvioDTO> actualizar(
            @Parameter(description = "Id del envío", example = "1") @PathVariable Integer id,
            @Valid @RequestBody EnvioRequestDTO requestDTO) {
        EnvioDTO actualizado = envioService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un envío")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Envío eliminado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del envío", example = "1") @PathVariable Integer id) {
        boolean eliminado = envioService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
