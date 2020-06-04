package com.romanmarkunas.blog.jackson;

public class NotFollowingJavaPropertyConventionZoo {

    private final int giraffeCount;
    private final boolean open;


    public NotFollowingJavaPropertyConventionZoo(int giraffeCount, boolean open) {
        this.giraffeCount = giraffeCount;
        this.open = open;
    }


    public int giraffeCount() {
        return giraffeCount;
    }

    public boolean open() {
        return open;
    }
}
