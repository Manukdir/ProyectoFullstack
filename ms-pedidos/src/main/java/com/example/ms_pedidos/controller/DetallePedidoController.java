package com.example.ms_pedidos.controller;

import com.example.ms_pedidos.dto.DetallePedidoDTO;
import com.example.ms_pedidos.dto.DetallePedidoRequestDTO;
import com.example.ms_pedidos.service.DetallePedidoService;
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
 * Administra los productos o detalles que pertenecen a un pedido.
 */
@RestController
@RequestMapping("/api/v1/detalles-pedido")
@Tag(name = "Detalles de pedido", description = "CRUD de productos asociados a pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;


    @GetMapping
    @Operation(summary = "Listar detalles de pedido")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<DetallePedidoDTO>> listar() {
        return ResponseEntity.ok(detallePedidoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un detalle por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<DetallePedidoDTO> obtenerPorId(
            @Parameter(description = "Id del detalle", example = "1") @PathVariable Integer id) {
        DetallePedidoDTO dto = detallePedidoService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un detalle de pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Detalle creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Pedido relacionado no encontrado")
    })
    public ResponseEntity<DetallePedidoDTO> crear(@Valid @RequestBody DetallePedidoRequestDTO requestDTO) {
        DetallePedidoDTO creado = detallePedidoService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un detalle de pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalle actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Detalle o pedido no encontrado")
    })
    public ResponseEntity<DetallePedidoDTO> actualizar(
            @Parameter(description = "Id del detalle", example = "1") @PathVariable Integer id,
            @Valid @RequestBody DetallePedidoRequestDTO requestDTO) {
        DetallePedidoDTO actualizado = detallePedidoService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un detalle de pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Detalle eliminado"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del detalle", example = "1") @PathVariable Integer id) {
        boolean eliminado = detallePedidoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
