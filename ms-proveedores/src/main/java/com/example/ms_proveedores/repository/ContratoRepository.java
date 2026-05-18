package com.example.ms_proveedores.repository;

import com.example.ms_proveedores.model.Contrato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
}
