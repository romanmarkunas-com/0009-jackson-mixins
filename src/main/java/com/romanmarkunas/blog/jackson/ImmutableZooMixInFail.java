package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ImmutableZooMixInFail {

    @JsonCreator
    public static ImmutableZoo create(
            @JsonProperty("giraffeCount") int giraffeCount,
            @JsonProperty("open") boolean open
    ) {
        return new ImmutableZoo(giraffeCount, open);
    }
}
