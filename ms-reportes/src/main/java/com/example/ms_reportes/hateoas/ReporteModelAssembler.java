package com.example.ms_reportes.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.ms_reportes.controller.ReporteController;
import com.example.ms_reportes.dto.ReporteDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<ReporteDTO, EntityModel<ReporteDTO>> {

    @Override
    public EntityModel<ReporteDTO> toModel(ReporteDTO dto) {
        EntityModel<ReporteDTO> model = EntityModel.of(dto);

        if (dto.getId() != null) {
            model.add(linkTo(methodOn(ReporteController.class).obtenerPorId(dto.getId())).withSelfRel());
        }

        model.add(linkTo(methodOn(ReporteController.class).listar()).withRel("reportes"));
        return model;
    }
}
