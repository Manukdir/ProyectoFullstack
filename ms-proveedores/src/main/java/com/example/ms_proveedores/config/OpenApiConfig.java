package com.example.ms_proveedores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * Personaliza la documentación OpenAPI presentada por Swagger.
 */
public class OpenApiConfig {

    @Bean
    public OpenAPI proveedoresOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ms-proveedores")
                        .version("1.0")
                        .description("Documentacion OpenAPI para proveedores y contratos"));
    }
}
