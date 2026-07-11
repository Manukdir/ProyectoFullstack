package com.example.ms_empleados.controller;

import com.example.ms_empleados.dto.EmpleadoDTO;
import com.example.ms_empleados.dto.EmpleadoRequestDTO;
import com.example.ms_empleados.hateoas.EmpleadoModelAssembler;
import com.example.ms_empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoModelAssembler empleadoModelAssembler;

    @GetMapping("/buscar")
        public ResponseEntity<List<EmpleadoDTO>> buscarPorSucursalYAnio(@RequestParam Integer sucursalId, @RequestParam Integer anio) {
            return ResponseEntity.ok(empleadoService.buscarPorSucursalYAnio(sucursalId, anio));
        }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<EmpleadoDTO>>> listar() {
        List<EntityModel<EmpleadoDTO>> empleados = empleadoService.listar()
                .stream()
                .map(empleadoModelAssembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(empleados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmpleadoDTO>> obtenerPorId(@PathVariable Integer id) {
        EmpleadoDTO dto = empleadoService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoModelAssembler.toModel(dto));
    }

    @PostMapping
    public ResponseEntity<EntityModel<EmpleadoDTO>> crear(@Valid @RequestBody EmpleadoRequestDTO requestDTO) {
        EmpleadoDTO creado = empleadoService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoModelAssembler.toModel(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EmpleadoDTO>> actualizar(@PathVariable Integer id, @Valid @RequestBody EmpleadoRequestDTO requestDTO) {
        EmpleadoDTO actualizado = empleadoService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoModelAssembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = empleadoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
