package com.romanmarkunas.blog.jackson;

public class HasPropertiesToIgnoreZoo {

    private final int giraffeCount;
    private final int sealCount;
    private final int totalCountCache;


    public HasPropertiesToIgnoreZoo(int giraffeCount, int sealCount) {
        this.giraffeCount = giraffeCount;
        this.sealCount = sealCount;
        this.totalCountCache = giraffeCount + sealCount;
    }


    public int getGiraffeCount() {
        return giraffeCount;
    }

    public int getSealCount() {
        return sealCount;
    }

    public int getTotalCount() {
        return totalCountCache;
    }
}
