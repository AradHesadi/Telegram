package org.telegram.messenger;

public class TestTools {

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int value) {
        try {
            Thread.sleep(value * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
