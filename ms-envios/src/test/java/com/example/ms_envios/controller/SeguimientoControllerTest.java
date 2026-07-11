package com.example.ms_envios.controller;

import com.example.ms_envios.dto.SeguimientoDTO;
import com.example.ms_envios.dto.SeguimientoRequestDTO;
import com.example.ms_envios.exception.ResourceNotFoundException;
import com.example.ms_envios.service.SeguimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
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

@WebMvcTest(SeguimientoController.class)
class SeguimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SeguimientoService seguimientoService;

    @Test
    void obtenerPorIdRetornaSeguimiento() throws Exception {
        when(seguimientoService.obtenerPorId(1)).thenReturn(seguimientoDto(1));

        mockMvc.perform(get("/api/v1/seguimientos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.envioId").value(1));
    }

    @Test
    void obtenerPorIdRetorna404CuandoNoExiste() throws Exception {
        when(seguimientoService.obtenerPorId(99))
                .thenThrow(new ResourceNotFoundException("Seguimiento no encontrado con id 99"));

        mockMvc.perform(get("/api/v1/seguimientos/{id}", 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void crearRetorna201ConRequestValido() throws Exception {
        SeguimientoRequestDTO request = requestValido();
        when(seguimientoService.crear(any(SeguimientoRequestDTO.class))).thenReturn(seguimientoDto(1));

        mockMvc.perform(post("/api/v1/seguimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(seguimientoService).crear(any(SeguimientoRequestDTO.class));
    }

    @Test
    void crearRetorna400ConRequestInvalido() throws Exception {
        SeguimientoRequestDTO request = new SeguimientoRequestDTO(
                null,
                "",
                "",
                "",
                -1,
                null,
                LocalDateTime.now().plusDays(1));

        mockMvc.perform(post("/api/v1/seguimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));

        verify(seguimientoService, never()).crear(any());
    }

    private SeguimientoRequestDTO requestValido() {
        return new SeguimientoRequestDTO(
                1,
                "EN_TRANSITO",
                "Pedido recibido en centro de distribucion",
                "Santiago",
                1,
                true,
                LocalDateTime.now().minusHours(1));
    }

    private SeguimientoDTO seguimientoDto(Integer id) {
        return new SeguimientoDTO(
                id,
                1,
                "EN_TRANSITO",
                "Pedido recibido en centro de distribucion",
                "Santiago",
                1,
                true,
                LocalDateTime.now().minusHours(1));
    }
}
