//package com.demo.java17.m2streamapienhancements;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class ModernStreamApiDemo {
//    public static void main(String[] args) {
//        // 1. Sample data initialization
//        List<Transaction> transactions = Arrays.asList(
////                new Transaction("T1", new BigDecimal(100.50)),
////                new Transaction("T2", new BigDecimal(375.50)),
////                new Transaction("T3", new BigDecimal(400.50))
//
//        new Transaction("T1", new BigDecimal(100.50)),
//                new Transaction("T2", new BigDecimal(200.50)),
//                new Transaction("T3", new BigDecimal(300.50))
//                );
//
//        //2. takeWhile :
//        System.out.println("========= Small Transaction =============");
//        transactions.stream()
//                .takeWhile(t -> t.getAmount().compareTo(BigDecimal.valueOf(400)) < 0)
//                .forEach(System.out::println);
//
//        //3. dropWhile(Lab) use case : to skip smaller transactions.
//
//        //4. ofNullable : Handle nullable transaction (JAVA 9)
//        System.out.println("========= Working with Nullable Transactions =============");
//        Transaction nullableTransaction = retreivePendingTransaction();
//        Stream.ofNullable(nullableTransaction).forEach(System.out::println);
//
//
//        //6. teeing  : Calculate average and total combining two collectors
//        TransactionSummary summary = transactions.stream()
//                .collect(
//                        Collectors.teeing(
//                                Collectors.averagingDouble(t -> t.getAmount().doubleValue()),
//                                Collectors.summingDouble(t -> t.getAmount().doubleValue()),
//                                (avg,sum) -> new TransactionSummary(avg,sum)
//                        )
//                );
//        System.out.println("\n Transaction Summary " + summary);
//    }
//    private static Transaction retreivePendingTransaction() {
//        return null;
//    }
//}
//
//class Transaction {
//    private final String id;
//    private final BigDecimal amount;
//
//    public Transaction(String id, BigDecimal amount) {
//        this.id = id;
//        this.amount = amount;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "amount=" + amount +
//                ", id='" + id + '\'' +
//                '}';
//    }
//}
//
//class TransactionSummary {
//    private final double average;
//    private final double total;
//
//    public TransactionSummary(double average, double total) {
//        this.average = average;
//        this.total = total;
//    }
//    public double getAverage() {
//        return average;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    @Override
//    public String toString() {
//        return "TransactionSummary{" +
//                "average=" + average +
//                ", total=" + total +
//                '}';
//    }
//}