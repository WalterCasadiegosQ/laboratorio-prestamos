package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.EstadoPrestamo;
import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.domain.repository.PrestamoRepository;
import com.ufps.laboratorioprestamos.infrastructure.converter.PrestamoConverter;
import com.ufps.laboratorioprestamos.infrastructure.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository {
    private final PrestamoJpaRepository jpaRepository;
    private final PrestamoConverter mapper;

    public PrestamoRepositoryImpl(PrestamoJpaRepository jpaRepository, PrestamoConverter mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(prestamo)));
    }

    @Override
    public List<Prestamo> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Prestamo> findActiveByPersonId(Long personaId) {
        return jpaRepository.findActiveByPersonId(personaId, EstadoPrestamo.ACTIVO).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Prestamo> findActiveOverdue(LocalDate hoy) {
        return jpaRepository.findActiveOverdue(hoy, EstadoPrestamo.ACTIVO).stream().map(mapper::toDomain).toList();
    }
}

interface PrestamoJpaRepository extends JpaRepository<PrestamoEntity, Long> {
    @Query("select p from PrestamoEntity p where p.persona.id = :personaId and p.estado = :estado")
    List<PrestamoEntity> findActiveByPersonId(Long personaId, EstadoPrestamo estado);

    @Query("select p from PrestamoEntity p where p.estado = :estado and p.fechaVencimiento < :hoy")
    List<PrestamoEntity> findActiveOverdue(LocalDate hoy, EstadoPrestamo estado);
}
