package com.effective.chapter2.item8;

import java.lang.ref.Cleaner;

public class Cleaner_Example {
    private static final Cleaner cleaner = Cleaner.create();

    static class MyResource implements AutoCloseable {
        private final Cleaner.Cleanable cleanable;

        public MyResource() {
            cleanable = cleaner.register(this, () -> System.out.println("Resource cleaned up by Cleaner."));
        }

        @Override
        public void close() {
            cleanable.clean();
            System.out.println("Resource cleaned up manually.");
        }
    }

    public static void main(String[] args) {
        {
            MyResource resource = new MyResource();
            // Intentionally not calling resource.close();
        }
        System.gc();
    }


}
