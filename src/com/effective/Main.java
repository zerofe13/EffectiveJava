package com.effective;

import com.effective.chapter2.item1.StaticFactory;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
	// write your code here
        StaticFactory StaticFactoryInstance = StaticFactory.getInstance();
        StaticFactory StaticFactoryInstance2 = StaticFactory.getInstance();
        StaticFactory newStaticFactoryInstance = StaticFactory.newInstance();

        //true
        System.out.println(StaticFactoryInstance == StaticFactoryInstance2);
        //false
        System.out.println(StaticFactoryInstance == newStaticFactoryInstance);

    }
}
