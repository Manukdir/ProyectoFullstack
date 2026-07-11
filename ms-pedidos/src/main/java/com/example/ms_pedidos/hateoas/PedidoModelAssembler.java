package com.example.ms_pedidos.hateoas;

import com.example.ms_pedidos.controller.PedidoController;
import com.example.ms_pedidos.controller.PedidoHateoasController;
import com.example.ms_pedidos.dto.PedidoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>> {

    @Override
    public EntityModel<PedidoDTO> toModel(PedidoDTO pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoHateoasController.class).obtenerConLinks(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoHateoasController.class).listarConLinks()).withRel("pedidos"),
                linkTo(PedidoController.class).slash(pedido.getId()).withRel("crud"));
    }
}
