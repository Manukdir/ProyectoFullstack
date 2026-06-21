package com.example.ms_pedidos.repository;

import com.example.ms_pedidos.model.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data genera las operaciones CRUD de pedidos a partir de esta interfaz.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE p.pagado = true ORDER BY p.fechaPedido DESC")
    List<Pedido> buscarPedidosPagadosOrdenados();
}
