package com.example.ms_sucursales.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.ms_sucursales.controller.SucursalController;
import com.example.ms_sucursales.dto.SucursalDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<SucursalDTO, EntityModel<SucursalDTO>> {

    @Override
    public EntityModel<SucursalDTO> toModel(SucursalDTO dto) {
        EntityModel<SucursalDTO> model = EntityModel.of(dto);

        if (dto.getId() != null) {
            model.add(linkTo(methodOn(SucursalController.class).obtenerPorId(dto.getId())).withSelfRel());
        }

        model.add(linkTo(methodOn(SucursalController.class).listar()).withRel("sucursales"));
        return model;
    }
}
