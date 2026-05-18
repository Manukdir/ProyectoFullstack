package com.example.ms_usuarios.runner;

import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.PerfilRepository;
import com.example.ms_usuarios.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosRunner implements CommandLineRunner {

        @Autowired
        private UsuarioRepository usuarioRepository;
    @Autowired
        private PerfilRepository perfilRepository;

    @Override
    public void run(String... args) {
                if (usuarioRepository.count() == 0) {
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("nombre 1");
            usuario1.setEmail("email1@correo.cl");
            usuario1.setTelefono("telefono 1");
            usuario1.setDireccionEntrega("direccionEntrega 1");
            usuario1.setPuntosFidelidad(10);
            usuario1.setActivo(true);
            usuario1.setFechaRegistro(LocalDate.of(2026, 1, 10));
            usuario1 = usuarioRepository.save(usuario1);
        
            Usuario usuario2 = new Usuario();
            usuario2.setNombre("nombre 2");
            usuario2.setEmail("email2@correo.cl");
            usuario2.setTelefono("telefono 2");
            usuario2.setDireccionEntrega("direccionEntrega 2");
            usuario2.setPuntosFidelidad(20);
            usuario2.setActivo(false);
            usuario2.setFechaRegistro(LocalDate.of(2026, 2, 10));
            usuario2 = usuarioRepository.save(usuario2);
        
            Usuario usuario3 = new Usuario();
            usuario3.setNombre("nombre 3");
            usuario3.setEmail("email3@correo.cl");
            usuario3.setTelefono("telefono 3");
            usuario3.setDireccionEntrega("direccionEntrega 3");
            usuario3.setPuntosFidelidad(30);
            usuario3.setActivo(true);
            usuario3.setFechaRegistro(LocalDate.of(2026, 3, 10));
            usuario3 = usuarioRepository.save(usuario3);
        
        }
        
        List<Usuario> usuariosBase = usuarioRepository.findAll();
        if (perfilRepository.count() == 0) {
            Perfil perfil1 = new Perfil();
            perfil1.setNombrePerfil("nombrePerfil 1");
            perfil1.setDescripcion("descripcion 1");
            perfil1.setNivelAcceso(10);
            perfil1.setActivo(true);
            perfil1.setFechaCreacion(LocalDate.of(2026, 1, 10));
            perfil1.setPermisosEspeciales(true);
            perfil1.setUsuario(usuariosBase.get((1 - 1) % usuariosBase.size()));
            perfil1 = perfilRepository.save(perfil1);
        
            Perfil perfil2 = new Perfil();
            perfil2.setNombrePerfil("nombrePerfil 2");
            perfil2.setDescripcion("descripcion 2");
            perfil2.setNivelAcceso(20);
            perfil2.setActivo(false);
            perfil2.setFechaCreacion(LocalDate.of(2026, 2, 10));
            perfil2.setPermisosEspeciales(false);
            perfil2.setUsuario(usuariosBase.get((2 - 1) % usuariosBase.size()));
            perfil2 = perfilRepository.save(perfil2);
        
            Perfil perfil3 = new Perfil();
            perfil3.setNombrePerfil("nombrePerfil 3");
            perfil3.setDescripcion("descripcion 3");
            perfil3.setNivelAcceso(30);
            perfil3.setActivo(true);
            perfil3.setFechaCreacion(LocalDate.of(2026, 3, 10));
            perfil3.setPermisosEspeciales(true);
            perfil3.setUsuario(usuariosBase.get((3 - 1) % usuariosBase.size()));
            perfil3 = perfilRepository.save(perfil3);
        
        }
        
    }
}
