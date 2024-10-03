package ru.sber.csportal.repository.request.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sber.csportal.entity.request.catalog.RequestTypeEntity;

public interface RequestTypeRepository extends JpaRepository<RequestTypeEntity, String>, JpaSpecificationExecutor<RequestTypeEntity> {
}