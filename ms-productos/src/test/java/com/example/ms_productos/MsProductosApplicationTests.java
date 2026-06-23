package com.example.ms_productos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Verifica que el contexto de Spring cargue correctamente, con todas las
 * dependencias nuevas (Feign, Eureka Client, HATEOAS, Swagger) configuradas.
 *
 * IMPORTANTE: este test necesita que MySQL este corriendo y la base de
 * datos configurada en application.yml exista (o pueda crearse), porque
 * levanta el contexto completo de la aplicacion, incluyendo JPA.
 * Si solo quieres probar la logica de negocio sin base de datos, ejecuta
 * ProductoServiceTest y CategoriaServiceTest, que usan Mockito y no
 * requieren conexion real.
 */
@SpringBootTest
class MsProductosApplicationTests {

	@Test
	void contextLoads() {
	}

}
