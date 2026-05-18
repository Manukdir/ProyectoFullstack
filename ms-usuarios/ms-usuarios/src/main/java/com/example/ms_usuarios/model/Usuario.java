package com.example.ms_usuarios.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombreCompleto;
    private String email;
    private Integer puntosAcumulados;
    private boolean activo = true;
    private LocalDate fechaRegistro;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perfil> perfiles;
}