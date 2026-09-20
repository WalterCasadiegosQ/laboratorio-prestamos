# BITACORA-IA.md

## Sesión: primera publicación del proyecto

- Fecha: 2026-09-18
- Objetivo: realizar la primera publicación progresiva del sistema de préstamo de equipos de laboratorio, estableciendo la estructura base del proyecto y el modelo inicial del dominio.

### Acciones realizadas

- Creación y organización de la estructura base del proyecto.
- Definición de la separación entre las capas `domain`, `usecases` e `infrastructure`.
- Construcción de los modelos principales del dominio relacionados con categorías, equipos y personas solicitantes.
- Definición de las interfaces de repositorio y servicios del dominio.
- Configuración inicial del proyecto con Java 17, Spring Boot y Maven.
- Incorporación de la documentación base del proyecto y de las decisiones arquitectónicas iniciales.

### Decisiones

- Mantener una arquitectura orientada a la separación de responsabilidades.
- Mantener los modelos de negocio independientes de la infraestructura de persistencia.
- Utilizar PostgreSQL como base de datos principal y H2 para las pruebas.
- Mantener los nombres del dominio en español para facilitar la correspondencia con el problema planteado.

### Verificación

- Compilación exitosa del proyecto mediante Maven.
- Ejecución exitosa de las pruebas automatizadas utilizando el perfil `test`.

## Siguiente avance

- Fecha prevista: 2026-09-19
- Objetivo: incorporar la capa de persistencia y los casos de uso que permitan conectar el dominio con la infraestructura.

### Trabajo previsto

- Incorporar las entidades JPA correspondientes al dominio.
- Implementar los repositorios de infraestructura.
- Incorporar los converters entre entidades de persistencia y modelos de dominio.
- Implementar los servicios de casos de uso.
- Configurar la conexión de persistencia para los perfiles correspondientes.
- Documentar las decisiones arquitectónicas relacionadas con la separación entre dominio y persistencia mediante `ADR-002`.

### Verificación prevista

- Compilación del proyecto.
- Ejecución de las pruebas automatizadas.
- Verificación de la integración entre los casos de uso y la persistencia.

## Siguiente avance

- Fecha prevista: 2026-09-20
- Objetivo: completar la exposición REST del sistema, incorporar las pruebas finales y consolidar la documentación técnica del proyecto.

### Trabajo previsto

- Incorporar los controladores REST y los DTO de entrada y salida.
- Completar la conversión entre los modelos internos y las respuestas REST.
- Incorporar las pruebas correspondientes a los principales flujos y reglas de negocio.
- Completar `README.md` con la descripción, configuración, arquitectura y endpoints del sistema.
- Incorporar `ADR-003` para documentar las decisiones técnicas relacionadas con la separación entre modelos de dominio, entidades JPA y DTO REST.

### Verificación prevista

- Ejecución completa de las pruebas automatizadas.
- Verificación de los principales endpoints mediante la API REST.
- Validación del flujo completo de préstamo y devolución.
- Verificación final de compilación y ejecución del proyecto.