package com.example.ms_envios.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que registra un cambio de estado o ubicación del envío.
 */
@Entity
@Table(name = "seguimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "envio_id", nullable = false)
    private Integer envioId;

    @Column(name = "estado", nullable = false, length = 150)
    private String estado;

    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    @Column(name = "ubicacion", nullable = false, length = 150)
    private String ubicacion;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "visible_cliente", nullable = false)
    private Boolean visibleCliente;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDateTime fechaEvento;
}
