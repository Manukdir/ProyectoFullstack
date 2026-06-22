package com.example.ms_productos.controller;

import com.example.ms_productos.dto.request.ProductoRequestDTO;
import com.example.ms_productos.dto.response.ProductoResponseDTO;
import com.example.ms_productos.service.ProductoService;
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
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
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
    public ResponseEntity<EntityModel<ProductoResponseDTO>> buscarPorId(@PathVariable Integer id) {
        ProductoResponseDTO producto = productoService.buscarPorId(id);


        producto.removeLinks();
        producto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).buscarPorId(id)).withSelfRel());
        producto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoController.class).listarTodos()).withRel("productos"));

        return ResponseEntity.ok(EntityModel.of(producto));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoRequestDTO dto) {
        return new ResponseEntity<>(productoService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombreYPrecio(
            @RequestParam String nombre,
            @RequestParam Double precio) {
        return ResponseEntity.ok(productoService.buscarPorNombreYPrecio(nombre, precio));
    }
}