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
import ru.sber.csportal.dto.request.RequestUiDto;
import ru.sber.csportal.entity.request.RequestEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface RequestUiMapper extends RequestMapper<RequestUiDto, RequestEntity> {
    @Mapping(source = "contractUvhdNumber", target = "contract.uvhdNumber")
    @Mapping(source = "contractExternalNumber", target = "contract.externalNumber")
    @Mapping(source = "contractId", target = "contract.id")
    @Mapping(source = "requestStatusName", target = "requestStatus.name")
    @Mapping(source = "requestStatusId", target = "requestStatus.id")
    @Mapping(source = "requestTypeName", target = "requestType.name")
    @Mapping(source = "requestTypeRequestType", target = "requestType.requestType")
    @Override
    RequestEntity toEntity(RequestUiDto requestUiDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Override
    RequestUiDto toDto(RequestEntity requestEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name = "toEntity")
    @Override
    RequestEntity partialUpdate(RequestUiDto requestUiDto, @MappingTarget RequestEntity requestEntity);
}