package com.example.ms_productos.controller;

import com.example.ms_productos.dto.external.InventarioDTO;
import com.example.ms_productos.dto.request.ProductoRequestDTO;
import com.example.ms_productos.dto.response.ProductoResponseDTO;
import com.example.ms_productos.hateoas.ProductoModelAssembler;
import com.example.ms_productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
 * Expone el CRUD de productos.
 * Cada respuesta incluye enlaces HATEOAS (_links) hacia si misma y hacia
 * recursos relacionados, ademas de estar documentada con Swagger/OpenAPI.
 */
@Tag(name = "Productos", description = "Gestion del catalogo de productos")
@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoModelAssembler assembler;

    @Operation(summary = "Listar todos los productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                            {
                              "_embedded": {
                                "productoResponseDTOList": [
                                  {
                                    "id": 1,
                                    "nombre": "Notebook Gamer",
                                    "codigoSku": "SKU-1001",
                                    "precio": 1500000.0,
                                    "stock": 10,
                                    "fechaIngreso": "2026-06-20",
                                    "disponible": true,
                                    "categoriaId": 1,
                                    "categoriaNombre": "Electronica",
                                    "_links": { "self": { "href": "http://localhost:8082/api/v1/productos/1" } }
                                  }
                                ]
                              },
                              "_links": { "self": { "href": "http://localhost:8082/api/v1/productos" } }
                            }
                            """)))
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProductoResponseDTO>>> listarTodos() {
        List<EntityModel<ProductoResponseDTO>> productos = productoService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ProductoResponseDTO>> collectionModel = CollectionModel.of(productos,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Buscar un producto por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                            {
                              "id": 1,
                              "nombre": "Notebook Gamer",
                              "codigoSku": "SKU-1001",
                              "precio": 1500000.0,
                              "stock": 10,
                              "fechaIngreso": "2026-06-20",
                              "disponible": true,
                              "categoriaId": 1,
                              "categoriaNombre": "Electronica",
                              "_links": {
                                "self": { "href": "http://localhost:8082/api/v1/productos/1" },
                                "productos": { "href": "http://localhost:8082/api/v1/productos" }
                              }
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = """
                            { "error": "Producto no encontrado con ID: 99" }
                            """)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductoResponseDTO>> buscarPorId(
            @Parameter(description = "ID del producto a buscar") @PathVariable Integer id) {
        ProductoResponseDTO producto = productoService.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en la solicitud"),
            @ApiResponse(responseCode = "404", description = "La categoria indicada no existe")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoRequestDTO dto) {
        return new ResponseEntity<>(productoService.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un producto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en la solicitud"),
            @ApiResponse(responseCode = "404", description = "Producto o categoria no encontrados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un producto por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar productos por nombre (parcial) y precio maximo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busqueda realizada correctamente")
    })
    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombreYPrecio(
            @Parameter(description = "Texto a buscar dentro del nombre") @RequestParam String nombre,
            @Parameter(description = "Precio maximo (excluyente)") @RequestParam Double precio) {
        return ResponseEntity.ok(productoService.buscarPorNombreYPrecio(nombre, precio));
    }

    @Operation(summary = "Consultar el inventario (bodegas y stock) de un producto",
            description = "Comunicacion entre microservicios via OpenFeign: este endpoint llama " +
                    "internamente a ms-inventario para traer las bodegas donde existe stock del producto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente " +
                    "(la lista puede venir vacia si ms-inventario no tiene registros o no esta disponible)"),
            @ApiResponse(responseCode = "404", description = "El producto no existe en ms-productos")
    })
    @GetMapping("/{id}/inventario")
    public ResponseEntity<List<InventarioDTO>> buscarInventarioDeProducto(
            @Parameter(description = "ID del producto") @PathVariable Integer id) {
        return ResponseEntity.ok(productoService.buscarInventarioDeProducto(id));
    }
}
