package ru.sber.csportal.service.request;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sber.csportal.dto.request.RequestApiDto;
import ru.sber.csportal.mapper.request.RequestApiMapper;
import ru.sber.csportal.mapper.request.RequestApiMapperImpl;
import ru.sber.csportal.mapper.request.RequestMapper;
import ru.sber.csportal.mapper.request.RequestUiMapperImpl;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RequestCreatorMapperFactoryTest {
//
//    @Autowired
//    private RequestCreatorMapperFactory requestCreatorMapperFactory;
//
//    @Configuration
//    static class ContextConfig {
//        @Bean
//        public RequestCreatorMapperFactory getRequestCreatorMapperFactory() {
//            Map<String, RequestMapper> requestMapperMap = new HashMap<>();
//            requestMapperMap.put("RequestApiDto", new RequestApiMapperImpl());
//            requestMapperMap.put("RequestUiDto", new RequestUiMapperImpl());
//
//            return new RequestCreatorMapperFactory(requestMapperMap);
//        }
//    }
//
//    @Test
//    void getMapper() {
//        RequestApiDto requestApiDto = new RequestApiDto();
//        RequestMapper mapper = requestCreatorMapperFactory.getMapper(requestApiDto);
//        assertNotNull(mapper);
//        assertInstanceOf(RequestApiMapper.class, mapper);
//    }
}