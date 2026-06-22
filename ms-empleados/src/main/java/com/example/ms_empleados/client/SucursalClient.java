package com.example.ms_empleados.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SucursalClient {

    private final WebClient webClient;

    public SucursalClient(
            WebClient.Builder webClientBuilder,
            @Value("${sucursales.service.url}") String sucursalesServiceUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(sucursalesServiceUrl)
                .build();
    }

    public SucursalClientDTO obtenerSucursalPorId(Integer id) {
        return webClient.get()
                .uri("/api/v1/sucursales/{id}", id)
                .retrieve()
                .bodyToMono(SucursalClientDTO.class)
                .block();
    }
}