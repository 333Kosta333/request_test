package ru.sber.csportal.controller.catalog;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sber.csportal.dto.catalog.ContractUiDto;

/**
 * The interface Contract controller.
 */
public interface ContractController {
    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    ResponseEntity<List<ContractUiDto>> findAll();

    /**
     * Find by uvhd number response entity.
     *
     * @param uvhdNumber the uvhd number
     * @return the response entity
     */
    ResponseEntity<List<ContractUiDto>> findByUvhdNumber(@PathVariable String uvhdNumber);

    /**
     * Create response entity.
     *
     * @param contractUiDto the contract ui dto
     * @return the response entity
     */
    ResponseEntity<ContractUiDto> create(@RequestBody ContractUiDto contractUiDto);

}
