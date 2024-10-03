package ru.sber.csportal.service.request;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.csportal.mapper.request.RequestMapper;

/**
 * Factory of mapper factory to create request .
 */
@RequiredArgsConstructor
@Component
public class RequestCreatorMapperFactory {
    // Map of mappers. SpringBoot collect this map for Component.
    private final Map<String, RequestMapper> mappers;

    /**
     * Gets mapper.
     *
     * @param dto the dto
     * @return the mapper
     */
    public RequestMapper getMapper(Object dto) {
        return switch (dto.getClass().getSimpleName()) {
            case "RequestApiDto" -> mappers.get("requestApiMapperImpl");
            case "RequestUiDto" -> mappers.get("requestUiMapperImpl");
            default -> mappers.get("requestOtherMapperImpl");
        };
    }
}
