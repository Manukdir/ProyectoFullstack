package com.example.ms_sucursales.controller;

import com.example.ms_sucursales.dto.SucursalDTO;
import com.example.ms_sucursales.dto.SucursalRequestDTO;
import com.example.ms_sucursales.hateoas.SucursalModelAssembler;
import com.example.ms_sucursales.service.SucursalService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler sucursalModelAssembler;

    @GetMapping("/por-region")
        public ResponseEntity<List<SucursalDTO>> buscarPorNombreRegion(@RequestParam String nombreRegion) {
            return ResponseEntity.ok(sucursalService.buscarPorNombreRegion(nombreRegion));
        }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<SucursalDTO>>> listar() {
        List<EntityModel<SucursalDTO>> sucursales = sucursalService.listar()
                .stream()
                .map(sucursalModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(sucursales));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SucursalDTO>> obtenerPorId(@PathVariable Integer id) {
        SucursalDTO dto = sucursalService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalModelAssembler.toModel(dto));
    }

    @PostMapping
    public ResponseEntity<EntityModel<SucursalDTO>> crear(@Valid @RequestBody SucursalRequestDTO requestDTO) {
        SucursalDTO creado = sucursalService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalModelAssembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<SucursalDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody SucursalRequestDTO requestDTO) {
        SucursalDTO actualizado = sucursalService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursalModelAssembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = sucursalService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
