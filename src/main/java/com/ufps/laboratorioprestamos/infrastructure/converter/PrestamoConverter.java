package com.ufps.laboratorioprestamos.infrastructure.converter;

import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.infrastructure.entity.PrestamoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {PersonaSolicitanteConverter.class, EquipoConverter.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrestamoConverter {
    Prestamo toDomain(PrestamoEntity entity);

    @Mapping(target = "persona", source = "persona")
    @Mapping(target = "equipo", source = "equipo")
    PrestamoEntity toEntity(Prestamo domain);
}
