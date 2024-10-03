package ru.sber.csportal.controller.catalog;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.csportal.dto.catalog.ContractUiDto;
import ru.sber.csportal.service.catalog.ContractService;

/**
 * The type Contract controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalog/contract")
public class ContractControllerImpl implements ContractController {

    private final ContractService contractService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ContractUiDto>> findAll() {
        Optional<List<ContractUiDto>> contractUiDtosOptional = contractService.findAll();
        List<ContractUiDto> contractUiDto = contractUiDtosOptional.orElse(null);
        return ResponseEntity.ok(contractUiDto);
    }

    @Override
    @GetMapping("/{uvhdNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ContractUiDto>> findByUvhdNumber(@PathVariable String uvhdNumber) {
        Optional<List<ContractUiDto>> contractUiDtosOptional = contractService.findByUvhdNumber(uvhdNumber);
        List<ContractUiDto> contractUiDto = contractUiDtosOptional.orElse(null);
        return ResponseEntity.ok(contractUiDto);
    }

    @Override
    @PostMapping(path = {"/create"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContractUiDto> create(@RequestBody ContractUiDto contractUiDto) {
        ContractUiDto resultContractUiDto = contractService.createUi(contractUiDto);
        return ResponseEntity.ok(resultContractUiDto);
    }
}

