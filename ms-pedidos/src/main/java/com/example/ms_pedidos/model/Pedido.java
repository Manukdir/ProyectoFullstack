package com.example.ms_pedidos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa la tabla pedidos.
 */
@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "numero_pedido", nullable = false, length = 150)
    private String numeroPedido;

    @Column(name = "estado", nullable = false, length = 150)
    private String estado;

    @Column(name = "direccion_entrega", nullable = false, length = 150)
    private String direccionEntrega;

    @Column(name = "cantidad_productos", nullable = false)
    private Integer cantidadProductos;

    @Column(name = "total", nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;
}
