package com.example.ms_usuarios.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "telefono", nullable = false, length = 150)
    private String telefono;

    @Column(name = "direccion_entrega", nullable = false, length = 150)
    private String direccionEntrega;

    @Column(name = "puntos_fidelidad", nullable = false)
    private Integer puntosFidelidad;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Perfil> perfiles;
}
