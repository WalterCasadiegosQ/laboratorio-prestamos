# AGENTS.md

## Objetivo
Construir y mantener una API REST profesional para la gestión de préstamos de equipos de laboratorio, con orientación a dominio, reglas de negocio explícitas y una arquitectura coherente para defensa oral.

## Sistema asignado
Sistema A — Préstamo de equipos de laboratorio.

## Stack
- Java 17
- Spring Boot 4.1.1
- Maven
- PostgreSQL
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Bean Validation
- Lombok
- MapStruct
- Springdoc OpenAPI
- JUnit 5

## Arquitectura
Se adopta una estructura basada en capas, con la siguiente organización:

- `domain`
  - `model`: modelos del negocio en español (`Equipo`, `CategoriaEquipo`, `PersonaSolicitante`, `Prestamo`, `Devolucion`, `Novedad`)
  - `repository`: contratos del dominio sin extender Spring Data
  - `exception`: excepciones de negocio
  - `services`: interfaces de servicios del dominio
- `usecases`
  - `services`: implementaciones de los servicios de casos de uso (`*ServiceImpl`)
- `infrastructure`
  - `config`: configuración general y OpenAPI
  - `entity`: entidades JPA
  - `repository`: implementaciones JPA de los repositorios del dominio (`*RepositoryImpl`)
  - `converter`: conversores entre dominio y persistencia
  - `rest`
    - `controller`: controladores REST
    - `dto.request`: DTOs de entrada
    - `dto.response`: DTOs de salida
    - `converter`: conversores DTO ↔ dominio

## Principios clave
- El dominio no depende de Spring, JPA, Hibernate ni REST.
- Las entidades JPA no se exponen al dominio ni a la API.
- Los controllers son delgados y delegan en servicios de casos de uso.
- Los repositorios del dominio son puertos; las implementaciones JPA viven en `infrastructure.repository`.
- Los servicios del dominio se definen en `domain.services`; las implementaciones quedan en `usecases.services` con sufijo `Impl`.
- Los repositorios de infraestructura usan sufijo `Impl` y no `Adapter`.

## Estructura de paquetes real
```text
com.ufps.laboratorioprestamos
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
└── docs/
```

## Reglas de negocio finales
1. Un solicitante con un préstamo vencido sin devolver no puede solicitar otro equipo.
2. Un equipo en mantenimiento no aparece como disponible.
3. El plazo del préstamo depende de la categoría del equipo y no del solicitante.
4. Una devolución con novedad bloquea el equipo.
5. Los estados válidos son: `DISPONIBLE`, `PRESTADO`, `MANTENIMIENTO`, `BLOQUEADO`.
6. La consulta de préstamos vencidos calcula los días de atraso en base a la fecha límite y la fecha actual, sin persistir ese valor redundante.
7. La liberación es una operación explícita para volver un equipo bloqueado a disponible.

## Restricciones
- No se implementa frontend ni autenticación avanzada.
- No se agregan dependencias innecesarias.
- No se debe esconder lógica de negocio en controllers.
- No se exponen entidades JPA directamente en la API.
- Se debe mantener la aplicación pequeña, profesional y defendible.

## Convenciones de código
- Nombres del negocio en español y consistentes.
- MapStruct para conversiones repetitivas DTO ↔ dominio ↔ entidad.
- Lombok para reducir boilerplate sin abusar de `@Data`.
- Bean Validation para validación de entrada.
- Manejo centralizado de errores con respuestas HTTP en español.

## Cómo ejecutar
1. Crear la base de datos PostgreSQL `laboratorio_prestamos` o usar el perfil de prueba con H2.
2. Configurar variables de entorno si se usa PostgreSQL:
   - `DB_URL=jdbc:postgresql://localhost:5432/laboratorio_prestamos`
   - `DB_USERNAME=postgres`
   - `DB_PASSWORD=postgres`
3. Ejecutar:
   `./mvnw spring-boot:run`

## Cómo ejecutar tests
`./mvnw test`

## Swagger / OpenAPI
- La API queda documentada con Springdoc OpenAPI.
- Swagger UI está disponible en `http://localhost:8080/swagger-ui.html`.

## Reglas importantes
- Las decisiones ambiguas deben registrarse en `ASSUMPTIONS.md`.
- Los ADR deben reflejar decisiones relevantes y no inventar hechos históricos.
- La documentación debe mantenerse fiel al código real.
- No se deben introducir patrones innecesarios ni complejidad artificial.
- La nomenclatura final exige `*ServiceImpl` y `*RepositoryImpl`.
