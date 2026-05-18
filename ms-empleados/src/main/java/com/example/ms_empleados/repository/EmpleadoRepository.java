package com.example.ms_empleados.repository;

import com.example.ms_empleados.model.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    @Query(value = "SELECT * FROM empleados WHERE sucursal_id = :sucursalId AND YEAR(fecha_ingreso) = :anio", nativeQuery = true)
    List<Empleado> buscarPorSucursalYAnio(@Param("sucursalId") Integer sucursalId, @Param("anio") Integer anio);
}
