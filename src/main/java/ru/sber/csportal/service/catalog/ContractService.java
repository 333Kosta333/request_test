package ru.sber.csportal.service.catalog;

import java.util.List;
import java.util.Optional;
import ru.sber.csportal.dto.catalog.ContractUiDto;

public interface ContractService {
    Optional<List<ContractUiDto>> findAll();

    Optional<List<ContractUiDto>> findByUvhdNumber(String uvhdNumber);

    ContractUiDto createUi(ContractUiDto contractUiDto);

}
