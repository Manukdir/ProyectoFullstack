package com.example.ms_sucursales.repository;

import com.example.ms_sucursales.model.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    @Query(value = "SELECT s.* FROM sucursales s JOIN regiones r ON s.region_id = r.id WHERE LOWER(r.nombre) = LOWER(:nombreRegion)", nativeQuery = true)
    List<Sucursal> buscarPorNombreRegion(@Param("nombreRegion") String nombreRegion);
}
