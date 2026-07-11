package com.example.ms_inventario.repository;

import com.example.ms_inventario.model.Inventario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    List<Inventario> findByProductoId(Integer productoId);
}
