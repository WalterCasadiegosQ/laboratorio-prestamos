package com.ufps.laboratorioprestamos.infrastructure.converter;

import com.ufps.laboratorioprestamos.domain.model.Devolucion;
import com.ufps.laboratorioprestamos.infrastructure.entity.DevolucionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = PrestamoConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DevolucionConverter {
    Devolucion toDomain(DevolucionEntity entity);
    DevolucionEntity toEntity(Devolucion domain);
}
