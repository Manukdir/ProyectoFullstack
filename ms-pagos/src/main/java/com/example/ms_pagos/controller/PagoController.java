package com.example.ms_pagos.controller;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints CRUD para administrar pagos.
 */
@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones para administrar pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping("/buscar")
    @Operation(summary = "Buscar pagos por monto y estado")
    public ResponseEntity<List<PagoDTO>> buscarPorMontoYEstado(
            @RequestParam BigDecimal montoMinimo,
            @RequestParam String estadoPago) {
        return ResponseEntity.ok(pagoService.buscarPorMontoYEstado(montoMinimo, estadoPago));
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos")
    public ResponseEntity<List<PagoDTO>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pago por su id")
    public ResponseEntity<PagoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un pago")
    public ResponseEntity<PagoDTO> crear(@Valid @RequestBody PagoRequestDTO requestDTO) {
        PagoDTO creado = pagoService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pago")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody PagoRequestDTO requestDTO) {
        return ResponseEntity.ok(pagoService.actualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
