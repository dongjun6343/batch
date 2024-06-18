package com.rn.batch.api.scrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String objectToJson(T requestDto) {
        try {
            return objectMapper.writeValueAsString(requestDto);
        } catch (JsonProcessingException e) {
            log.error("ConvertUtils.objectToJson - requestDto: {}", requestDto, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("ConvertUtils.convertToObject - responseJson: {}", json, e);
            throw new RuntimeException(e);
        }
    }
}
