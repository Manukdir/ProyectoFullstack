package com.example.ms_usuarios.runner;

import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.PerfilRepository;
import com.example.ms_usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        return args -> {

            if (usuarioRepository.count() == 0) {

                // 1. Creamos 3 Usuarios
                Usuario u1 = new Usuario();
                u1.setNombreCompleto("Juan Perez");
                u1.setEmail("juan@mail.com");
                u1.setPuntosAcumulados(100);
                u1.setActivo(true);
                u1.setFechaRegistro(LocalDate.now());

                Usuario u2 = new Usuario();
                u2.setNombreCompleto("Maria Lopez");
                u2.setEmail("maria@mail.com");
                u2.setPuntosAcumulados(250);
                u2.setActivo(true);
                u2.setFechaRegistro(LocalDate.now());

                Usuario u3 = new Usuario();
                u3.setNombreCompleto("Carlos Ruiz");
                u3.setEmail("carlos@mail.com");
                u3.setPuntosAcumulados(0);
                u3.setActivo(false);
                u3.setFechaRegistro(LocalDate.now());

                usuarioRepository.save(u1);
                usuarioRepository.save(u2);
                usuarioRepository.save(u3);


                Perfil p1 = new Perfil();
                p1.setNombre("Admin");
                p1.setDescripcion("Administrador del sistema");
                p1.setNivelAcceso(10);
                p1.setHabilitado(true);
                p1.setFechaCreacion(LocalDate.now());
                p1.setUsuario(u1);

                Perfil p2 = new Perfil();
                p2.setNombre("Cliente");
                p2.setDescripcion("Cliente regular");
                p2.setNivelAcceso(1);
                p2.setHabilitado(true);
                p2.setFechaCreacion(LocalDate.now());
                p2.setUsuario(u2);

                Perfil p3 = new Perfil();
                p3.setNombre("Soporte");
                p3.setDescripcion("Soporte tecnico nivel 1");
                p3.setNivelAcceso(5);
                p3.setHabilitado(true);
                p3.setFechaCreacion(LocalDate.now());
                p3.setUsuario(u3);

                perfilRepository.save(p1);
                perfilRepository.save(p2);
                perfilRepository.save(p3);
            }
        };
    }
}