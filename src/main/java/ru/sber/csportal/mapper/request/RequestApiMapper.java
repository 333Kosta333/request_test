package ru.sber.csportal.mapper.request;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.sber.csportal.dto.request.RequestApiDto;
import ru.sber.csportal.entity.request.RequestEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface RequestApiMapper extends RequestMapper<RequestApiDto, RequestEntity> {
    @Mapping(source = "contractUvhdNumber", target = "contract.uvhdNumber")
    @Mapping(source = "contractExternalNumber", target = "contract.externalNumber")
    @Mapping(source = "requestStatusName", target = "requestStatus.name")
    @Mapping(source = "requestStatusId", target = "requestStatus.id")
    @Mapping(source = "requestTypeDescription", target = "requestType.description")
    @Mapping(source = "requestTypeName", target = "requestType.name")
    @Override
    RequestEntity toEntity(RequestApiDto requestApiDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    RequestApiDto toDto(RequestEntity requestEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name = "toEntity")
    @Override
    RequestEntity partialUpdate(RequestApiDto requestApiDto, @MappingTarget RequestEntity requestEntity);
}