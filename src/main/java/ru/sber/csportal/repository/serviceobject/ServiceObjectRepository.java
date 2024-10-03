package ru.sber.csportal.repository.serviceobject;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sber.csportal.entity.serviceobject.ServiceObjectEntity;

public interface ServiceObjectRepository extends JpaRepository<ServiceObjectEntity, UUID>, JpaSpecificationExecutor<ServiceObjectEntity> {
}