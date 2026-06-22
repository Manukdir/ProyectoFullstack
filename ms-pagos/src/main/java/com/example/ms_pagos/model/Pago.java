package com.example.ms_pagos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un pago en MySQL.
 */
@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pedido_id", nullable = false)
    private Integer pedidoId;

    @Column(name = "metodo_pago", nullable = false, length = 150)
    private String metodoPago;

    @Column(name = "estado_pago", nullable = false, length = 150)
    private String estadoPago;

    @Column(name = "codigo_transaccion", nullable = false, length = 150)
    private String codigoTransaccion;

    @Column(name = "numero_cuotas", nullable = false)
    private Integer numeroCuotas;

    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;
}
