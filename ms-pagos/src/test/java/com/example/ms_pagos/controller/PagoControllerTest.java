package com.example.ms_pagos.controller;

import com.example.ms_pagos.dto.PagoDTO;
import com.example.ms_pagos.dto.PagoRequestDTO;
import com.example.ms_pagos.exception.ResourceNotFoundException;
import com.example.ms_pagos.service.PagoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
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

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PagoService pagoService;

    @Test
    void obtenerPorIdRetornaPago() throws Exception {
        PagoDTO dto = pagoDto(1);
        when(pagoService.obtenerPorId(1)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/pagos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pedidoId").value(1));
    }

    @Test
    void obtenerPorIdRetorna404CuandoNoExiste() throws Exception {
        when(pagoService.obtenerPorId(99))
                .thenThrow(new ResourceNotFoundException("No existe el pago con id 99"));

        mockMvc.perform(get("/api/v1/pagos/{id}", 99))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void crearRetorna201ConRequestValido() throws Exception {
        PagoRequestDTO request = requestValido();
        PagoDTO creado = pagoDto(1);
        when(pagoService.crear(any(PagoRequestDTO.class))).thenReturn(creado);

        mockMvc.perform(post("/api/v1/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(pagoService).crear(any(PagoRequestDTO.class));
    }

    @Test
    void crearRetorna400ConRequestInvalido() throws Exception {
        PagoRequestDTO request = new PagoRequestDTO(
                null,
                "",
                "",
                "",
                -1,
                new BigDecimal("-100.00"),
                null,
                LocalDateTime.now().plusDays(1));

        mockMvc.perform(post("/api/v1/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));

        verify(pagoService, never()).crear(any());
    }

    private PagoRequestDTO requestValido() {
        return new PagoRequestDTO(
                1,
                "TARJETA",
                "APROBADO",
                "TRX-1001",
                1,
                new BigDecimal("19990.00"),
                true,
                LocalDateTime.now().minusDays(1));
    }

    private PagoDTO pagoDto(Integer id) {
        return new PagoDTO(
                id,
                1,
                "TARJETA",
                "APROBADO",
                "TRX-1001",
                1,
                new BigDecimal("19990.00"),
                true,
                LocalDateTime.now().minusDays(1));
    }
}
