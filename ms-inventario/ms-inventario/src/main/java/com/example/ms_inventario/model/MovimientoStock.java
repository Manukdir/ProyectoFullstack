package com.example.ms_inventario.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos_stock")
@Data
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipoMovimiento;
    private String motivo;
    private Integer cantidad;
    private boolean aprobado = true;
    private LocalDate fechaMovimiento;

    private Integer productoId;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;
}