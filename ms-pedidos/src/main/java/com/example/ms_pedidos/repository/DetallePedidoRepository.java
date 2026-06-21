package com.example.ms_pedidos.repository;

import com.example.ms_pedidos.model.DetallePedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acceso a los detalles de pedido almacenados en MySQL.
 */
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}
