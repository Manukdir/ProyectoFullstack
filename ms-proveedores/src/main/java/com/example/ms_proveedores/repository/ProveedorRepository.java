package com.example.ms_proveedores.repository;

import com.example.ms_proveedores.model.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query(value = "SELECT * FROM proveedores WHERE activo = true ORDER BY nombre ASC", nativeQuery = true)
    List<Proveedor> buscarProveedoresActivosOrdenados();
}
