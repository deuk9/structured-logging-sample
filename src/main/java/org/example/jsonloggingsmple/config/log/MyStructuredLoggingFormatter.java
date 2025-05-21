package org.example.jsonloggingsmple.config.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.apache.logging.log4j.core.LogEvent;
import org.springframework.boot.json.JsonWriter;
import org.springframework.boot.logging.structured.StructuredLogFormatter;

public class MyStructuredLoggingFormatter implements StructuredLogFormatter<LogEvent> {

    private final ObjectMapper objectMapper = new JsonMapper();

    private final JsonWriter<LogEvent> writer = JsonWriter.<LogEvent>of((members) -> {
        members.add("time", event -> {
            Instant javaInstant = Instant.ofEpochMilli(event.getInstant().getEpochMillisecond());
            return LocalDateTime.ofInstant(javaInstant, ZoneId.systemDefault());
        });
        members.add("level", LogEvent::getLevel);
        members.add("marker", LogEvent::getMarker)
            .whenNotNull();

        members.add("thread", LogEvent::getThreadName);
        members.add("message",
            (event) -> {
                if (event.getMessage().getParameters() != null
                    && event.getMessage().getParameters().length == 1) {

                    if (event.getMessage().getParameters()[0] instanceof MapSerializable mapSerializable) {
                        return mapSerializable.toMap();
                    }
                }
                return event.getMessage().getFormattedMessage();
            });
    }).withNewLineAtEnd();

    @Override
    public String format(LogEvent event) {
        return this.writer.writeToString(event);
    }

}
