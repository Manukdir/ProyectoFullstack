package com.example.ms_productos.controller;

import com.example.ms_productos.dto.request.CategoriaRequestDTO;
import com.example.ms_productos.dto.response.CategoriaResponseDTO;
import com.example.ms_productos.service.CategoriaService;
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

/**
 * Expone el CRUD de categorias.
 * Igual que ProductoController, cada respuesta agrega enlaces HATEOAS.
 */
@Tag(name = "Categorias", description = "Gestion de categorias de productos")
@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Listar todas las categorias")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CategoriaResponseDTO>>> listarTodos() {
        List<EntityModel<CategoriaResponseDTO>> categorias = categoriaService.listarTodos().stream()
                .map(categoria -> {
                    categoria.removeLinks();
                    categoria.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(CategoriaController.class).buscarPorId(categoria.getId())
                    ).withSelfRel());
                    return EntityModel.of(categoria);
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<CategoriaResponseDTO>> collectionModel = CollectionModel.of(categorias,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Buscar una categoria por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoriaResponseDTO>> buscarPorId(
            @Parameter(description = "ID de la categoria a buscar") @PathVariable Integer id) {
        CategoriaResponseDTO categoria = categoriaService.buscarPorId(id);

        categoria.removeLinks();
        categoria.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).buscarPorId(id)).withSelfRel());
        categoria.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).listarTodos()).withRel("categorias"));

        return ResponseEntity.ok(EntityModel.of(categoria));
    }

    @Operation(summary = "Crear una nueva categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> guardar(@Valid @RequestBody CategoriaRequestDTO dto) {
        return new ResponseEntity<>(categoriaService.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una categoria existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en la solicitud"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar una categoria por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
