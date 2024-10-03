package ru.sber.csportal.service.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.csportal.dto.request.RequestApiDto;
import ru.sber.csportal.entity.request.RequestEntity;

@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestServiceImpl requestServiceImpl;

    @PostMapping("/createApi")
    public ResponseEntity<RequestEntity> createApi(@RequestBody RequestApiDto dto) {
        RequestEntity requestEntity = requestServiceImpl.create(dto);
        return ResponseEntity.ok(requestEntity);
    }
}

