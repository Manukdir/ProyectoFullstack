package com.example.ms_usuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usuariosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Usuarios - E-Commerce")
                        .version("1.0.0")
                        .description(
                                "Microservicio encargado de gestionar usuarios "
                                        + "del sistema E-Commerce."
                        ));
    }
}