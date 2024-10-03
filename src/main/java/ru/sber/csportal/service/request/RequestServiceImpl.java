package ru.sber.csportal.service.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.csportal.entity.request.RequestEntity;
import ru.sber.csportal.mapper.request.RequestMapper;

@RequiredArgsConstructor
@Service
public class RequestServiceImpl {

    private final RequestCreatorMapperFactory requestCreatorMapperFactory;

    public RequestEntity create(Object dto) {
        RequestMapper mapper = requestCreatorMapperFactory.getMapper(dto);
        Object entity = mapper.toEntity(dto);

        return null;
    }
}
