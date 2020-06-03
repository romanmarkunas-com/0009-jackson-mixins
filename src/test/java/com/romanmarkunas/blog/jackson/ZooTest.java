package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZooTest {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void canEasilyDeserializeIfPojoIsJacksonFriendly() throws JsonProcessingException {
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";
        JacksonFriendlyZoo zoo = objectMapper.readValue(jsonIn, JacksonFriendlyZoo.class);
        String jsonOut = objectMapper.writeValueAsString(zoo);
        assertEquals(jsonIn, jsonOut);
    }
}