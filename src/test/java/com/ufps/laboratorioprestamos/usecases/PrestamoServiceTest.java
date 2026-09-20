package com.ufps.laboratorioprestamos.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.exception.EquipoBloqueadoException;
import com.ufps.laboratorioprestamos.domain.exception.EquipoEnMantenimientoException;
import com.ufps.laboratorioprestamos.domain.exception.PersonaConPrestamoVencidoException;
import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;
import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.domain.model.Novedad;
import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;
import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.domain.services.CategoriaEquipoService;
import com.ufps.laboratorioprestamos.domain.services.DevolucionService;
import com.ufps.laboratorioprestamos.domain.services.EquipoService;
import com.ufps.laboratorioprestamos.domain.services.PersonaSolicitanteService;
import com.ufps.laboratorioprestamos.domain.services.PrestamoService;

import com.ufps.laboratorioprestamos.infrastructure.config.LaboratorioPrestamosApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = LaboratorioPrestamosApplication.class)
@ActiveProfiles("test")
@Transactional
class PrestamoServiceTest {

    @Autowired
    private CategoriaEquipoService categoriaEquipoService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private PersonaSolicitanteService personaSolicitanteService;

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private DevolucionService devolucionService;

    private CategoriaEquipo categoria;

    @BeforeEach
    void setUp() {
        categoria = categoriaEquipoService.registrarCategoria("Portátiles", 7);
    }

    @Test
    void solicitanteConPrestamoVencidoNoPuedeSolicitarOtroEquipo() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Ana", "123", "ana@test.com");
        Equipo primerEquipo = equipoService.registrarEquipo("E-001", "Laptop 1", categoria.getId());
        prestamoService.registrarPrestamo(persona.getId(), primerEquipo.getId(), LocalDate.now().minusDays(15));

        Equipo segundoEquipo = equipoService.registrarEquipo("E-002", "Laptop 2", categoria.getId());
        assertThrows(PersonaConPrestamoVencidoException.class,
                () -> prestamoService.registrarPrestamo(persona.getId(), segundoEquipo.getId(), LocalDate.now()));
    }

    @Test
    void equipoEnMantenimientoNoPuedePrestarse() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Luis", "456", "luis@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-010", "Osciloscopio", categoria.getId());
        equipoService.marcarEnMantenimiento(equipo.getId());

        assertThrows(EquipoEnMantenimientoException.class,
                () -> prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.now()));
    }

    @Test
    void plazoDelPrestamoSeCalculaSegunLaCategoria() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Sara", "789", "sara@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-020", "Kit sensor", categoria.getId());

        Prestamo prestamo = prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.of(2026, 9, 1));

        assertEquals(LocalDate.of(2026, 9, 8), prestamo.getFechaVencimiento());
    }

    @Test
    void devolucionConDanioBloqueaElEquipo() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Carlos", "101", "carlos@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-030", "Portatil C", categoria.getId());
        Prestamo prestamo = prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.now().minusDays(2));

        assertNotNull(devolucionService.registrarDevolucion(prestamo.getId(), Novedad.DAÑO, LocalDate.now()));
        assertEquals(EstadoEquipo.BLOQUEADO, equipoService.findAll().stream()
                .filter(item -> item.getId().equals(equipo.getId()))
                .findFirst()
                .orElseThrow()
                .getEstado());
    }

    @Test
    void devolucionConFaltanteBloqueaElEquipo() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Marta", "202", "marta@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-040", "Portatil D", categoria.getId());
        Prestamo prestamo = prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.now().minusDays(3));

        assertNotNull(devolucionService.registrarDevolucion(prestamo.getId(), Novedad.FALTANTE, LocalDate.now()));
        assertEquals(EstadoEquipo.BLOQUEADO, equipoService.findAll().stream()
                .filter(item -> item.getId().equals(equipo.getId()))
                .findFirst()
                .orElseThrow()
                .getEstado());
    }

    @Test
    void equipoBloqueadoNoPuedePrestarse() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Pedro", "303", "pedro@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-050", "Portatil E", categoria.getId());
        Prestamo prestamo = prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.now().minusDays(2));
        devolucionService.registrarDevolucion(prestamo.getId(), Novedad.DAÑO, LocalDate.now());

        PersonaSolicitante otraPersona = personaSolicitanteService.registrarPersonaSolicitante("Nina", "404", "nina@test.com");
        assertThrows(EquipoBloqueadoException.class,
                () -> prestamoService.registrarPrestamo(otraPersona.getId(), equipo.getId(), LocalDate.now()));
    }

    @Test
    void equipoLiberadoPuedeVolverAEstarDisponible() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Julia", "505", "julia@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-060", "Portatil F", categoria.getId());
        Prestamo prestamo = prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.now().minusDays(2));
        devolucionService.registrarDevolucion(prestamo.getId(), Novedad.DAÑO, LocalDate.now());

        equipoService.liberarEquipo(equipo.getId());

        assertEquals(EstadoEquipo.DISPONIBLE, equipoService.findAll().stream()
                .filter(item -> item.getId().equals(equipo.getId()))
                .findFirst()
                .orElseThrow()
                .getEstado());
    }

    @Test
    void consultaDePrestamosVencidosCalculaDiasDeAtraso() {
        PersonaSolicitante persona = personaSolicitanteService.registrarPersonaSolicitante("Rosa", "606", "rosa@test.com");
        Equipo equipo = equipoService.registrarEquipo("E-070", "Portatil G", categoria.getId());
        prestamoService.registrarPrestamo(persona.getId(), equipo.getId(), LocalDate.now().minusDays(15));

        assertEquals(8, prestamoService.consultarPrestamosVencidos().get(0).getDiasDeAtraso());
    }
}
