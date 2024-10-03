package ru.sber.csportal.repository.catalog;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sber.csportal.entity.catalog.ContractEntity;

public interface ContractRepository extends JpaRepository<ContractEntity, UUID>, JpaSpecificationExecutor<ContractEntity> {
    List<ContractEntity> findByUvhdNumber(String uvhdNumber);
}