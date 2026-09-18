# ADR-001: Herramienta de IA

## Contexto
La prueba técnica requiere un proceso de desarrollo con evidencia, una revisión crítica del diseño y una documentación razonada del proyecto. En este contexto, se necesitó una herramienta de apoyo para analizar la estructura actual, revisar responsabilidades entre capas y mantener una bitácora técnica honesta.

## Problema
Se requería una herramienta de IA que apoyara la revisión del código, la identificación de inconsistencias arquitectónicas y la documentación sin sustituir la decisión final del desarrollador.

## Decisión
Se utilizó GitHub Copilot como apoyo para:
- revisar la estructura existente del proyecto;
- analizar la separación entre domain, usecases e infrastructure;
- detectar inconsistencias de capas, nombres y responsabilidades;
- proponer ajustes razonables de diseño y documentación;
- apoyar la redacción de ADRs y la bitácora de trabajo.

La herramienta se empleó como apoyo de análisis y validación, pero las decisiones finales sobre arquitectura, diseño y entregables fueron tomadas por el desarrollador.

## Alternativas consideradas
- No usar IA durante el desarrollo.
- Usar IA sin mantener una revisión crítica del resultado.

## Alternativas descartadas
Se descartó no usar IA porque la prueba exige usar evidencia y apoyo técnico para justificar decisiones de arquitectura, especialmente en un proyecto con varias capas y responsabilidades bien definidas.
Se descartó usar IA sin revisión humana porque puede generar sugerencias útiles pero no verificadas, lo que es incompatible con la necesidad de mantener una documentación honesta y defensable.

## Consecuencias
- Se obtuvo una revisión más clara de la arquitectura y del flujo de dominio, casos de uso e infraestructura.
- Se redujo el riesgo de dejar inconsistencias de packaging o nombres sin detectar.
- La documentación quedó más sustentada, pero siempre bajo control humano de la decisión final.
- La IA fue un apoyo de trabajo, no un sustituto de la responsabilidad técnica del desarrollador.
