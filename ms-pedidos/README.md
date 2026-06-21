# MS Pedidos

Microservicio encargado de administrar pedidos y sus detalles.

## Tecnologías

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- OpenFeign y Eureka Client
- Swagger / OpenAPI
- Spring HATEOAS
- JUnit, Mockito y JaCoCo

## Ejecución

1. Encender MySQL en Laragon.
2. Levantar Eureka Server en el puerto `8761`.
3. Ejecutar `MsPedidosApplication`.
4. El servicio queda disponible en `http://localhost:8084`.

## Enlaces

- Swagger: `http://localhost:8084/swagger-ui.html`
- Pedidos: `http://localhost:8084/api/v1/pedidos`
- Detalles: `http://localhost:8084/api/v1/detalles-pedido`
- HATEOAS: `http://localhost:8084/api/v1/hateoas/pedidos`

## Pruebas

```bash
./mvnw test
```

El reporte JaCoCo se genera en `target/site/jacoco/index.html`.
