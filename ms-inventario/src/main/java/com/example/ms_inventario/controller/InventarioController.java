package com.example.ms_inventario.controller;

import com.example.ms_inventario.dto.request.InventarioRequestDTO;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import com.example.ms_inventario.hateoas.InventarioModelAssembler;
import com.example.ms_inventario.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/inventarios")
@RequiredArgsConstructor
@Tag(name = "Inventario Controller", description = "Endpoints para la gestión, control físico y asignación de bodegas de almacenamiento")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioModelAssembler assembler;

    @Operation(summary = "Listar todos los inventarios", description = "Recupera una lista con todos los inventarios de las bodegas disponibles, incluyendo enlaces hipermedia (HATEOAS).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inventarios obtenida exitosamente", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<InventarioResponseDTO>>> listarTodos() {
        List<EntityModel<InventarioResponseDTO>> inventarios = inventarioService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<InventarioResponseDTO>> collectionModel = CollectionModel.of(inventarios,
                org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo(
                        org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn(InventarioController.class)
                                .listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Buscar inventario por ID", description = "Obtiene los detalles de un registro de inventario específico mediante su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado de forma exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InventarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún inventario con el ID proporcionado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioResponseDTO>> buscarPorId(
            @Parameter(description = "ID numérico del inventario a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        InventarioResponseDTO inventario = inventarioService.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(inventario));
    }

    @Operation(summary = "Buscar inventarios por producto", description = "Endpoint usado por ms-productos mediante OpenFeign.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<InventarioResponseDTO>> buscarPorProductoId(@PathVariable Integer productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    @Operation(summary = "Registrar un nuevo inventario", description = "Crea un registro de inventario en una bodega específica. Realiza la validación remota de la existencia del producto mediante OpenFeign.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado y registrado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InventarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Estructura de datos inválida o validación del producto fallida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EntityModel<InventarioResponseDTO>> guardar(@Valid @RequestBody InventarioRequestDTO dto) {
        InventarioResponseDTO inventario = inventarioService.guardar(dto);
        return new ResponseEntity<>(assembler.toModel(inventario), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un inventario existente", description = "Modifica los datos de una bodega o la asignación de un inventario existente localizando el registro mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InventarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada incorrectos o inconsistentes", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontró el inventario que se desea actualizar", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioResponseDTO>> actualizar(
            @Parameter(description = "ID numérico del inventario a modificar", example = "1", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody InventarioRequestDTO dto) {

        InventarioResponseDTO inventario = inventarioService.actualizar(id, dto);
        return ResponseEntity.ok(assembler.toModel(inventario));
    }

    @Operation(summary = "Eliminar un registro de inventario", description = "Borra físicamente del sistema el registro de inventario identificado por el ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado exitosamente (Sin contenido de retorno)", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontró el inventario que se desea eliminar", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID numérico del inventario a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
