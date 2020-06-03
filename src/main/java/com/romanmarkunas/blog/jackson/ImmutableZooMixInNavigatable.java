package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ImmutableZooMixInNavigatable extends ImmutableZoo {

    @JsonCreator
    public ImmutableZooMixInNavigatable(
            @JsonProperty("giraffeCount") int giraffeCount,
            @JsonProperty("open") boolean open
    ) {
        super(giraffeCount, open);
    }
}
