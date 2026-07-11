package com.example.ms_usuarios.controller;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.hateoas.UsuarioModelAssembler;
import com.example.ms_usuarios.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "CRUD de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioModelAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar usuarios")
    public ResponseEntity<CollectionModel<EntityModel<UsuarioDTO>>> listar() {
        List<EntityModel<UsuarioDTO>> usuarios = usuarioService.listar().stream()
                .map(assembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).listar()).withSelfRel()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un usuario por id")
    public ResponseEntity<EntityModel<UsuarioDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(usuarioService.obtenerPorId(id)));
    }

    @PostMapping
    @Operation(summary = "Crear un usuario")
    public ResponseEntity<EntityModel<UsuarioDTO>> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(usuarioService.crear(dto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario")
    public ResponseEntity<EntityModel<UsuarioDTO>> actualizar(@PathVariable Integer id,
                                                              @Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(assembler.toModel(usuarioService.actualizar(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
