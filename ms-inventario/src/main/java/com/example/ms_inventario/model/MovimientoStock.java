package com.example.ms_inventario.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimientos_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "tipo_movimiento", nullable = false, length = 150)
    private String tipoMovimiento;

    @Column(name = "motivo", nullable = false, length = 150)
    private String motivo;

    @Column(name = "documento_referencia", nullable = false, length = 150)
    private String documentoReferencia;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "costo_movimiento", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoMovimiento;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;
}
