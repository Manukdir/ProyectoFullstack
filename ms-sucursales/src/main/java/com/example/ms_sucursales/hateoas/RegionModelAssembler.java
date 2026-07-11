package com.example.ms_sucursales.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.ms_sucursales.controller.RegionController;
import com.example.ms_sucursales.dto.RegionDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class RegionModelAssembler implements RepresentationModelAssembler<RegionDTO, EntityModel<RegionDTO>> {

    @Override
    public EntityModel<RegionDTO> toModel(RegionDTO dto) {
        EntityModel<RegionDTO> model = EntityModel.of(dto);

        if (dto.getId() != null) {
            model.add(linkTo(methodOn(RegionController.class).obtenerPorId(dto.getId())).withSelfRel());
        }

        model.add(linkTo(methodOn(RegionController.class).listar()).withRel("regiones"));
        return model;
    }
}
