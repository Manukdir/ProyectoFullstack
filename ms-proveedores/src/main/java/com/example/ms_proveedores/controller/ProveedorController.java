package com.example.ms_proveedores.controller;

import com.example.ms_proveedores.dto.ProveedorDTO;
import com.example.ms_proveedores.dto.ProveedorRequestDTO;
import com.example.ms_proveedores.service.ProveedorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;


    @GetMapping("/activos")
        public ResponseEntity<List<ProveedorDTO>> buscarProveedoresActivos() {
            return ResponseEntity.ok(proveedorService.buscarProveedoresActivos());
        }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listar() {
        return ResponseEntity.ok(proveedorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Integer id) {
        ProveedorDTO dto = proveedorService.obtenerPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@Valid @RequestBody ProveedorRequestDTO requestDTO) {
        ProveedorDTO creado = proveedorService.crear(requestDTO);
        if (creado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ProveedorRequestDTO requestDTO) {
        ProveedorDTO actualizado = proveedorService.actualizar(id, requestDTO);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = proveedorService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
