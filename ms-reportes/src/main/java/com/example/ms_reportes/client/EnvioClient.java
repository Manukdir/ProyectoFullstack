package com.example.ms_reportes.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class EnvioClient {

    private final WebClient webClient;

    public EnvioClient(
            WebClient.Builder webClientBuilder,
            @Value("${envios.service.url}") String enviosServiceUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(enviosServiceUrl)
                .build();
    }

    public List<EnvioRemoteDTO> listarEnvios() {
        return webClient.get()
                .uri("/api/v1/envios")
                .retrieve()
                .bodyToFlux(EnvioRemoteDTO.class)
                .collectList()
                .block();
    }
}