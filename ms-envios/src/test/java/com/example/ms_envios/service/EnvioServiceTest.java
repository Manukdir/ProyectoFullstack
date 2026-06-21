package com.example.ms_envios.service;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.mapper.EnvioMapper;
import com.example.ms_envios.model.Envio;
import com.example.ms_envios.repository.EnvioRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @Mock
    private EnvioMapper envioMapper;

    @InjectMocks
    private EnvioService envioService;

    @Test
    void obtenerPorIdRetornaEnvioCuandoExiste() {
        Envio envio = new Envio();
        EnvioDTO esperado = new EnvioDTO();

        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
        when(envioMapper.toDTO(envio)).thenReturn(esperado);

        EnvioDTO resultado = envioService.obtenerPorId(1);

        assertEquals(esperado, resultado);
    }
}
