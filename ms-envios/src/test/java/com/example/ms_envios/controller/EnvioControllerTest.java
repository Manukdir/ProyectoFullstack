package com.example.ms_envios.controller;

import com.example.ms_envios.dto.EnvioDTO;
import com.example.ms_envios.dto.EnvioRequestDTO;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnvioController.class)
class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnvioService envioService;

    @Test
    void obtenerPorIdRetornaEnvio() throws Exception {
        when(envioService.obtenerPorId(1)).thenReturn(envioDto(1));

        mockMvc.perform(get("/api/v1/envios/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pedidoId").value(1));
    }

    @Test
    void obtenerPorIdRetorna404CuandoNoExiste() throws Exception {
        when(envioService.obtenerPorId(99))
                .thenThrow(new ResourceNotFoundException("Envio no encontrado con id 99"));

        mockMvc.perform(get("/api/v1/envios/{id}", 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void crearRetorna201ConRequestValido() throws Exception {
        EnvioRequestDTO request = requestValido();
        when(envioService.crear(any(EnvioRequestDTO.class))).thenReturn(envioDto(1));

        mockMvc.perform(post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(envioService).crear(any(EnvioRequestDTO.class));
    }

    @Test
    void crearRetorna400ConRequestInvalido() throws Exception {
        EnvioRequestDTO request = new EnvioRequestDTO(
                null,
                -1,
                "",
                "",
                "",
                new BigDecimal("-1.00"),
                null,
                LocalDate.now().plusDays(1),
                LocalDate.now().minusDays(1));

        mockMvc.perform(post("/api/v1/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));

        verify(envioService, never()).crear(any());
    }

    private EnvioRequestDTO requestValido() {
        return new EnvioRequestDTO(
                1,
                1,
                "ENV-1001",
                "Av. Principal 123",
                "Chilexpress",
                new BigDecimal("3990.00"),
                false,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(3));
    }

    private EnvioDTO envioDto(Integer id) {
        return new EnvioDTO(
                id,
                1,
                1,
                "ENV-1001",
                "Av. Principal 123",
                "Chilexpress",
                new BigDecimal("3990.00"),
                false,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(3));
    }
}
