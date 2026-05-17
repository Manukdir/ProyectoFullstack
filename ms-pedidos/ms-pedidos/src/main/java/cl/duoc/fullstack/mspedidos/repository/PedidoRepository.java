package cl.duoc.fullstack.mspedidos.repository;

import cl.duoc.fullstack.mspedidos.model.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p WHERE p.pagado = true ORDER BY p.fechaPedido DESC")
    List<Pedido> buscarPedidosPagadosRecientes();

}
