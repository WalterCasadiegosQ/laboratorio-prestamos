package com.ufps.laboratorioprestamos.usecases.services;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.exception.BusinessRuleException;
import com.ufps.laboratorioprestamos.domain.exception.ResourceNotFoundException;
import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;
import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.domain.repository.CategoriaEquipoRepository;
import com.ufps.laboratorioprestamos.domain.repository.EquipoRepository;
import com.ufps.laboratorioprestamos.domain.services.EquipoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;
    private final CategoriaEquipoRepository categoriaEquipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository, CategoriaEquipoRepository categoriaEquipoRepository) {
        this.equipoRepository = equipoRepository;
        this.categoriaEquipoRepository = categoriaEquipoRepository;
    }

    @Override
    public Equipo registrarEquipo(String codigo, String nombre, Long categoriaId) {
        if (codigo == null || codigo.isBlank()) {
            throw new BusinessRuleException("El código del equipo es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new BusinessRuleException("El nombre del equipo es obligatorio.");
        }
        CategoriaEquipo categoria = categoriaEquipoRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada."));

        Equipo equipo = Equipo.builder()
                .codigo(codigo.trim())
                .nombre(nombre.trim())
                .categoria(categoria)
                .estado(EstadoEquipo.DISPONIBLE)
                .build();

        return equipoRepository.save(equipo);
    }

    @Override
    @Transactional
    public Equipo actualizarEstado(Long equipoId, EstadoEquipo estado) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado."));
        if (estado == null) {
            throw new BusinessRuleException("El estado del equipo es obligatorio.");
        }
        equipo.setEstado(estado);
        return equipoRepository.save(equipo);
    }

    @Override
    @Transactional
    public Equipo marcarEnMantenimiento(Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado."));
        equipo.marcarEnMantenimiento();
        return equipoRepository.save(equipo);
    }

    @Override
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    @Override
    public List<Equipo> consultarEquiposDisponibles() {
        return equipoRepository.findByEstado(EstadoEquipo.DISPONIBLE);
    }

    @Override
    @Transactional
    public Equipo liberarEquipo(Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado."));
        if (!EstadoEquipo.BLOQUEADO.equals(equipo.getEstado())) {
            throw new BusinessRuleException("El equipo no está bloqueado por una novedad.");
        }
        equipo.marcarDisponible();
        return equipoRepository.save(equipo);
    }
}
