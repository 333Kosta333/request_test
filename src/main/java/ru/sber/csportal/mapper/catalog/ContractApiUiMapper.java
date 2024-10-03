package ru.sber.csportal.mapper.catalog;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;
import ru.sber.csportal.dto.catalog.ContractUiDto;
import ru.sber.csportal.entity.catalog.ContractEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ContractApiUiMapper {
    ContractUiDto toDto(ContractEntity contractEntity);

    ContractEntity toEntity(ContractUiDto contractUiDto);
}