# Inditex IoT – Pricing Service

Servicio REST que devuelve el **precio aplicable** para un producto de una cadena (brand) en una **fecha** dada.

**Stack:** Java 17 · Spring Boot 3.5.6 · Spring Data JPA · H2 · JUnit 5  
**Doc API:** springdoc-openapi 2.8.13 (Swagger UI)

---

## Arquitectura

Implementa **Arquitectura Hexagonal (Puertos y Adaptadores)**.

```
HTTP (Adapter IN)
   ↓
PricingController  →  GetApplicablePriceUseCase (Puerto IN)
                         ↓
                     GetApplicablePriceService (Aplicación)
                         ↓
                     LoadPricesPort (Puerto OUT)
                         ↓
                PricePersistenceAdapter (Adapter OUT)
                         ↓
                 JpaPriceRepository → H2 (in-memory)
```

**Capas**

- **Dominio**: Entidades y reglas de negocio puras (sin Spring).
- **Aplicación**: Casos de uso que orquestan el dominio.
- **Adaptadores**: Web (REST) y Persistencia (JPA/H2).

**Reglas de negocio**

1. Se elige la tarifa con **mayor `priority`**.
2. En empate, la de **`startDate` más reciente**.

---

## Estructura del proyecto

```
src/main/java/com/inditex/inditex_iot_backend
├─ adapter
│  ├─ in/web
│  │  ├─ PricingController.java
│  │  ├─ GlobalExceptionHandler.java
│  │  ├─ dto/PriceResponse.java
│  │  └─ mapper/PriceDtoMapper.java
│  └─ out/persistence
│     ├─ PriceJpaEntity.java
│     ├─ JpaPriceRepository.java
│     └─ PricePersistenceAdapter.java
├─ application/usecase
│  └─ GetApplicablePriceService.java
├─ config
│  ├─ BeanConfig.java
│  └─ OpenApiConfig.java
└─ domain
   ├─ model/Price.java
   ├─ port/in/GetApplicablePriceUseCase.java
   ├─ port/out/LoadPricesPort.java
   └─ service/PriceSelector.java
```

---

## Requisitos

- Java 17 (Temurin/Adoptium recomendado).
- Maven Wrapper (incluido: `./mvnw`).

---

## Cómo ejecutar

### 1) Arrancar la aplicación

```bash
./mvnw spring-boot:run
```

Aplicación: `http://localhost:8080`  
Healthcheck: `http://localhost:8080/health`

### 2) Ejecutar como JAR

```bash
./mvnw clean package
java -jar target/inditex-iot-backend-0.0.1-SNAPSHOT.jar
```

---

## API

### GET `/prices`

Obtiene el **precio aplicable** para un `brandId`, `productId` y `applicationDate` (ISO-8601).

**Parámetros (query)**

- `brandId` (int, > 0)
- `productId` (long, > 0)
- `applicationDate` (ISO-8601, ej. `2020-06-14T16:00:00`)

**Ejemplo**

```bash
curl "http://localhost:8080/prices?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00"
```

**200 OK**

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}
```

**404 Not Found**: no hay tarifa aplicable.  
**400 Bad Request**: parámetros inválidos (respuesta en **Problem Details**, RFC 7807).

---

## Swagger / OpenAPI

- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

---

## Base de datos H2

**Consola H2** (si está habilitada): `http://localhost:8080/h2-console`

Config típica:

```yaml
spring:
  h2:
    console:
      enabled: true
  datasource:
    url: jdbc:h2:mem:pricingdb;MODE=LEGACY;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    password:
    driverClassName: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
  sql:
    init:
      mode: always
      continue-on-error: true
```

**Datos iniciales** (cargados vía `data.sql`):

| brandId | startDate           | endDate             | priceList | productId | priority | price | curr |
| ------: | ------------------- | ------------------- | --------: | --------: | -------: | ----: | :--- |
|       1 | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 |         1 |     35455 |        0 | 35.50 | EUR  |
|       1 | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 |         2 |     35455 |        1 | 25.45 | EUR  |
|       1 | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 |         3 |     35455 |        1 | 30.50 | EUR  |
|       1 | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 |         4 |     35455 |        1 | 38.95 | EUR  |

---

## Testing

Este proyecto separa **unit tests** y **integration tests** mediante Surefire/Failsafe.

### Unit tests (Surefire)

```bash
./mvnw test
```

Incluye:

- `PriceSelectorTest` – lógica de selección en dominio.
- `GetApplicablePriceServiceTest` – orquestación del caso de uso (mock del puerto OUT).

### Integration tests (Failsafe)

```bash
./mvnw verify
```

- `PricingControllerIT` – valida los **5 casos** del enunciado (end-to-end).

> Ejecutar **solo** los IT: `./mvnw -Dtest=*IT verify`

**Casos validados**

| Caso | applicationDate     | Esperado (priceList, price) |
| ---: | ------------------- | --------------------------- |
|    1 | 2020-06-14T10:00:00 | 1, 35.50 EUR                |
|    2 | 2020-06-14T16:00:00 | 2, 25.45 EUR                |
|    3 | 2020-06-14T21:00:00 | 1, 35.50 EUR                |
|    4 | 2020-06-15T10:00:00 | 3, 30.50 EUR                |
|    5 | 2020-06-16T21:00:00 | 4, 38.95 EUR                |

---

## Decisiones de diseño y eficiencia

- **Hexagonal**: dominio independiente de frameworks; puertos IN/OUT + adapters.
- **Selección en dominio**: persistencia filtra candidatos; la **regla de negocio** (priority y desempate por `startDate`) vive en el **dominio**.
- **Eficiencia**: la query usa `order by priority desc, startDate desc` y `PageRequest.of(0,1)` para traer **solo 1** candidato desde BD.
- **DTOs**: nunca exponemos entidades JPA fuera del adapter de persistencia.
- **Errores uniformes**: `@RestControllerAdvice` con **Problem Details** (RFC 7807).

---

## Extras implementados

- Validación con Bean Validation en `@RequestParam`.
- Respuestas 400/404 homogéneas (Problem Details).
- OpenAPI/Swagger UI 2.8.13.
- Separación de tests (Surefire/Failsafe) y escenarios 400/404.
- (Opcional) Pipeline CI: `mvn verify` en GitHub Actions.

---

## Futuras mejoras (no requeridas en la prueba)

- Endpoint de histórico/paginado (`GET /prices/history`).
- Observabilidad (Micrometer/Prometheus/Grafana).
- Dockerfile + docker-compose.
- Integración con Kafka (producer/consumer) para eventos de pricing.
- Autenticación/Autorización (Spring Security) si se expone públicamente.
