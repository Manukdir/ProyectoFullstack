package com.example.ms_inventario.controller;

import com.example.ms_inventario.dto.request.MovimientoStockRequestDTO;
import com.example.ms_inventario.dto.response.MovimientoStockResponseDTO;
import com.example.ms_inventario.service.MovimientoStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos-stock")
@RequiredArgsConstructor
@Tag(name = "Movimientos de Stock Controller", description = "Endpoints para el registro, auditoría y filtrado de flujos de inventario (Ingresos y Egresos)")
public class MovimientoStockController {

    private final MovimientoStockService movimientoStockService;

    @Operation(summary = "Listar todos los movimientos de stock", description = "Recupera una lista histórica completa con todas las transacciones de inventario registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista histórica obtenida exitosamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovimientoStockResponseDTO.class))))
    })
    @GetMapping
    public ResponseEntity<List<MovimientoStockResponseDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoStockService.listarTodos());
    }

    @Operation(summary = "Buscar movimiento por ID", description = "Obtiene los detalles específicos de una transacción de stock utilizando su ID numérico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento de stock encontrado de forma exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoStockResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún registro con el ID proporcionado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoStockResponseDTO> buscarPorId(
            @Parameter(description = "ID numérico del movimiento a consultar", example = "10", required = true)
            @PathVariable Integer id) {
        return ResponseEntity.ok(movimientoStockService.buscarPorId(id));
    }

    @Operation(summary = "Registrar un nuevo movimiento de stock", description = "Registra una entrada (INGRESO) o salida (EGRESO) física de mercadería, alterando el flujo del inventario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimiento procesado y guardado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoStockResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Estructura de payload incorrecta o faltan campos requeridos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MovimientoStockResponseDTO> crearMovimiento(@Valid @RequestBody MovimientoStockRequestDTO dto) {
        return new ResponseEntity<>(movimientoStockService.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un movimiento existente", description = "Modifica los datos históricos o auditoría de un movimiento específico localizándolo por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoStockResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontró el registro para actualizar", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoStockResponseDTO> actualizar(
            @Parameter(description = "ID numérico del movimiento a modificar", example = "10", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody MovimientoStockRequestDTO dto) {
        return ResponseEntity.ok(movimientoStockService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un registro de movimiento", description = "Borra lógicamente o remueve del historial el movimiento de stock identificado por el ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimiento removido exitosamente de la base de datos", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontró el registro que se desea eliminar", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID numérico del movimiento a eliminar", example = "10", required = true)
            @PathVariable Integer id) {
        movimientoStockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filtrar movimientos por tipo", description = "Filtra la lista de transacciones aprobadas basándose únicamente en su clasificación operativa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultados filtrados devueltos exitosamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovimientoStockResponseDTO.class))))
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<MovimientoStockResponseDTO>> buscarPorTipo(
            @Parameter(description = "Clasificación de la operación a filtrar", example = "INGRESO", required = true)
            @RequestParam String tipoMovimiento) {
        return ResponseEntity.ok(movimientoStockService.buscarPorTipoYAprobado(tipoMovimiento));
    }
}
