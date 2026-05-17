package cl.duoc.fullstack.msenvios.repository;

import cl.duoc.fullstack.msenvios.model.Envio;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    @Query("SELECT e FROM Envio e WHERE e.fechaEnvio BETWEEN :desde AND :hasta AND e.entregado = false")
    List<Envio> buscarNoEntregadosPorRango(LocalDate desde, LocalDate hasta);

}
