package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NotFollowingJavaPropertyConventionZooMixIn {

    @JsonProperty("giraffeCount")
    public abstract int giraffeCount();

    @JsonProperty("open")
    public abstract boolean open();
}
