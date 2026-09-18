# ASSUMPTIONS.md

Este documento registra decisiones tomadas para completar la especificación cuando el enunciado no fue completamente preciso. Todo lo que aquí aparece es una decisión de implementación, no un requisito original del dominio.

## 1. Duración del préstamo por categoría
Se asume que cada categoría define un plazo fijo en días, representado por `diasPrestamo`. Esta decisión permite calcular la fecha límite automática a partir de la categoría del equipo y evita que un cliente envíe un plazo arbitrario.

## 2. Estados exactos del equipo
Se modelan los siguientes estados del dominio:
- `DISPONIBLE`
- `PRESTADO`
- `MANTENIMIENTO`
- `BLOQUEADO`

La disponibilidad del equipo se define exclusivamente por el estado del mismo, sin duplicar lógica en controladores ni consultas ad hoc.

## 3. Qué significa "liberar equipo"
Se define una operación explícita `liberarEquipo()` que transforma un equipo bloqueado por novedad en `DISPONIBLE` cuando el responsable decide dejarlo operativo nuevamente.

## 4. Formato de identificadores
Se usa `Long` como identificador para las entidades principales. Es compatible con JPA, simple de manejar y suficiente para el alcance de la prueba.

## 5. Comportamiento ante fechas
- La fecha actual se toma con `LocalDate.now()`.
- La consulta de préstamos vencidos compara la fecha límite contra la fecha actual.
- Un préstamo se considera vencido únicamente cuando la fecha actual supera la fecha límite.
- No se almacena un número de días de atraso porque puede calcularse dinámicamente.

## 6. Qué ocurre si se intenta devolver un préstamo ya devuelto
Se lanza una excepción de negocio `PrestamoYaDevueltoException` para proteger la integridad del flujo y evitar devoluciones duplicadas.

## 7. Novedades permitidas
Se aceptan dos novedades mínimas:
- `DAÑO`
- `FALTANTE`

Si se registra una novedad, el equipo queda en `BLOQUEADO` y no puede prestarse hasta que se ejecute la liberación.

## 8. Uso del perfil de pruebas
Para no depender de PostgreSQL en la ejecución de tests, se crea un perfil de pruebas con H2. La aplicación principal sigue configurada para PostgreSQL mediante variables de entorno.

## 9. Consulta obligatoria de vencidos
La consulta devuelve el préstamo y el número de días de atraso calculado directamente entre la fecha límite y la fecha actual, sin persistir ese valor redundante en la base de datos.

## 10. Implementación del dominio en español
Se decide que los nombres del negocio se mantengan en español incluso cuando la infraestructura usa Spring, JPA y Hibernate. Esto favorece la defensa oral y la claridad del código.
