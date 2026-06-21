package com.example.ms_proveedores.controller;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.dto.ProveedorRequestDTO;
import com.example.ms_proveedores.service.ProveedorService;
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
 * Expone las operaciones REST para administrar proveedores.
 */
@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "CRUD y consultas de proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;


    @GetMapping("/activos")
    @Operation(summary = "Listar proveedores activos")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
        public ResponseEntity<List<ProveedorDTO>> buscarProveedoresActivos() {
            return ResponseEntity.ok(proveedorService.buscarProveedoresActivos());
        }

    @GetMapping
    @Operation(summary = "Listar todos los proveedores")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<ProveedorDTO>> listar() {
        return ResponseEntity.ok(proveedorService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un proveedor por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<ProveedorDTO> obtenerPorId(
            @Parameter(description = "Id del proveedor", example = "1") @PathVariable Integer id) {
        ProveedorDTO dto = proveedorService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear un proveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proveedor creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ProveedorDTO> crear(@Valid @RequestBody ProveedorRequestDTO requestDTO) {
        ProveedorDTO creado = proveedorService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<ProveedorDTO> actualizar(
            @Parameter(description = "Id del proveedor", example = "1") @PathVariable Integer id,
            @Valid @RequestBody ProveedorRequestDTO requestDTO) {
        ProveedorDTO actualizado = proveedorService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Proveedor eliminado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Id del proveedor", example = "1") @PathVariable Integer id) {
        boolean eliminado = proveedorService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
