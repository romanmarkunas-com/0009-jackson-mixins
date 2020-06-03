package com.romanmarkunas.blog.jackson;

public class ZooFollowingJavaPropertiesConvention {

    private final int giraffeCount;
    private final boolean open;


    public ZooFollowingJavaPropertiesConvention(int giraffeCount, boolean open) {
        this.giraffeCount = giraffeCount;
        this.open = open;
    }


    public int getGiraffeCount() {
        return giraffeCount;
    }

    public boolean isOpen() {
        return open;
    }
}
