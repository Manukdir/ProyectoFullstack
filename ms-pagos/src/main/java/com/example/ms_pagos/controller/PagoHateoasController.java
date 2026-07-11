package com.example.ms_pagos.controller;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.hateoas.PagoModelAssembler;
import com.example.ms_pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Muestra pagos con enlaces HATEOAS.
 */
@RestController
@RequestMapping("/api/v1/hateoas/pagos")
@Tag(name = "Pagos HATEOAS")
public class PagoHateoasController {

    private final PagoService pagoService;
    private final PagoModelAssembler assembler;

    public PagoHateoasController(PagoService pagoService, PagoModelAssembler assembler) {
        this.pagoService = pagoService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar pagos con enlaces")
    public ResponseEntity<CollectionModel<EntityModel<PagoDTO>>> listar() {
        return ResponseEntity.ok(assembler.toCollectionModel(pagoService.listar()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pago con enlaces")
    public ResponseEntity<EntityModel<PagoDTO>> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(pagoService.obtenerPorId(id)));
    }
}
