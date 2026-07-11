package com.example.ms_usuarios.hateoas;

import com.example.ms_usuarios.controller.PerfilController;
import com.example.ms_usuarios.dto.PerfilDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PerfilModelAssembler implements RepresentationModelAssembler<PerfilDTO, EntityModel<PerfilDTO>> {

    @Override
    public EntityModel<PerfilDTO> toModel(PerfilDTO perfil) {
        return EntityModel.of(perfil,
                linkTo(methodOn(PerfilController.class).obtenerPorId(perfil.getId())).withSelfRel(),
                linkTo(methodOn(PerfilController.class).listar()).withRel("perfiles"));
    }
}
