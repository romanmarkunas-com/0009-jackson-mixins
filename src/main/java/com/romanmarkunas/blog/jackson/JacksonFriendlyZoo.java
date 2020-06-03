package com.romanmarkunas.blog.jackson;

public class JacksonFriendlyZoo {

    private int giraffeCount;
    private boolean open;


    public JacksonFriendlyZoo() {
        // for deserialization, don't use me!
    }

    public JacksonFriendlyZoo(int giraffeCount, boolean open) {
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
