package com.effective.chapter2.item1;

public class StaticFactory {
    public static final StaticFactory INSTANCE = new StaticFactory();

    private StaticFactory() {
    }

    public static StaticFactory getInstance(){
        return INSTANCE;
    }

    public static StaticFactory newInstance(){
        return newInstance();
    }
}
