package cl.duoc.fullstack.mspagos.repository;

import cl.duoc.fullstack.mspagos.model.Pago;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

    @Query("SELECT p FROM Pago p WHERE p.monto > :monto AND p.estadoPago = :estadoPago")
    List<Pago> buscarPorMontoYEstado(BigDecimal monto, String estadoPago);

}
