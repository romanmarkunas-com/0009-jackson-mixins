package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImmutableZooMixIn {

    @JsonCreator
    public ImmutableZooMixIn(
            @JsonProperty("giraffeCount") int giraffeCount,
            @JsonProperty("open") boolean open
    ) {}
}
