package ru.sber.csportal.dto.request;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link ru.sber.csportal.entity.request.RequestEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUiDto {
    private UUID id;
    private Instant createdDate;
    private Long requestNumber;
    private String requestDescription;
    private String requestTemplate;
    private String requestTypeRequestType;
    private String requestTypeName;
    private String requestStatusId;
    private String requestStatusName;
    private UUID contractId;
    private String contractExternalNumber;
    private String contractUvhdNumber;
}