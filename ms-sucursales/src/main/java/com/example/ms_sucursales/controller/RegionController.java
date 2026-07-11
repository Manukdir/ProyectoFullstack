package com.example.ms_sucursales.controller;

import com.example.ms_sucursales.dto.RegionDTO;
import com.example.ms_sucursales.dto.RegionRequestDTO;
import com.example.ms_sucursales.hateoas.RegionModelAssembler;
import com.example.ms_sucursales.service.RegionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionModelAssembler regionModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RegionDTO>>> listar() {
        List<EntityModel<RegionDTO>> regiones = regionService.listar()
                .stream()
                .map(regionModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(regiones));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RegionDTO>> obtenerPorId(@PathVariable Integer id) {
        RegionDTO dto = regionService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regionModelAssembler.toModel(dto));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RegionDTO>> crear(@Valid @RequestBody RegionRequestDTO requestDTO) {
        RegionDTO creado = regionService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(regionModelAssembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RegionDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody RegionRequestDTO requestDTO) {
        RegionDTO actualizado = regionService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regionModelAssembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = regionService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
