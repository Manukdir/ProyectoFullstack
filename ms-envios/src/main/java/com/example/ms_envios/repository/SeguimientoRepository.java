package com.example.ms_envios.repository;

import com.example.ms_envios.model.Seguimiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
}
