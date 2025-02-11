package com.demo.java17.m7compactstring;

public class CompactStringPerformanceDemo {
    private static final int TRANSACTION_COUNT = 1_000_000;
    public static void main(String[] args) {

        System.gc();
        System.gc();
//        Print out the JDK version
//        System.out.println("JDK Version " + System.getProperty("java.version"));
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuilder builder = new StringBuilder( TRANSACTION_COUNT * 30);
        for (int i = 0; i < TRANSACTION_COUNT; i++) {
            String txnId = "TXN" + String.format("%09d",i);
            String amount = "USD" + String.format("%.2f", Math.random()* 10000);
            String record = txnId + "\t" + amount + "\t" + txnId;
            builder.append(record);
        }

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("JDK Version " + System.getProperty("java.version"));
        System.out.println(afterMemory - beforeMemory);
        System.out.println((afterMemory - beforeMemory)/1024.0);
        System.out.println((afterMemory - beforeMemory)/(1024.0 * 1024.0));
    }
}
