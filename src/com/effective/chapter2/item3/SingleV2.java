package com.effective.chapter2.item3;

import java.io.Serializable;

public class SingleV2 implements Serializable {

    private static final SingleV2 INSTANCE = new SingleV2();

    private SingleV2() {
    }
    public static SingleV2 getInstance = INSTANCE;

    private Object readResolve(){
        return INSTANCE;
    }


}
