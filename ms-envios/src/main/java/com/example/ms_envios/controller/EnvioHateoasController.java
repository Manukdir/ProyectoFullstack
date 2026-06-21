package com.example.ms_envios.controller;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.service.EnvioService;
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
@RequestMapping("/api/v1/hateoas/envios")
public class EnvioHateoasController {

    private final EnvioService envioService;

    public EnvioHateoasController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<EnvioDTO>>> listarConLinks() {
        List<EntityModel<EnvioDTO>> envios = envioService.listar().stream()
                .map(this::agregarLinks)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(envios,
                linkTo(methodOn(EnvioHateoasController.class).listarConLinks()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EnvioDTO>> obtenerConLinks(@PathVariable Integer id) {
        EnvioDTO envio = envioService.obtenerPorId(id);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agregarLinks(envio));
    }

    private EntityModel<EnvioDTO> agregarLinks(EnvioDTO envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioHateoasController.class).obtenerConLinks(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioHateoasController.class).listarConLinks()).withRel("envios"),
                linkTo(EnvioController.class).slash(envio.getId()).withRel("crud"));
    }
}
