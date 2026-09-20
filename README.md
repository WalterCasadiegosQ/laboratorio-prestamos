# Laboratorio Préstamos

## Qué es el sistema
Este proyecto implementa una API REST para gestionar préstamos, devoluciones, disponibilidad y liberación de equipos de laboratorio. Permite registrar categorías, equipos, personas solicitantes, préstamos activos, devoluciones con novedades y consultar préstamos vencidos con días de atraso calculados.

## Problema que resuelve
Antes, los préstamos se llevaban en cuadernos y se registraba la información de forma dispersa. La API centraliza la operación, evita préstamos indebidos y controla devoluciones con novedades para proteger la disponibilidad real del inventario.

## Alcance
El sistema cubre:
- registro y consulta de categorías de equipo;
- registro y consulta de equipos;
- registro y consulta de personas solicitantes;
- registro de préstamos y control de disponibilidad;
- registro de devoluciones con novedad opcional;
- bloqueo y liberación de equipos;
- consulta de préstamos vencidos.

## Arquitectura
Se utiliza una versión adaptada de Clean Architecture, con separación clara en:
- `domain`: modelos, reglas, repositorios y excepciones del negocio.
- `usecases`: coordinación y lógica de negocio en servicios (`*ServiceImpl`).
- `infrastructure`: persistencia JPA, conversiones, configuración y API REST.

## Tecnologías
- Java 17
- Spring Boot 4.1.1
- Maven
- PostgreSQL
- Spring Data JPA
- Hibernate
- Bean Validation
- Lombok
- MapStruct
- Springdoc OpenAPI
- JUnit 5

## Estructura principal
```text
src/main/java/com/ufps/laboratorioprestamos
├── domain
│   ├── exception
│   ├── model
│   ├── repository
│   └── services
├── usecases
│   └── services
├── infrastructure
│   ├── config
│   ├── converter
│   ├── entity
│   ├── repository
│   └── rest
│       ├── controller
│       ├── converter
│       ├── dto
│       │   ├── request
│       │   └── response
│       └── ...
```

## Reglas de negocio
- Un solicitante con un préstamo vencido activo no puede solicitar otro equipo.
- Un equipo en mantenimiento no aparece como disponible.
- El plazo del préstamo depende de la categoría del equipo.
- Un equipo bloqueado por novedad no puede prestarse hasta que se libere explícitamente.
- La consulta de préstamos vencidos calcula los días de atraso a partir de la fecha límite y la fecha actual.
- La devolución con novedad genera bloqueo del equipo y exige liberación posterior para volver a disponibilidad.

## Configuración de PostgreSQL
Se recomienda crear una base de datos llamada `laboratorio_prestamos`.

Variables de entorno opcionales:
- `DB_URL=jdbc:postgresql://localhost:5432/laboratorio_prestamos`
- `DB_USERNAME=postgres`
- `DB_PASSWORD=postgres`

Si no se configuran, el perfil de pruebas usa H2 para no depender de PostgreSQL durante la validación automática.

## Cómo ejecutar
1. Clonar el proyecto.
2. Crear la base de datos PostgreSQL si se va a usar el perfil principal.
3. Definir las variables de entorno adecuadas.
4. Ejecutar:

```bash
./mvnw spring-boot:run
```

## Cómo ejecutar tests
```bash
./mvnw test
```

## Swagger
La documentación OpenAPI queda disponible en:
- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/api-docs`

## Endpoints principales
- `POST /api/categorias`
- `GET /api/categorias`
- `POST /api/equipos`
- `GET /api/equipos`
- `GET /api/equipos/disponibles`
- `POST /api/personas-solicitantes`
- `GET /api/personas-solicitantes`
- `POST /api/prestamos`
- `GET /api/prestamos`
- `GET /api/prestamos-vencidos`
- `POST /api/devoluciones`
- `POST /api/equipos/{id}/liberar`

## Funcionalidades fuera de alcance
- Frontend
- Autenticación y autorización avanzada
- Pagos
- Integraciones externas
- Microservicios
- Caches, eventos o mensajería asincrónica para este alcance

## Decisiones clave de implementación
- El dominio usa nombres en español y se mantiene independiente de Spring/JPA.
- Los servicios del dominio están en interfaces de `domain.services` y las implementaciones en `usecases.services` con sufijo `Impl`.
- Los repositorios del dominio son contratos; las implementaciones en infraestructura terminan con `Impl`.
- Los DTOs REST se usan para entrada/salida; no se exponen entidades JPA.
- El cálculo de días de atraso se hace al consultar y no se persiste de forma redundante.
