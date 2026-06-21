package com.example.ms_sucursales.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "regiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "codigo", nullable = false, length = 150)
    private String codigo;

    @Column(name = "cantidad_comunas", nullable = false)
    private Integer cantidadComunas;

    @Column(name = "costo_despacho_base", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoDespachoBase;

    @Column(name = "activa", nullable = false)
    private Boolean activa;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Sucursal> sucursales = new ArrayList<>();

}
