package com.example.ms_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Punto de inicio del microservicio de pedidos.
 * EnableFeignClients permite utilizar las interfaces que consumen otros servicios.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPedidosApplication.class, args);
	}

}
