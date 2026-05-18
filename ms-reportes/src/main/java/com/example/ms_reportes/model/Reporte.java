package com.example.ms_reportes.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "tipo_reporte", nullable = false, length = 150)
    private String tipoReporte;

    @Column(name = "resumen", nullable = false, length = 150)
    private String resumen;

    @Column(name = "cantidad_pedidos", nullable = false)
    private Integer cantidadPedidos;

    @Column(name = "total_ventas", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalVentas;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion;
}
