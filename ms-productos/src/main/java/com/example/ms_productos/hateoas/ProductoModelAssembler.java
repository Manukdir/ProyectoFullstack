package com.example.ms_productos.hateoas;

import com.example.ms_productos.controller.ProductoController;
import com.example.ms_productos.dto.response.ProductoResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductoResponseDTO, EntityModel<ProductoResponseDTO>> {

    @Override
    public EntityModel<ProductoResponseDTO> toModel(ProductoResponseDTO producto) {
        producto.removeLinks();
        producto.add(linkTo(methodOn(ProductoController.class).buscarPorId(producto.getId())).withSelfRel());
        producto.add(linkTo(methodOn(ProductoController.class).listarTodos()).withRel("productos"));
        return EntityModel.of(producto);
    }
}
