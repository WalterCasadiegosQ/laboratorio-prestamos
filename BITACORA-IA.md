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