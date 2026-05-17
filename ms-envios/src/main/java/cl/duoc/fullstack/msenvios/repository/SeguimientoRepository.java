package cl.duoc.fullstack.msenvios.repository;

import cl.duoc.fullstack.msenvios.model.Seguimiento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {

}
