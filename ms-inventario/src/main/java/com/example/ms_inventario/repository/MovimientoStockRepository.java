package com.example.ms_inventario.repository;

import com.example.ms_inventario.model.MovimientoStock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Integer> {
}
