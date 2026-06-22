package com.example.ms_productos.controller;

import com.example.ms_productos.dto.request.ProductoRequestDTO;
import com.example.ms_productos.dto.response.ProductoResponseDTO;
import com.example.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Mantenimiento del catálogo de productos")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Retorna la colección completa de productos con soporte HATEOAS")
    public ResponseEntity<CollectionModel<EntityModel<ProductoResponseDTO>>> listarTodos() {
        List<EntityModel<ProductoResponseDTO>> productos = productoService.listarTodos().stream()
                .map(producto -> {
                    producto.removeLinks();
                    producto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).buscarPorId(producto.getId())).withSelfRel());
                    return EntityModel.of(producto);
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ProductoResponseDTO>> collectionModel = CollectionModel.of(productos,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).listarTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Recupera un producto específico a partir de su identificador único")
    public ResponseEntity<EntityModel<ProductoResponseDTO>> buscarPorId(@PathVariable Integer id) {
        ProductoResponseDTO producto = productoService.buscarPorId(id);

        producto.removeLinks();
        producto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).buscarPorId(id)).withSelfRel());
        producto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).listarTodos()).withRel("productos"));

        return ResponseEntity.ok(EntityModel.of(producto));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo producto", description = "Crea un producto en la base de datos validando los campos obligatorios")
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoRequestDTO dto) {
        return new ResponseEntity<>(productoService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto existente", description = "Modifica los datos de un producto a partir de su ID")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Remueve de forma física un producto de la base de datos mediante su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrar")
    @Operation(summary = "Filtrar productos", description = "Busca coincidencia exacta o parcial por nombre y precio asignado")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombreYPrecio(
            @RequestParam String nombre,
            @RequestParam Double precio) {
        return ResponseEntity.ok(productoService.buscarPorNombreYPrecio(nombre, precio));
    }
}