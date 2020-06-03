package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZooTest {

    @Test
    void canDeserializeIfPojoIsJacksonFriendly() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";

        JacksonFriendlyZoo zoo = objectMapper.readValue(jsonIn, JacksonFriendlyZoo.class);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(jsonIn, jsonOut);
    }


    @Test
    void canDeserializeWithoutDefaultConstructor() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedOut = "{\"giraffeCount\":42,\"open\":false}";

        ImmutableZoo zoo = new ImmutableZoo(42, false);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(expectedOut, jsonOut);
    }

    @Test
    void failsToDeserializeWithoutDefaultConstructor() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";

        assertThrows(
                InvalidDefinitionException.class,
                () -> objectMapper.readValue(jsonIn, ImmutableZoo.class)
        );
    }

    @Test
    @Disabled
    void canDeserializeWithoutDefaultConstructorButWithMixIn() {
        ObjectMapper objectMapper = new ObjectMapper().addMixIn(ImmutableZoo.class, null);
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";

        assertThrows(
                InvalidDefinitionException.class,
                () -> objectMapper.readValue(jsonIn, ImmutableZoo.class)
        );
    }
}