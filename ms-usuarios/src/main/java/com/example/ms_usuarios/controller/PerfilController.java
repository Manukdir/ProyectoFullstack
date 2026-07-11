package com.example.ms_usuarios.controller;

import com.example.ms_usuarios.dto.PerfilDTO;
import com.example.ms_usuarios.dto.PerfilRequestDTO;
import com.example.ms_usuarios.hateoas.PerfilModelAssembler;
import com.example.ms_usuarios.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/perfiles")
@Tag(name = "Perfiles", description = "CRUD de perfiles de usuario")
public class PerfilController {

    private final PerfilService perfilService;
    private final PerfilModelAssembler assembler;

    public PerfilController(PerfilService perfilService, PerfilModelAssembler assembler) {
        this.perfilService = perfilService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar perfiles")
    public ResponseEntity<CollectionModel<EntityModel<PerfilDTO>>> listar() {
        List<EntityModel<PerfilDTO>> perfiles = perfilService.listar().stream()
                .map(assembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(perfiles,
                linkTo(methodOn(PerfilController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un perfil por id")
    public ResponseEntity<EntityModel<PerfilDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(perfilService.obtenerPorId(id)));
    }

    @PostMapping
    @Operation(summary = "Crear un perfil")
    public ResponseEntity<EntityModel<PerfilDTO>> crear(@Valid @RequestBody PerfilRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(perfilService.crear(dto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un perfil")
    public ResponseEntity<EntityModel<PerfilDTO>> actualizar(@PathVariable Integer id,
                                                             @Valid @RequestBody PerfilRequestDTO dto) {
        return ResponseEntity.ok(assembler.toModel(perfilService.actualizar(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un perfil")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        perfilService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
