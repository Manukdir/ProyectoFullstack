package com.example.ms_proveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Punto de inicio del microservicio de proveedores.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsProveedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProveedoresApplication.class, args);
	}

}
