package com.example.ms_usuarios.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "perfiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "nombre_perfil", nullable = false, length = 150)
    private String nombrePerfil;

    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    @Column(name = "nivel_acceso", nullable = false)
    private Integer nivelAcceso;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "permisos_especiales", nullable = false)
    private Boolean permisosEspeciales;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
