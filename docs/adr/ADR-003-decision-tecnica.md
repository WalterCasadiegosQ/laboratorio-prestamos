# ADR-003: Decisión técnica

## Contexto
La aplicación requiere separar la lógica de negocio de la persistencia y de la capa REST. El modelo de negocio no debe depender de JPA ni de detalles HTTP, y la aplicación ya usa conversiones con MapStruct para evitar mapeos manuales repetitivos.

## Problema
Usar las mismas clases para dominio, persistencia y REST mezclaría responsabilidades, dificultaría la prueba del negocio y haría más frágil la evolución del sistema.

## Decisión
Se definieron modelos de dominio independientes de las entidades JPA. La persistencia se modela en `infrastructure.entity`, mientras el negocio queda en `domain.model` y `domain.services`.

La conversión entre capas se realiza con conversores y MapStruct:
- `infrastructure.converter` para conversión entre dominio y entidades JPA;
- `infrastructure.rest.converter` para convertir resultados internos del negocio a DTOs REST;
- respuesta HTTP en `infrastructure.rest.dto.response`.

Como ejemplo del patrón, `PrestamoVencidoDto` representa el resultado interno del caso de uso, mientras `PrestamoVencidoResponse` representa la salida HTTP. La conversión entre ambos se hace en `RestConverter`.

## Alternativas consideradas
- Usar entidades JPA como modelo de dominio.
- Realizar conversiones manuales sin MapStruct.
- Integrar DTOs del dominio y DTOs REST en el mismo nivel.

## Alternativas descartadas
Se descartó usar entidades JPA como modelo de dominio porque rompe la intención de mantener el negocio independiente de infraestructura.
Se descartó el mapeo manual repetitivo porque MapStruct reduce ruido, mejora mantenibilidad y mantiene una conversión más consistente entre capas.
Se descartó reutilizar DTOs internos como contratos REST porque mezcla dos niveles distintos de responsabilidad.

## Consecuencias
- El dominio queda más puro y fácil de entender.
- La persistencia y la API quedan desacopladas del modelo del negocio.
- Los cambios técnicos en JPA o REST no tienen que implicar cambios en las reglas del negocio.
- El flujo de datos queda más claro: dominio → caso de uso → DTO interno → conversor REST → respuesta HTTP.
