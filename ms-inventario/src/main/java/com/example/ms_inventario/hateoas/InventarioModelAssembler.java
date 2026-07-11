package com.example.ms_inventario.hateoas;

import com.example.ms_inventario.controller.InventarioController;
import com.example.ms_inventario.dto.response.InventarioResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<InventarioResponseDTO, EntityModel<InventarioResponseDTO>> {

    @Override
    public EntityModel<InventarioResponseDTO> toModel(InventarioResponseDTO inventario) {
        inventario.removeLinks();
        inventario.add(linkTo(methodOn(InventarioController.class).buscarPorId(inventario.getId())).withSelfRel());
        inventario.add(linkTo(methodOn(InventarioController.class).listarTodos()).withRel("inventarios"));
        return EntityModel.of(inventario);
    }
}
