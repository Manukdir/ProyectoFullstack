package com.example.ms_productos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String codigoAlterno;
    private Integer prioridadVisualizacion;
    private boolean activo = true;
    private LocalDate fechaCreacion;
}