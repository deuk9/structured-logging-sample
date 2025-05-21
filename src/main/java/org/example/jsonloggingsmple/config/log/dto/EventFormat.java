package org.example.jsonloggingsmple.config.log.dto;


import lombok.Builder;

@Builder
public record EventFormat<T>(
    String eventId,
    String name,
    String message,
    T body
) {

}
