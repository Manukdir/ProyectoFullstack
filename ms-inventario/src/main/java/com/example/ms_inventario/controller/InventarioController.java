package com.example.ms_inventario.controller;

import com.example.ms_inventario.dto.request.InventarioRequestDTO;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import com.example.ms_inventario.service.InventarioService;
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
public class InventarioController {

    private final InventarioService inventarioService;

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

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioResponseDTO>> buscarPorId(@PathVariable Integer id) {
        InventarioResponseDTO inventario = inventarioService.buscarPorId(id);

        inventario.removeLinks();
        inventario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).buscarPorId(id)).withSelfRel());
        inventario.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InventarioController.class).listarTodos()).withRel("inventarios"));

        return ResponseEntity.ok(EntityModel.of(inventario));
    }

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> guardar(@Valid @RequestBody InventarioRequestDTO dto) {
        return new ResponseEntity<>(inventarioService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.ok(inventarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}