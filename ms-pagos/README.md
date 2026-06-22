# MS Pagos

**Autor: Luis Stiven**

Este microservicio permite registrar y administrar los pagos de los pedidos.

## Datos principales

- Puerto: `8085`
- Base de datos: `prueba4`
- Ruta principal: `/api/v1/pagos`
- Swagger: `http://localhost:8085/swagger-ui.html`

## Endpoints

```http
GET    /api/v1/pagos
GET    /api/v1/pagos/{id}
GET    /api/v1/pagos/buscar
POST   /api/v1/pagos
PUT    /api/v1/pagos/{id}
DELETE /api/v1/pagos/{id}
```

Rutas adicionales:

```http
GET /api/v1/hateoas/pagos
GET /api/v1/pagos/integracion/pedidos/{id}
```

La segunda ruta usa OpenFeign para consultar un pedido registrado en Eureka.

## Ejemplo de pago

```json
{
  "pedidoId": 1,
  "metodoPago": "TARJETA",
  "estadoPago": "APROBADO",
  "codigoTransaccion": "TRX-1001",
  "numeroCuotas": 3,
  "monto": 45990,
  "pagado": true,
  "fechaPago": "2026-06-20T10:30:00"
}
```

## Tecnologias

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- MySQL y Flyway
- OpenFeign y Eureka
- Swagger/OpenAPI
- Spring HATEOAS
- JUnit, Mockito y JaCoCo

## Pruebas

```bash
./mvnw clean verify
```

Resultado: 10 pruebas correctas y 100 % de cobertura en `PagoService`.
