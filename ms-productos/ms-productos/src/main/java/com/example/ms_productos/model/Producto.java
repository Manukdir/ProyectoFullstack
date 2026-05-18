package com.example.ms_productos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String codigoSku;
    private Double precio;
    private Integer stock;
    private LocalDate fechaIngreso;
    private boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}