package com.example.ms_pagos.hateoas;

import com.example.ms_pagos.controller.PagoHateoasController;
import com.example.ms_pagos.dto.PagoDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PagoModelAssembler implements RepresentationModelAssembler<PagoDTO, EntityModel<PagoDTO>> {

    @Override
    public EntityModel<PagoDTO> toModel(PagoDTO pago) {
        return EntityModel.of(pago,
                linkTo(methodOn(PagoHateoasController.class).obtenerPorId(pago.getId())).withSelfRel(),
                linkTo(methodOn(PagoHateoasController.class).listar()).withRel("pagos"));
    }

    @Override
    public CollectionModel<EntityModel<PagoDTO>> toCollectionModel(Iterable<? extends PagoDTO> pagos) {
        CollectionModel<EntityModel<PagoDTO>> collection = RepresentationModelAssembler.super.toCollectionModel(pagos);
        collection.add(linkTo(methodOn(PagoHateoasController.class).listar()).withSelfRel());
        return collection;
    }
}
