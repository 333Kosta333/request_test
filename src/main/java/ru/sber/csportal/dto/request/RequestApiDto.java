package ru.sber.csportal.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link ru.sber.csportal.entity.request.RequestEntity}
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestApiDto {
    private Long requestNumber;
    private String requestDescription;
    private String requestTemplate;
    private String requestTypeName;
    private String requestTypeDescription;
    private String requestStatusId;
    private String requestStatusName;
    private String contractExternalNumber;
    private String contractUvhdNumber;
}