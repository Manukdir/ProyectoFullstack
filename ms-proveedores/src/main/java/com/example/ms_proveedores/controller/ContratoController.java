package com.example.ms_proveedores.controller;

import com.example.ms_proveedores.dto.ContratoDTO;
import com.example.ms_proveedores.dto.ContratoRequestDTO;
import com.example.ms_proveedores.service.ContratoService;
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
 * Administra los contratos asociados a los proveedores.
 */
@RestController
@RequestMapping("/api/v1/contratos")
@Tag(name = "Contratos", description = "CRUD de contratos de proveedores")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;


    @GetMapping
    @Operation(summary = "Listar contratos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<ContratoDTO>> listar() {
        return ResponseEntity.ok(contratoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un contrato por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrato encontrado"),
            @ApiResponse(responseCode = "404", description = "Contrato no encontrado")
    })
    public ResponseEntity<ContratoDTO> obtenerPorId(
            @Parameter(description = "Id del contrato", example = "1") @PathVariable Integer id) {
        ContratoDTO dto = contratoService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrato creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Proveedor relacionado no encontrado")
    })
    public ResponseEntity<ContratoDTO> crear(@Valid @RequestBody ContratoRequestDTO requestDTO) {
        ContratoDTO creado = contratoService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrato actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Contrato o proveedor no encontrado")
    })
    public ResponseEntity<ContratoDTO> actualizar(
            @Parameter(description = "Id del contrato", example = "1") @PathVariable Integer id,
            @Valid @RequestBody ContratoRequestDTO requestDTO) {
        ContratoDTO actualizado = contratoService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un contrato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrato eliminado"),
            @ApiResponse(responseCode = "404", description = "Contrato no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del contrato", example = "1") @PathVariable Integer id) {
        boolean eliminado = contratoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
