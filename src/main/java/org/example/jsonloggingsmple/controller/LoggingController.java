package org.example.jsonloggingsmple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jsonloggingsmple.config.log.ObjectToMapSerializable;
import org.example.jsonloggingsmple.config.log.dto.EventFormat;
import org.example.jsonloggingsmple.controller.dto.TestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoggingController {

    private final ObjectMapper objectMapper;
    @GetMapping("/api/test")
    public String logging() {

        EventFormat<TestDto> eventFormat = EventFormat.<TestDto>builder()
            .eventId("ABC")
            .name("네임")
            .message("메시지")
            .body(new TestDto("name", "lastName", "test"))
            .build();

        log.warn("{}", new ObjectToMapSerializable<>(eventFormat, objectMapper));

        return "Logs generated successfully with log4j2";
    }
}
