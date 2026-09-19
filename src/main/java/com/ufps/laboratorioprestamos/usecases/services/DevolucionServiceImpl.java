package com.ufps.laboratorioprestamos.usecases.services;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.exception.OperacionInvalidaException;
import com.ufps.laboratorioprestamos.domain.exception.PrestamoYaDevueltoException;
import com.ufps.laboratorioprestamos.domain.exception.ResourceNotFoundException;
import com.ufps.laboratorioprestamos.domain.model.Devolucion;
import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.domain.model.Novedad;
import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.domain.repository.DevolucionRepository;
import com.ufps.laboratorioprestamos.domain.repository.EquipoRepository;
import com.ufps.laboratorioprestamos.domain.repository.PrestamoRepository;
import com.ufps.laboratorioprestamos.domain.services.DevolucionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DevolucionServiceImpl implements DevolucionService {
    private final PrestamoRepository prestamoRepository;
    private final EquipoRepository equipoRepository;
    private final DevolucionRepository devolucionRepository;

    public DevolucionServiceImpl(PrestamoRepository prestamoRepository,
                                EquipoRepository equipoRepository,
                                DevolucionRepository devolucionRepository) {
        this.prestamoRepository = prestamoRepository;
        this.equipoRepository = equipoRepository;
        this.devolucionRepository = devolucionRepository;
    }

    @Override
    @Transactional
    public Devolucion registrarDevolucion(Long prestamoId, Novedad novedad, LocalDate fechaDevolucion) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResourceNotFoundException("Préstamo no encontrado."));
        if (!prestamo.estaActivo()) {
            throw new PrestamoYaDevueltoException("El préstamo ya fue devuelto.");
        }

        Equipo equipo = prestamo.getEquipo();
        prestamo.marcarDevuelto();
        prestamoRepository.save(prestamo);

        Devolucion devolucion = Devolucion.builder()
                .prestamo(prestamo)
                .fechaDevolucion(fechaDevolucion)
                .novedad(novedad)
                .build();

        if (novedad != null) {
            equipo.marcarBloqueado();
        } else {
            equipo.marcarDisponible();
        }
        equipoRepository.save(equipo);
        return devolucionRepository.save(devolucion);
    }

    @Override
    @Transactional
    public Equipo liberarEquipo(Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado."));
        if (!EstadoEquipo.BLOQUEADO.equals(equipo.getEstado())) {
            throw new OperacionInvalidaException("Solo puede liberarse un equipo bloqueado por novedad.");
        }
        equipo.marcarDisponible();
        return equipoRepository.save(equipo);
    }
}
