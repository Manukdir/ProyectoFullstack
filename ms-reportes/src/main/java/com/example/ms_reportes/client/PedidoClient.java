package com.example.ms_reportes.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class PedidoClient {

    private final WebClient webClient;

    public PedidoClient(
            WebClient.Builder webClientBuilder,
            @Value("${pedidos.service.url}") String pedidosServiceUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(pedidosServiceUrl)
                .build();
    }

    public List<PedidoRemoteDTO> listarPedidos() {
        return webClient.get()
                .uri("/api/v1/pedidos")
                .retrieve()
                .bodyToFlux(PedidoRemoteDTO.class)
                .collectList()
                .block();
    }
}