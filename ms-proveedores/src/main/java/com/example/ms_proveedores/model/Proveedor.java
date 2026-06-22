package com.example.ms_proveedores.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "rut", nullable = false, length = 150)
    private String rut;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "monto_compras", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoCompras;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Contrato> contratos;
}
