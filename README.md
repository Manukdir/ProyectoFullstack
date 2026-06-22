# Proyecto Fullstack - Aporte de microservicios

Este repositorio contiene el proyecto grupal desarrollado para la asignatura
Fullstack. Mi aporte se concentra en los siguientes microservicios:

- `ms-pedidos`: administración de pedidos y detalles de pedido.
- `ms-envios`: administración de envíos y eventos de seguimiento.
- `ms-proveedores`: administración de proveedores y contratos.

Los tres servicios fueron desarrollados con Java 17 y Spring Boot, siguiendo
una arquitectura por capas para separar las responsabilidades del sistema.

## Tecnologías utilizadas

- Java 17.
- Spring Boot 3.3.5.
- Spring Data JPA.
- MySQL mediante Laragon.
- OpenFeign para comunicación entre microservicios.
- Eureka Discovery Client.
- Spring Cloud API Gateway.
- Springdoc OpenAPI y Swagger.
- Spring HATEOAS.
- Jakarta Validation.
- JUnit 5 y Mockito.
- JaCoCo para revisar la cobertura de pruebas.
- Flyway en pedidos y Liquibase en envíos.

## Arquitectura

Cada microservicio utiliza la siguiente estructura:

```text
Controller -> Service -> Repository -> Base de datos
                  |
                Mapper
                  |
                 DTO
```

- **Controller:** recibe las peticiones HTTP y devuelve las respuestas.
- **Service:** contiene la lógica de negocio.
- **Repository:** realiza las operaciones con la base de datos usando JPA.
- **Model:** representa las tablas mediante entidades JPA.
- **DTO:** define los datos que entran y salen de la API.
- **Mapper:** convierte entidades en DTO y DTO en entidades.
- **Client:** contiene las interfaces Feign para consultar otros servicios.
- **Exception:** centraliza los errores y sus códigos HTTP.

## Microservicios desarrollados

### MS Pedidos

Administra los pedidos realizados por los usuarios y los productos asociados a
cada pedido.

| Recurso | Puerto | Ruta principal |
| --- | ---: | --- |
| Pedidos | `8084` | `/api/v1/pedidos` |
| Detalles de pedido | `8084` | `/api/v1/detalles-pedido` |
| HATEOAS | `8084` | `/api/v1/hateoas/pedidos` |

También permite consultar usuarios y productos mediante OpenFeign:

```text
/api/v1/pedidos/integracion/usuarios/{id}
/api/v1/pedidos/integracion/productos/{id}
```

### MS Envíos

Administra los datos de despacho y los eventos que permiten conocer el estado
y la ubicación de un envío.

| Recurso | Puerto | Ruta principal |
| --- | ---: | --- |
| Envíos | `8086` | `/api/v1/envios` |
| Seguimientos | `8086` | `/api/v1/seguimientos` |
| HATEOAS | `8086` | `/api/v1/hateoas/envios` |

La comunicación con pedidos se realiza mediante Eureka y OpenFeign:

```text
/api/v1/envios/integracion/pedidos/{id}
/api/v1/envios/integracion/usuarios/{id}
```

### MS Proveedores

Administra los proveedores registrados y los contratos relacionados con cada
uno.

| Recurso | Puerto | Ruta principal |
| --- | ---: | --- |
| Proveedores | `8087` | `/api/v1/proveedores` |
| Contratos | `8087` | `/api/v1/contratos` |
| HATEOAS | `8087` | `/api/v1/hateoas/proveedores` |

Incluye una consulta al microservicio de productos mediante OpenFeign:

```text
/api/v1/proveedores/integracion/productos/{id}
```

## Funcionalidades implementadas

