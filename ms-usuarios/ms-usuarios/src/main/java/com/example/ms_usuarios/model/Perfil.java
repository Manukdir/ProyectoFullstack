package com.example.ms_usuarios.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "perfiles")
@Data
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombre;
    private String descripcion;
    private Integer nivelAcceso;
    private boolean habilitado = true;
    private LocalDate fechaCreacion;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}