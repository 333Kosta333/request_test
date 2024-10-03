package ru.sber.csportal.dto.catalog;

import java.util.UUID;

/**
 * DTO for {@link ru.sber.csportal.entity.catalog.ContractEntity}
 */
public record ContractUiDto(UUID contractId, String externalNumber, String uvhdNumber) {
}