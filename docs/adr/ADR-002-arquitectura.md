# ADR-002: Arquitectura

## Contexto
El proyecto gestiona préstamos de equipos de laboratorio con reglas de negocio claras, validaciones de dominio y una API REST. La solución debía mantener separadas las responsabilidades entre negocio, casos de uso e infraestructura para facilitar el mantenimiento, la prueba y la defensa técnica.

## Problema
Si se mezclaban el modelo de negocio, la lógica de aplicación, la persistencia y la capa REST, la aplicación se volvería más compleja, menos testeable y más difícil de explicar con claridad.

## Decisión
Se adoptó una arquitectura orientada por capas con la estructura real del proyecto:
- `domain.model`: modelos de negocio y DTOs internos del dominio.
- `domain.repository`: puertos/repositorios del dominio.
- `domain.services`: interfaces/contratos de servicios del negocio.
- `domain.exception`: excepciones de negocio.
- `usecases.services`: implementaciones de servicios/casos de uso.
- `infrastructure.entity`: entidades JPA.
- `infrastructure.repository`: implementaciones concretas de repositorios.
- `infrastructure.converter`: conversores entre dominio y JPA.
- `infrastructure.rest.controller`: controladores REST.
- `infrastructure.rest.dto.request`: DTOs de entrada HTTP.
- `infrastructure.rest.dto.response`: DTOs de salida HTTP.
- `infrastructure.rest.converter`: conversores hacia/desde la capa REST.

Además, se delimitó la diferencia entre:
- modelos de dominio: representan entidades y reglas del negocio;
- DTOs internos del dominio: resultados de consulta o transferencias internas del caso de uso;
- DTOs de entrada REST: contratos HTTP de entrada;
- DTOs de salida REST: contratos HTTP de salida.

## Alternativas consideradas
- Arquitectura monolítica sin capas claras.
- Arquitectura por capas sin dominio explícito.
- Mezclar DTOs de dominio y DTOs REST en el mismo paquete.

## Alternativas descartadas
Se descartó la solución monolítica porque acopla negocio, infraestructura y presentación y dificulta la evolución del proyecto.
Se descartó una capa de servicio sin dominio explícito porque debilita la separación entre reglas del negocio y detalles técnicos.
Se descartó mezclar DTOs del dominio con DTOs HTTP porque genera confusión de responsabilidades y reduce la trazabilidad del flujo de datos.

## Consecuencias
- La lógica de negocio queda organizada y más fácil de probar.
- La persistencia queda desacoplada del modelo del negocio.
- La API REST queda definida por contratos específicos de entrada y salida.
- La arquitectura es más legible y defendible en la explicación oral del proyecto.
