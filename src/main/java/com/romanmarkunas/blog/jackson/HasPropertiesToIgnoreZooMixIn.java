package com.romanmarkunas.blog.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class HasPropertiesToIgnoreZooMixIn {

    @JsonIgnore
    public abstract int getTotalCount();
}
