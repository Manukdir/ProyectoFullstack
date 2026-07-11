package com.example.ms_envios.controller;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.hateoas.EnvioModelAssembler;
import com.example.ms_envios.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Agrega enlaces HATEOAS a las respuestas de envios.
 */
@RestController
@RequestMapping("/api/v1/hateoas/envios")
@Tag(name = "Envios HATEOAS", description = "Envios con enlaces de navegacion")
public class EnvioHateoasController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    public EnvioHateoasController(EnvioService envioService, EnvioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Listar envios con enlaces HATEOAS")
    public ResponseEntity<CollectionModel<EntityModel<EnvioDTO>>> listarConLinks() {
        return ResponseEntity.ok(assembler.toCollectionModel(envioService.listar()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un envio con enlaces HATEOAS")
    public ResponseEntity<EntityModel<EnvioDTO>> obtenerConLinks(@PathVariable Integer id) {
        return ResponseEntity.ok(assembler.toModel(envioService.obtenerPorId(id)));
    }
}
