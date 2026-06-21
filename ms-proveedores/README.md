# MS Proveedores

Microservicio encargado de administrar proveedores y sus contratos.

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
3. Ejecutar `MsProveedoresApplication`.
4. El servicio queda disponible en `http://localhost:8087`.

## Enlaces

- Swagger: `http://localhost:8087/swagger-ui.html`
- Proveedores: `http://localhost:8087/api/v1/proveedores`
- Contratos: `http://localhost:8087/api/v1/contratos`
- HATEOAS: `http://localhost:8087/api/v1/hateoas/proveedores`

## Pruebas

```bash
./mvnw test
```

El reporte JaCoCo se genera en `target/site/jacoco/index.html`.
