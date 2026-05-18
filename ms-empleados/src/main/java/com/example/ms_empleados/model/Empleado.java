package com.example.ms_empleados.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "sucursal_id", nullable = false)
    private Integer sucursalId;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "cargo", nullable = false, length = 150)
    private String cargo;

    @Column(name = "horas_semanales", nullable = false)
    private Integer horasSemanales;

    @Column(name = "sueldo", nullable = false, precision = 12, scale = 2)
    private BigDecimal sueldo;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;
}
