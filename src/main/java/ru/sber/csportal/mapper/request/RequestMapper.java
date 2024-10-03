package ru.sber.csportal.mapper.request;

import org.mapstruct.MappingTarget;

public interface RequestMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    E partialUpdate(D dto, E entity);
}
