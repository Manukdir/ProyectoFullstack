package com.example.ms_proveedores.hateoas;

import com.example.ms_proveedores.controller.ProveedorController;
import com.example.ms_proveedores.controller.ProveedorHateoasController;
import com.example.ms_proveedores.dto.ProveedorDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<ProveedorDTO, EntityModel<ProveedorDTO>> {

    @Override
    public EntityModel<ProveedorDTO> toModel(ProveedorDTO proveedor) {
        return EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorHateoasController.class).obtenerConLinks(proveedor.getId())).withSelfRel(),
                linkTo(methodOn(ProveedorHateoasController.class).listarConLinks()).withRel("proveedores"),
                linkTo(ProveedorController.class).slash(proveedor.getId()).withRel("crud"));
    }
}
