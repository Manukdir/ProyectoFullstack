package com.example.ms_pagos.controller;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Muestra pagos con enlaces HATEOAS.
 */
@RestController
@RequestMapping("/api/v1/hateoas/pagos")
@Tag(name = "Pagos HATEOAS")
public class PagoHateoasController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    @Operation(summary = "Listar pagos con enlaces")
    public List<EntityModel<PagoDTO>> listar() {
        return pagoService.listar().stream()
                .map(this::agregarEnlaces)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pago con enlaces")
    public EntityModel<PagoDTO> obtenerPorId(@PathVariable Integer id) {
        return agregarEnlaces(pagoService.obtenerPorId(id));
    }

    private EntityModel<PagoDTO> agregarEnlaces(PagoDTO pago) {
        Link self = linkTo(methodOn(PagoHateoasController.class)
                .obtenerPorId(pago.getId())).withSelfRel();
        Link lista = linkTo(methodOn(PagoHateoasController.class)
                .listar()).withRel("pagos");
        return EntityModel.of(pago, self, lista);
    }
}
