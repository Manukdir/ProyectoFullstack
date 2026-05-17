package cl.duoc.fullstack.mspedidos.repository;

import cl.duoc.fullstack.mspedidos.model.DetallePedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

}
