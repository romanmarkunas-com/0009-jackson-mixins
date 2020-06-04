package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void canDeserializeWithoutDefaultConstructorButWithMixIn() throws JsonProcessingException {
        ObjectMapper objectMapper
                = new ObjectMapper()
                .addMixIn(ImmutableZoo.class, ImmutableZooMixIn.class);
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";

        ImmutableZoo zoo = objectMapper.readValue(jsonIn, ImmutableZoo.class);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(jsonIn, jsonOut);
    }

    @Test
    void failsToDeserializeWithoutDefaultConstructorAndIncorrectMixIn() {
        ObjectMapper objectMapper
                = new ObjectMapper()
                .addMixIn(ImmutableZoo.class, ImmutableZooMixInFail.class);
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";

        assertThrows(
                InvalidDefinitionException.class,
                () -> objectMapper.readValue(jsonIn, ImmutableZoo.class)
        );
    }

    @Test
    void canDeserializeWithoutDefaultConstructorButWithNavigatableMixIn() throws JsonProcessingException {
        ObjectMapper objectMapper
                = new ObjectMapper()
                .addMixIn(ImmutableZoo.class, ImmutableZooMixInNavigatable.class);
        String jsonIn = "{\"giraffeCount\":5,\"open\":true}";

        ImmutableZoo zoo = objectMapper.readValue(jsonIn, ImmutableZoo.class);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(jsonIn, jsonOut);
    }


    @Test
    void failsToSerializeIfMethodsDoNotFollowPropertyConvention() {
        ObjectMapper objectMapper = new ObjectMapper();

        NotFollowingJavaPropertyConventionZoo zoo = new NotFollowingJavaPropertyConventionZoo(42, false);
        assertThrows(
                InvalidDefinitionException.class,
                () -> objectMapper.writeValueAsString(zoo)
        );
    }

    @Test
    void canSerializeIfMethodsDoNotFollowPropertyConventionButMapperIsConfiguredToLookAtFields() throws JsonProcessingException {
        ObjectMapper objectMapper
                = new ObjectMapper()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String expectedOut = "{\"giraffeCount\":42,\"open\":false}";

        NotFollowingJavaPropertyConventionZoo zoo
                = new NotFollowingJavaPropertyConventionZoo(42, false);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(expectedOut, jsonOut);
    }

    @Test
    void canSerializeIfMethodsDoNotFollowPropertyConventionButWithMixIn() throws JsonProcessingException {
        ObjectMapper objectMapper
                = new ObjectMapper()
                .addMixIn(
                        NotFollowingJavaPropertyConventionZoo.class,
                        NotFollowingJavaPropertyConventionZooMixIn.class
                );
        String expectedOut = "{\"giraffeCount\":42,\"open\":false}";

        NotFollowingJavaPropertyConventionZoo zoo
                = new NotFollowingJavaPropertyConventionZoo(42, false);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(expectedOut, jsonOut);
    }


    @Test
    void serializesCachedFieldIncorrectly() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedOut = "{\"giraffeCount\":42,\"sealCount\":13}";
        String wrongOut = "{\"giraffeCount\":42,\"sealCount\":13,\"totalCount\":55}";

        HasPropertiesToIgnoreZoo zoo = new HasPropertiesToIgnoreZoo(42, 13);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertNotEquals(expectedOut, jsonOut);
        assertEquals(wrongOut, jsonOut);
    }

    @Test
    void serializesCachedFieldCorrectlyWithMixIn() throws JsonProcessingException {
        ObjectMapper objectMapper
                = new ObjectMapper()
                .addMixIn(HasPropertiesToIgnoreZoo.class, HasPropertiesToIgnoreZooMixIn.class);
        String expectedOut = "{\"giraffeCount\":42,\"sealCount\":13}";

        HasPropertiesToIgnoreZoo zoo = new HasPropertiesToIgnoreZoo(42, 13);
        String jsonOut = objectMapper.writeValueAsString(zoo);

        assertEquals(expectedOut, jsonOut);
    }
}