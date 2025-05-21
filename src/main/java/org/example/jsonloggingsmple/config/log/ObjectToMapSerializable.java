package org.example.jsonloggingsmple.config.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjectToMapSerializable<T> implements MapSerializable {

    private final T target;
    private final ObjectMapper objectMapper;

    @Override
    public Map<String, Object> toMap() {
        return convertToMap(target);
    }

    private Map<String, Object> convertToMap(T obj) {
        return objectMapper.convertValue(obj, Map.class);
    }
}