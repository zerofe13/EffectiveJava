package com.effective.chapter2.item7;


import java.lang.ref.WeakReference;
import java.util.stream.IntStream;

public class ReferTest {

    public static void main(String[] args) {
       Temp t = new Temp("test");
       Temp tt =new Temp("test1");

       Temp strongRef = t;
       WeakReference<Temp> weakRef = new WeakReference<>(tt);

        System.out.println(t.hashCode() == strongRef.hashCode());
        System.out.println(tt.hashCode() == weakRef.hashCode());


        t = null;
        tt=null;
        System.gc();

        //null 을했을때 약한참조만 메모리 해제

        System.out.println("strongRef = " + strongRef);
        if(weakRef.get() != null) {
            System.out.println("weakRef = " + weakRef.get());
        }else{
            System.out.println("참조해제");
        }
    }
    public static class Temp{
        String name;

        public Temp(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "name=" + name ;
        }
    }
}
