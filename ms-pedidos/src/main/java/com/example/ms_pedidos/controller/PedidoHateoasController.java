package com.example.ms_pedidos.controller;

import com.example.ms_pedidos.dto.PedidoDTO;
import com.example.ms_pedidos.service.PedidoService;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/hateoas/pedidos")
public class PedidoHateoasController {

    private final PedidoService pedidoService;

    public PedidoHateoasController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PedidoDTO>>> listarConLinks() {
        List<EntityModel<PedidoDTO>> pedidos = pedidoService.listar().stream()
                .map(this::agregarLinks)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoHateoasController.class).listarConLinks()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PedidoDTO>> obtenerConLinks(@PathVariable Integer id) {
        PedidoDTO pedido = pedidoService.obtenerPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agregarLinks(pedido));
    }

    private EntityModel<PedidoDTO> agregarLinks(PedidoDTO pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoHateoasController.class).obtenerConLinks(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoHateoasController.class).listarConLinks()).withRel("pedidos"),
                linkTo(PedidoController.class).slash(pedido.getId()).withRel("crud"));
    }
}
