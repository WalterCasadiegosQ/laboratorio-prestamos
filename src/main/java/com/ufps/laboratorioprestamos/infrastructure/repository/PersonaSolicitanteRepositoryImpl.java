package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;
import com.ufps.laboratorioprestamos.domain.repository.PersonaSolicitanteRepository;
import com.ufps.laboratorioprestamos.infrastructure.entity.PersonaSolicitanteEntity;
import com.ufps.laboratorioprestamos.infrastructure.converter.PersonaSolicitanteConverter;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaSolicitanteRepositoryImpl implements PersonaSolicitanteRepository {
    private final PersonaSolicitanteJpaRepository jpaRepository;
    private final PersonaSolicitanteConverter mapper;

    public PersonaSolicitanteRepositoryImpl(PersonaSolicitanteJpaRepository jpaRepository, PersonaSolicitanteConverter mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonaSolicitante save(PersonaSolicitante personaSolicitante) {
        PersonaSolicitanteEntity entity = mapper.toEntity(personaSolicitante);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<PersonaSolicitante> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<PersonaSolicitante> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
