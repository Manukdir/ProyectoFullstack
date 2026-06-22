package com.example.ms_reportes.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class PagoClient {

    private final WebClient webClient;

    public PagoClient(
            WebClient.Builder webClientBuilder,
            @Value("${pagos.service.url}") String pagosServiceUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(pagosServiceUrl)
                .build();
    }

    public List<PagoRemoteDTO> listarPagos() {
        return webClient.get()
                .uri("/api/v1/pagos")
                .retrieve()
                .bodyToFlux(PagoRemoteDTO.class)
                .collectList()
                .block();
    }
}