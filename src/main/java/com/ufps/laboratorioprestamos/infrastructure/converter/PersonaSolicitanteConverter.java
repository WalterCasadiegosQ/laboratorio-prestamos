package com.ufps.laboratorioprestamos.infrastructure.converter;

import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;
import com.ufps.laboratorioprestamos.infrastructure.entity.PersonaSolicitanteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonaSolicitanteConverter {
    PersonaSolicitante toDomain(PersonaSolicitanteEntity entity);
    PersonaSolicitanteEntity toEntity(PersonaSolicitante domain);
}
