package com.example.ms_empleados.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.ms_empleados.controller.EmpleadoController;
import com.example.ms_empleados.dto.EmpleadoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoModelAssembler implements RepresentationModelAssembler<EmpleadoDTO, EntityModel<EmpleadoDTO>> {

    @Override
    public EntityModel<EmpleadoDTO> toModel(EmpleadoDTO dto) {
        EntityModel<EmpleadoDTO> model = EntityModel.of(dto);

        if (dto.getId() != null) {
            model.add(linkTo(methodOn(EmpleadoController.class).obtenerPorId(dto.getId())).withSelfRel());
        }

        model.add(linkTo(methodOn(EmpleadoController.class).listar()).withRel("empleados"));
        return model;
    }
}
