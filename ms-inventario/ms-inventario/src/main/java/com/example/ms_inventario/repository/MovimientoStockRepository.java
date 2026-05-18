package com.example.ms_inventario.repository;

import com.example.ms_inventario.model.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Integer> {
    List<MovimientoStock> findByTipoMovimientoAndAprobadoTrue(String tipoMovimiento);
}