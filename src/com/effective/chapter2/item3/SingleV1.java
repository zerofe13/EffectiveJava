package com.effective.chapter2.item3;

import java.io.Serializable;

public class SingleV1 implements Serializable {
    private static final SingleV1 INSTANCE = new SingleV1();

    private SingleV1() {
    }
    public static SingleV1 getInstance = INSTANCE;



}
