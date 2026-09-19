package com.ufps.laboratorioprestamos.usecases.services;

import java.time.LocalDate;
import java.util.List;

import com.ufps.laboratorioprestamos.domain.exception.EquipoBloqueadoException;
import com.ufps.laboratorioprestamos.domain.exception.EquipoEnMantenimientoException;
import com.ufps.laboratorioprestamos.domain.exception.EquipoNoDisponibleException;
import com.ufps.laboratorioprestamos.domain.exception.PersonaConPrestamoVencidoException;
import com.ufps.laboratorioprestamos.domain.exception.ResourceNotFoundException;
import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.domain.model.EstadoPrestamo;
import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;
import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.domain.model.PrestamoVencidoDto;
import com.ufps.laboratorioprestamos.domain.repository.EquipoRepository;
import com.ufps.laboratorioprestamos.domain.repository.PersonaSolicitanteRepository;
import com.ufps.laboratorioprestamos.domain.repository.PrestamoRepository;
import com.ufps.laboratorioprestamos.domain.services.PrestamoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    private final PersonaSolicitanteRepository personaSolicitanteRepository;
    private final EquipoRepository equipoRepository;
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PersonaSolicitanteRepository personaSolicitanteRepository,
                              EquipoRepository equipoRepository,
                              PrestamoRepository prestamoRepository) {
        this.personaSolicitanteRepository = personaSolicitanteRepository;
        this.equipoRepository = equipoRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(Long personaId, Long equipoId, LocalDate fechaPrestamo) {
        PersonaSolicitante persona = personaSolicitanteRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitante no encontrado."));
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado."));

        if (equipo.getEstado() == EstadoEquipo.MANTENIMIENTO) {
            throw new EquipoEnMantenimientoException("El equipo se encuentra en mantenimiento.");
        }
        if (equipo.getEstado() == EstadoEquipo.BLOQUEADO) {
            throw new EquipoBloqueadoException("El equipo está bloqueado debido a una novedad en la devolución.");
        }
        if (!equipo.estaDisponibleParaPrestamo()) {
            throw new EquipoNoDisponibleException("El equipo no está disponible.");
        }

        LocalDate hoy = LocalDate.now();
        boolean tienePrestamoVencido = prestamoRepository.findActiveByPersonId(personaId).stream()
                .anyMatch(prestamo -> prestamo.estaVencido(hoy));
        if (tienePrestamoVencido) {
            throw new PersonaConPrestamoVencidoException("El solicitante tiene un préstamo vencido sin devolver.");
        }

        LocalDate fechaVencimiento = fechaPrestamo.plusDays(equipo.getCategoria().getDiasPrestamo());

        Prestamo prestamo = Prestamo.builder()
                .persona(persona)
                .equipo(equipo)
                .fechaPrestamo(fechaPrestamo)
                .fechaVencimiento(fechaVencimiento)
                .estado(EstadoPrestamo.ACTIVO)
                .build();

        equipo.marcarPrestado();
        equipoRepository.save(equipo);
        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<PrestamoVencidoDto> consultarPrestamosVencidos() {
        LocalDate hoy = LocalDate.now();
        return prestamoRepository.findActiveOverdue(hoy).stream()
                .map(prestamo -> PrestamoVencidoDto.builder()
                        .prestamoId(prestamo.getId())
                        .personaId(prestamo.getPersona().getId())
                        .personaNombre(prestamo.getPersona().getNombreCompleto())
                        .equipoId(prestamo.getEquipo().getId())
                        .equipoCodigo(prestamo.getEquipo().getCodigo())
                        .fechaVencimiento(prestamo.getFechaVencimiento())
                        .diasDeAtraso(prestamo.diasDeAtraso(hoy))
                        .build())
                .toList();
    }
}
