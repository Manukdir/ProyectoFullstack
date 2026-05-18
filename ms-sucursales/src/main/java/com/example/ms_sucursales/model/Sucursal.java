package com.example.ms_sucursales.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sucursales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "region_id", nullable = false)
    private Integer regionId;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @Column(name = "capacidad_bodega", nullable = false)
    private Integer capacidadBodega;

    @Column(name = "ventas_mensuales", nullable = false, precision = 12, scale = 2)
    private BigDecimal ventasMensuales;

    @Column(name = "activa", nullable = false)
    private Boolean activa;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;
}
