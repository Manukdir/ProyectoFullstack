package com.example.ms_reportes.controller;

import com.example.ms_reportes.dto.ReporteDTO;
import com.example.ms_reportes.dto.ReporteRequestDTO;
import com.example.ms_reportes.hateoas.ReporteModelAssembler;
import com.example.ms_reportes.service.ReporteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ReporteModelAssembler reporteModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ReporteDTO>>> listar() {
        List<EntityModel<ReporteDTO>> reportes = reporteService.listar()
                .stream()
                .map(reporteModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(reportes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReporteDTO>> obtenerPorId(@PathVariable Integer id) {
        ReporteDTO dto = reporteService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reporteModelAssembler.toModel(dto));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ReporteDTO>> crear(@Valid @RequestBody ReporteRequestDTO requestDTO) {
        ReporteDTO creado = reporteService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteModelAssembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ReporteDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody ReporteRequestDTO requestDTO) {
        ReporteDTO actualizado = reporteService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reporteModelAssembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = reporteService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/consolidado")
    public ResponseEntity<EntityModel<ReporteDTO>> generarReporteConsolidado() {
        ReporteDTO reporteGenerado = reporteService.generarReporteConsolidado();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reporteModelAssembler.toModel(reporteGenerado));
    }
}
