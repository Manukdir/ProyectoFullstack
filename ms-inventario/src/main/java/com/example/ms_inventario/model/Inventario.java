package com.example.ms_inventario.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "inventarios")
@Data
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigoBodega;
    private String nombreBodega;
    private Integer capacidadTotal;
    private boolean activo = true;
    private LocalDate fechaApertura;

    private Integer productoId;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientoStock> movimientos;
}