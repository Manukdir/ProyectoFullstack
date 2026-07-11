package com.example.ms_envios.hateoas;

import com.example.ms_envios.controller.EnvioController;
import com.example.ms_envios.controller.EnvioHateoasController;
import com.example.ms_envios.dto.EnvioDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<EnvioDTO, EntityModel<EnvioDTO>> {

    @Override
    public EntityModel<EnvioDTO> toModel(EnvioDTO envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioHateoasController.class).obtenerConLinks(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioHateoasController.class).listarConLinks()).withRel("envios"),
                linkTo(EnvioController.class).slash(envio.getId()).withRel("crud"));
    }

    @Override
    public CollectionModel<EntityModel<EnvioDTO>> toCollectionModel(Iterable<? extends EnvioDTO> envios) {
        CollectionModel<EntityModel<EnvioDTO>> collection = RepresentationModelAssembler.super.toCollectionModel(envios);
        collection.add(linkTo(methodOn(EnvioHateoasController.class).listarConLinks()).withSelfRel());
        return collection;
    }
}
