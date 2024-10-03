package ru.sber.csportal.repository.serviceobject.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sber.csportal.entity.serviceobject.catalog.ServiceObjectTypeEntity;

public interface ServiceObjectTypeRepository extends JpaRepository<ServiceObjectTypeEntity, String>, JpaSpecificationExecutor<ServiceObjectTypeEntity> {
}