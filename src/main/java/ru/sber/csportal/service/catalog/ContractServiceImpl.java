package ru.sber.csportal.service.catalog;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.csportal.dto.catalog.ContractUiDto;
import ru.sber.csportal.entity.catalog.ContractEntity;
import ru.sber.csportal.mapper.catalog.ContractApiUiMapper;
import ru.sber.csportal.repository.catalog.ContractRepository;

@RequiredArgsConstructor
@Service("contractService")
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ContractApiUiMapper contractMapper;

    @Override
    public Optional<List<ContractUiDto>> findAll() {
        List<ContractEntity> contractEntities = contractRepository.findAll();
        List<ContractUiDto> contractUiDto = contractEntities.stream()
                .map(contractMapper::toDto)
                .toList();
        return Optional.of(contractUiDto);
    }

    @Override
    public Optional<List<ContractUiDto>> findByUvhdNumber(String uvhdNumber) {
        List<ContractEntity> contractEntities = contractRepository.findByUvhdNumber(uvhdNumber);
        List<ContractUiDto> contractUiDto = contractEntities.stream()
                .map(contractMapper::toDto)
                .toList();
        return Optional.of(contractUiDto);
    }

    @Override
    public ContractUiDto createUi(ContractUiDto contractUiDto) {
        ContractEntity contractEntity = contractMapper.toEntity(contractUiDto);
        ContractEntity resultContractEntity = contractRepository.save(contractEntity);
        return contractMapper.toDto(resultContractEntity);
    }
}
