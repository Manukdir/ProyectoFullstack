package com.example.ms_pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Punto de inicio del microservicio de pagos.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsPagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagosApplication.class, args);
	}

}
