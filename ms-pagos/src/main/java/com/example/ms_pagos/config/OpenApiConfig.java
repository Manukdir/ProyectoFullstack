package com.example.ms_pagos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura la información principal que aparece en Swagger.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pagosOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API de Pagos")
                .version("1.0")
                .description("Microservicio para administrar pagos de pedidos"));
    }
}
