package com.example.ms_usuarios.repository;

import com.example.ms_usuarios.model.Perfil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
