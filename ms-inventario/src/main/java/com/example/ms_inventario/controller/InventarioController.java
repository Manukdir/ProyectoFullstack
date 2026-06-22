package com.example.ms_inventario.controller;

import com.example.ms_inventario.dto.request.InventarioRequestDTO;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import com.example.ms_inventario.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    @Operation(summary = "Listar todos los inventarios", description = "Recupera una lista con todos los inventarios de las bodegas disponibles, incluyendo enlaces hipermedia (HATEOAS).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inventarios obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<InventarioResponseDTO>>> listarTodos() {
        List<EntityModel<InventarioResponseDTO>> inventarios = inventarioService.listarTodos().stream()
                .map(inventario -> {
                    inventario.removeLinks();
                    inventario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).buscarPorId(inventario.getId())).withSelfRel());
                    return EntityModel.of(inventario);
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<InventarioResponseDTO>> collectionModel = CollectionModel.of(inventarios,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Buscar inventario por ID", description = "Obtiene los detalles de un registro de inventario específico mediante su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado de forma exitosa"),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún inventario con el ID proporcionado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioResponseDTO>> buscarPorId(
            @Parameter(description = "ID numérico del inventario a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        InventarioResponseDTO inventario = inventarioService.buscarPorId(id);

        inventario.removeLinks();
        inventario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).buscarPorId(id)).withSelfRel());
        inventario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).listarTodos()).withRel("inventarios"));

        return ResponseEntity.ok(EntityModel.of(inventario));
    }

    @Operation(summary = "Registrar un nuevo inventario", description = "Crea un registro de inventario en una bodega específica. Realiza la validación remota de la existencia del producto mediante OpenFeign.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado y registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Estructura de datos inválida o validación del producto fallida")
    })
    @PostMapping
    public ResponseEntity<InventarioResponseDTO> guardar(@Valid @RequestBody InventarioRequestDTO dto) {
        return new ResponseEntity<>(inventarioService.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un inventario existente", description = "Modifica los datos de una bodega o la asignación de un inventario existente localizando el registro mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada incorrectos o inconsistentes"),
            @ApiResponse(responseCode = "404", description = "No se encontró el inventario que se desea actualizar")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @Parameter(description = "ID numérico del inventario a modificar", example = "1", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.ok(inventarioService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un registro de inventario", description = "Borra físicamente del sistema el registro de inventario identificado por el ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado exitosamente (Sin contenido de retorno)"),
            @ApiResponse(responseCode = "404", description = "No se encontró el inventario que se desea eliminar")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID numérico del inventario a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}