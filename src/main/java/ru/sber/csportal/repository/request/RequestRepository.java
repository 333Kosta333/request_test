package ru.sber.csportal.repository.request;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.sber.csportal.entity.request.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity, UUID>, JpaSpecificationExecutor<RequestEntity> {
}