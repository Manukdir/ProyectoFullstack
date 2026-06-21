package com.example.ms_proveedores.controller;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.service.ProveedorService;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/hateoas/proveedores")
public class ProveedorHateoasController {

    private final ProveedorService proveedorService;

    public ProveedorHateoasController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProveedorDTO>>> listarConLinks() {
        List<EntityModel<ProveedorDTO>> proveedores = proveedorService.listar().stream()
                .map(this::agregarLinks)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(proveedores,
                linkTo(methodOn(ProveedorHateoasController.class).listarConLinks()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> obtenerConLinks(@PathVariable Integer id) {
        ProveedorDTO proveedor = proveedorService.obtenerPorId(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agregarLinks(proveedor));
    }

    private EntityModel<ProveedorDTO> agregarLinks(ProveedorDTO proveedor) {
        return EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorHateoasController.class).obtenerConLinks(proveedor.getId())).withSelfRel(),
                linkTo(methodOn(ProveedorHateoasController.class).listarConLinks()).withRel("proveedores"),
                linkTo(ProveedorController.class).slash(proveedor.getId()).withRel("crud"));
    }
}
