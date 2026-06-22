package com.example.ms_proveedores.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contratos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "codigo_contrato", nullable = false, length = 150)
    private String codigoContrato;

    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    @Column(name = "duracion_meses", nullable = false)
    private Integer duracionMeses;

    @Column(name = "monto_mensual", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoMensual;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_termino", nullable = false)
    private LocalDate fechaTermino;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
}
