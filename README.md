# Microservicios envios, pagos y pedidos

## Mi aporte

**Autor: Luis Stiven**

Este proyecto fue realizado para la asignatura Fullstack I. Mi parte corresponde
a los siguientes microservicios:

- `ms-pedidos`
- `ms-envios`
- `ms-pagos`

En estos servicios trabaje el CRUD, la conexion con MySQL, validaciones,
comunicacion entre microservicios, documentacion y pruebas.

## Servicios

### Pedidos

- Puerto: `8084`
- Pedidos: `/api/v1/pedidos`
- Detalles: `/api/v1/detalles-pedido`
- Swagger: `http://localhost:8084/swagger-ui.html`

### Envios

- Puerto: `8086`
- Envios: `/api/v1/envios`
- Seguimientos: `/api/v1/seguimientos`
- Swagger: `http://localhost:8086/swagger-ui.html`

### Pagos

- Puerto: `8085`
- Pagos: `/api/v1/pagos`
- Busqueda: `/api/v1/pagos/buscar`
- Swagger: `http://localhost:8085/swagger-ui.html`

## Tecnologias usadas

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- MySQL con Laragon
- OpenFeign
- Eureka Discovery Client
- API Gateway
- Swagger/OpenAPI
- Spring HATEOAS
- JUnit y Mockito
- JaCoCo
- Flyway y Liquibase
- Configuracion YAML

## Organizacion

El codigo esta separado en capas:

```text
Controller -> Service -> Repository -> Base de datos
                  |
                Mapper
                  |
                 DTO
```

- `controller`: recibe las peticiones HTTP.
- `service`: contiene la logica.
- `repository`: se comunica con MySQL.
- `model`: representa las tablas.
- `dto`: contiene los datos de entrada y salida.
- `mapper`: convierte entre DTO y entidad.
- `client`: contiene las llamadas con OpenFeign.
- `exception`: controla los errores de la API.

## Funcionalidades

- CRUD con GET, POST, PUT y DELETE.
- Validaciones de los datos recibidos.
- Manejo de errores 400, 404, 409, 500 y 502.
- Registro de servicios en Eureka.
- Comunicacion entre servicios usando OpenFeign.
- Documentacion con Swagger.
- Enlaces HATEOAS.
- Pruebas unitarias con JUnit y Mockito.
- Reporte de cobertura con JaCoCo.

## Como ejecutar

1. Encender MySQL desde Laragon.
2. Ejecutar Eureka Server en el puerto `8761`.
3. Ejecutar los servicios necesarios.
4. Ejecutar API Gateway en el puerto `8090`.
5. Probar las rutas desde Postman o Swagger.

Tambien se puede ejecutar cada servicio desde Git Bash:

```bash
cd ms-pagos
./mvnw spring-boot:run
```

## Ejemplos para Postman

```http
GET http://localhost:8084/api/v1/pedidos
GET http://localhost:8086/api/v1/envios
GET http://localhost:8085/api/v1/pagos
```

Por API Gateway:

```http
GET http://localhost:8090/api/v1/pedidos
GET http://localhost:8090/api/v1/envios
GET http://localhost:8090/api/v1/pagos
```

## Pruebas realizadas

Para ejecutar las pruebas:

```bash
./mvnw clean verify
```

| Microservicio | Pruebas | Cobertura de Service |
| --- | ---: | ---: |
| Pedidos | 15 | 89,35 % |
| Envios | 14 | 90,51 % |
| Pagos | 10 | 100 % |

El reporte se genera en:

```text
target/site/jacoco/index.html
```

Tambien se probaron los tres servicios en MySQL, Eureka, Swagger, HATEOAS,
OpenFeign, Postman y API Gateway.
