package com.example.ms_envios.controller;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.service.SeguimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Administra los eventos que permiten seguir el recorrido de un envío.
 */
@RestController
@RequestMapping("/api/v1/seguimientos")
@Tag(name = "Seguimientos", description = "CRUD de eventos de seguimiento")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;


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
            @ApiResponse(responseCode = "404", description = "Seguimiento no encontrado")
    })
    public ResponseEntity<SeguimientoDTO> obtenerPorId(
            @Parameter(description = "Id del seguimiento", example = "1") @PathVariable Integer id) {
        SeguimientoDTO dto = seguimientoService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un seguimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Seguimiento creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<SeguimientoDTO> crear(@Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        SeguimientoDTO creado = seguimientoService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un seguimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seguimiento actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Seguimiento no encontrado")
    })
    public ResponseEntity<SeguimientoDTO> actualizar(
            @Parameter(description = "Id del seguimiento", example = "1") @PathVariable Integer id,
            @Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        SeguimientoDTO actualizado = seguimientoService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un seguimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Seguimiento eliminado"),
            @ApiResponse(responseCode = "404", description = "Seguimiento no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del seguimiento", example = "1") @PathVariable Integer id) {
        boolean eliminado = seguimientoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
