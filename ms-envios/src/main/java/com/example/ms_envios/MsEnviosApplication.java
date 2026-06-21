package com.example.ms_envios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Punto de inicio del microservicio de envíos y de sus clientes Feign.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsEnviosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEnviosApplication.class, args);
	}

}
