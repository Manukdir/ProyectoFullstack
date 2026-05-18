package com.example.ms_productos.repository;

import com.example.ms_productos.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreContainingIgnoreCaseAndPrecioLessThan(String nombre, BigDecimal precio);
}
