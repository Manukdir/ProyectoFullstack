package com.example.ms_usuarios.service;

import com.example.ms_usuarios.dto.UsuarioDTO;
import com.example.ms_usuarios.dto.UsuarioRequestDTO;
import com.example.ms_usuarios.mapper.UsuarioMapper;
import com.example.ms_usuarios.model.Perfil;
import com.example.ms_usuarios.model.Usuario;
import com.example.ms_usuarios.repository.UsuarioRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private PerfilService perfilService;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        perfilService = Mockito.mock(PerfilService.class);
        usuarioService = new UsuarioService(usuarioRepository, perfilService, new UsuarioMapper());
    }

    @Test
    void obtenerPorIdRetornaUsuario() {
        Perfil perfil = new Perfil(1, "CLIENTE", "Cliente de la tienda");
        Usuario usuario = new Usuario(1, "Luis", "luis@mail.com", "999999999", true, perfil);
        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        UsuarioDTO resultado = usuarioService.obtenerPorId(1);

        Assertions.assertEquals("Luis", resultado.getNombre());
        Assertions.assertEquals("CLIENTE", resultado.getPerfilNombre());
    }

    @Test
    void crearGuardaUsuarioConPerfil() {
        Perfil perfil = new Perfil(1, "CLIENTE", "Cliente de la tienda");
        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNombre("Ana");
        request.setEmail("ana@mail.com");
        request.setTelefono("988888888");
        request.setActivo(true);
        request.setPerfilId(1);

        Mockito.when(perfilService.buscarEntidad(1)).thenReturn(perfil);
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId(10);
            return usuario;
        });

        UsuarioDTO resultado = usuarioService.crear(request);

        Assertions.assertEquals(10, resultado.getId());
        Assertions.assertEquals("Ana", resultado.getNombre());
    }
}
