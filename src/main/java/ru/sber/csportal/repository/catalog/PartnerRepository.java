package ru.sber.csportal.repository.catalog;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sber.csportal.entity.catalog.PartnerEntity;

public interface PartnerRepository extends JpaRepository<PartnerEntity, UUID>, JpaSpecificationExecutor<PartnerEntity> {
    List<PartnerEntity> findByPersonalNumber(String personalNumber);
}