- Operaciones CRUD con `GET`, `POST`, `PUT` y `DELETE`.
- Relaciones entre entidades usando JPA.
- DTO de entrada y salida.
- Mappers para evitar exponer directamente las entidades.
- Validaciones con `@NotNull`, `@NotBlank`, `@Positive` y otras anotaciones.
- Manejo global de excepciones.
- Respuestas HTTP `200`, `201`, `204`, `400`, `404`, `409`, `500` y `502`.
- Registros de actividad mediante logs.
- Comunicación entre microservicios con OpenFeign.
- Registro y descubrimiento de servicios mediante Eureka.
- Rutas disponibles mediante API Gateway.
- Documentación Swagger con descripciones, parámetros y ejemplos.
- Enlaces HATEOAS en los recursos principales.
- Pruebas unitarias con JUnit y Mockito.
- Reportes de cobertura con JaCoCo.
- Configuración de cada servicio mediante `application.yml`.

## Orden recomendado de ejecución

1. Encender MySQL desde Laragon.
2. Ejecutar Eureka Server en el puerto `8761`.
3. Ejecutar los servicios auxiliares:
   - usuarios en el puerto `8081`;
   - productos en el puerto `8082`.
4. Ejecutar `ms-pedidos` en el puerto `8084`.
5. Ejecutar `ms-envios` en el puerto `8086`.
6. Ejecutar `ms-proveedores` en el puerto `8087`.
7. Ejecutar API Gateway en el puerto `8090`.

Cada servicio puede iniciarse desde su clase `Application` en IntelliJ o desde
Git Bash:

```bash
cd ms-pedidos
./mvnw spring-boot:run
```

En Windows también se puede ejecutar:

```bash
mvnw.cmd spring-boot:run
```

El mismo procedimiento se utiliza dentro de `ms-envios` y
`ms-proveedores`.

## Documentación Swagger

Con los servicios ejecutándose, Swagger se encuentra disponible en:

- Pedidos: `http://localhost:8084/swagger-ui.html`
- Envíos: `http://localhost:8086/swagger-ui.html`
- Proveedores: `http://localhost:8087/swagger-ui.html`

Swagger permite revisar los endpoints y probar las peticiones sin crear
manualmente cada solicitud en Postman.

## Pruebas en Postman

Ejemplo para listar pedidos:

```http
GET http://localhost:8084/api/v1/pedidos
```

Ejemplo para consultar un envío:

```http
GET http://localhost:8086/api/v1/envios/1
```

Ejemplo para consultar un proveedor:

```http
GET http://localhost:8087/api/v1/proveedores/1
```

Las mismas rutas pueden utilizarse a través del Gateway:

```text
http://localhost:8090/api/v1/pedidos
http://localhost:8090/api/v1/envios
http://localhost:8090/api/v1/proveedores
```

## Pruebas unitarias y cobertura

Para ejecutar todas las pruebas de un microservicio:

```bash
./mvnw clean verify
```

Resultados obtenidos:

| Microservicio | Pruebas | Errores | Cobertura de servicios |
| --- | ---: | ---: | ---: |
| Pedidos | 15 | 0 | 89,35 % |
| Envíos | 14 | 0 | 90,51 % |
| Proveedores | 15 | 0 | 89,35 % |

El reporte HTML de JaCoCo se genera dentro de cada microservicio en:

```text
target/site/jacoco/index.html
```

## Manejo de errores

Los servicios utilizan un manejador global para entregar respuestas JSON
uniformes:

- `400 Bad Request`: datos incompletos o inválidos.
- `404 Not Found`: el recurso solicitado no existe.
- `409 Conflict`: conflicto con los datos almacenados.
- `502 Bad Gateway`: error al comunicarse con otro microservicio.
- `500 Internal Server Error`: error inesperado.

## Requisitos de la rúbrica cubiertos

- CRUD y persistencia con JPA.
- Relaciones entre entidades.
- DTO, mapper y validaciones.
- Manejo de excepciones y logs.
- OpenFeign y comunicación entre microservicios.
- Swagger/OpenAPI.
- Spring HATEOAS.
- JUnit, Mockito y cobertura JaCoCo superior al 80 %.
- Eureka Discovery Client.
- Integración con Eureka Server.
- API Gateway.
- Configuración YAML.
- Código comentado y organizado por responsabilidades.

## Commit principal del aporte

```text
7b20188 completo mejoras EA3 en pedidos envios y proveedores
```
