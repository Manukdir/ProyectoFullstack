package com.example.ms_sucursales.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Entity
@Table(name = "sucursales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Region region;

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
