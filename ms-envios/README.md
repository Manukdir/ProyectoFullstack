# MS Envíos

Microservicio encargado de administrar envíos y eventos de seguimiento.

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
3. Ejecutar `MsEnviosApplication`.
4. El servicio queda disponible en `http://localhost:8086`.

## Enlaces

- Swagger: `http://localhost:8086/swagger-ui.html`
- Envíos: `http://localhost:8086/api/v1/envios`
- Seguimientos: `http://localhost:8086/api/v1/seguimientos`
- HATEOAS: `http://localhost:8086/api/v1/hateoas/envios`

## Pruebas

```bash
./mvnw test
```

El reporte JaCoCo se genera en `target/site/jacoco/index.html`.
