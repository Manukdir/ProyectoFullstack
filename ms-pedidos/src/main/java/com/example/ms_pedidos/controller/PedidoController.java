package com.example.ms_pedidos.controller;

import com.example.ms_pedidos.dto.PedidoDTO;
import com.example.ms_pedidos.dto.PedidoRequestDTO;
import com.example.ms_pedidos.service.PedidoService;
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
 * Expone las operaciones REST para crear, consultar, actualizar y eliminar pedidos.
 */
@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "CRUD y consultas de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @GetMapping("/pagados")
    @Operation(summary = "Listar pedidos pagados")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
        public ResponseEntity<List<PedidoDTO>> buscarPedidosPagados() {
            return ResponseEntity.ok(pedidoService.buscarPedidosPagados());
        }

    @GetMapping
    @Operation(summary = "Listar todos los pedidos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<PedidoDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<PedidoDTO> obtenerPorId(
            @Parameter(description = "Id del pedido", example = "1") @PathVariable Integer id) {
        PedidoDTO dto = pedidoService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<PedidoDTO> crear(@Valid @RequestBody PedidoRequestDTO requestDTO) {
        PedidoDTO creado = pedidoService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<PedidoDTO> actualizar(
            @Parameter(description = "Id del pedido", example = "1") @PathVariable Integer id,
            @Valid @RequestBody PedidoRequestDTO requestDTO) {
        PedidoDTO actualizado = pedidoService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del pedido", example = "1") @PathVariable Integer id) {
        boolean eliminado = pedidoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
