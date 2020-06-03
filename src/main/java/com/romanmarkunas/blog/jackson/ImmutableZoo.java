package com.romanmarkunas.blog.jackson;

public class ImmutableZoo {

    private final int giraffeCount;
    private final boolean open;


    public ImmutableZoo(int giraffeCount, boolean open) {
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
