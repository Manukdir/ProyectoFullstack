package com.example.ms_usuarios.repository;

import com.example.ms_usuarios.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByEmailAndActivoTrue(String email);
}
