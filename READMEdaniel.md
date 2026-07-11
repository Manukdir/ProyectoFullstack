# Proyecto Fullstack - Microservicios

Proyecto para la evaluacion final transversal de DSY1103 Desarrollo FullStack I. La arquitectura usa microservicios Spring Boot registrados en Eureka, expuestos por API Gateway, con persistencia MySQL, comunicacion Feign, documentacion Swagger/OpenAPI, HATEOAS y pruebas automatizadas.

## Microservicios y puertos

| Modulo | Puerto | Nombre Eureka | Base local |
| --- | ---: | --- | --- |
| eureka-server | 8761 | eureka-server | - |
| api.gateway | 8080 | api-gateway | - |
| ms-usuarios | 8081 | MS-USUARIOS | prueba1 |
| ms-productos | 8082 | MS-PRODUCTOS | prueba2 |
| ms-inventario | 8083 | MS-INVENTARIO | prueba2 |
| ms-pedidos | 8084 | MS-PEDIDOS | prueba3 |
| ms-pagos | 8085 | MS-PAGOS | prueba4 |
| ms-envios | 8086 | MS-ENVIOS | prueba5 |
| ms-proveedores | 8087 | MS-PROVEEDORES | prueba5 |
| ms-sucursales | 8088 | MS-SUCURSALES | prueba1 |
| ms-empleados | 8089 | MS-EMPLEADOS | prueba6 |
| ms-reportes | 8090 | MS-REPORTES | prueba7 |

## Orden de ejecucion

1. Levantar MySQL desde Laragon y asegurar las bases locales.
2. Ejecutar `eureka-server`.
3. Ejecutar los microservicios necesarios.
4. Ejecutar `api.gateway`.
5. Probar por Swagger o Postman.

## Perfiles

`ms-pagos` y `ms-envios` tienen perfiles `dev` y `test`. El perfil por defecto es `dev`.

```bash
cd ms-pagos
mvn spring-boot:run -Dspring-boot.run.profiles=dev

cd ../ms-envios
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

El perfil `test` usa H2 en memoria y desactiva el registro Eureka para pruebas JPA.

## Pruebas y JaCoCo

```bash
cd ms-pagos
mvn clean test -Dspring.profiles.active=test
mvn clean verify -Dspring.profiles.active=test

cd ../ms-envios
mvn clean test -Dspring.profiles.active=test
mvn clean verify -Dspring.profiles.active=test
```

El reporte JaCoCo se genera en `target/site/jacoco/index.html` al ejecutar `mvn clean verify`. No se declara un porcentaje minimo obligatorio.

## Swagger

```http
http://localhost:8081/swagger-ui.html
http://localhost:8082/swagger-ui.html
http://localhost:8083/swagger-ui.html
http://localhost:8084/swagger-ui.html
http://localhost:8085/swagger-ui.html
http://localhost:8086/swagger-ui.html
http://localhost:8087/swagger-ui.html
http://localhost:8088/swagger-ui.html
http://localhost:8089/swagger-ui.html
http://localhost:8090/swagger-ui.html
```

## Rutas principales por Gateway

```http
GET  http://localhost:8080/api/v1/usuarios
GET  http://localhost:8080/api/v1/productos
GET  http://localhost:8080/api/v1/inventarios
GET  http://localhost:8080/api/v1/pedidos
GET  http://localhost:8080/api/v1/pagos
POST http://localhost:8080/api/v1/pagos
GET  http://localhost:8080/api/v1/envios
POST http://localhost:8080/api/v1/envios
GET  http://localhost:8080/api/v1/seguimientos
POST http://localhost:8080/api/v1/seguimientos
GET  http://localhost:8080/api/v1/proveedores
GET  http://localhost:8080/api/v1/empleados
GET  http://localhost:8080/api/v1/sucursales
GET  http://localhost:8080/api/v1/reportes
```

HATEOAS prioritario:

```http
GET http://localhost:8080/api/v1/hateoas/pedidos
GET http://localhost:8080/api/v1/hateoas/pagos
GET http://localhost:8080/api/v1/hateoas/envios
```

## Feign, Eureka y Gateway

Eureka registra cada microservicio con su `spring.application.name`. Feign usa esos nombres, por ejemplo `MS-PEDIDOS`, `MS-USUARIOS`, `MS-PAGOS` y `MS-ENVIOS`, evitando URLs fijas entre servicios. API Gateway centraliza la entrada en el puerto `8080` y enruta con `lb://NOMBRE-SERVICIO`.

En `ms-pagos`, al crear o actualizar un pago se valida por Feign que el pedido exista. En `ms-envios`, al crear o actualizar un envio se valida que existan el pedido y el usuario; al crear o actualizar un seguimiento se valida que exista el envio asociado.

## Postman

La coleccion esta en:

```text
postman/ProyectoFullstack.postman_collection.json
```

Incluye requests basicos por Gateway para listar, obtener y crear pagos, envios y seguimientos.
