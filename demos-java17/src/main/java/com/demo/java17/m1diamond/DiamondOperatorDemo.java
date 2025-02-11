package com.demo.java17.m1diamond;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Common domain classes
class Transaction {
    private String id;
    private BigDecimal amount;

    public Transaction(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{id='" + id + "', amount=" + amount + "}";
    }
}

public class DiamondOperatorDemo {
    public static void main(String[] args) {
        // Legacy [Pre Java 7]
        Map<String, List<Transaction>> legacyTransactions = new HashMap<String, List<Transaction>>();
        legacyTransactions.put("USD", new ArrayList<Transaction>());

        // Legacy [Java 7]
        Map<String, List<Transaction>> legacyTransactions1 = new HashMap<>();
        legacyTransactions1.put("EUR", new ArrayList<Transaction>());

        // Java 7
        TransactionProcessor<Transaction> legacyProcessor = new TransactionProcessor<Transaction>() {
            @Override
            public void process(Transaction t) {
                System.out.println("Modern Transaction: " + t);
            }
        };
        // Java 9+ Diamond operator with anonymous inner class
        TransactionProcessor<Transaction> modernProcessor = new TransactionProcessor<>() {
            @Override
            public void process(Transaction t) {
                System.out.println("Modern Transaction: " + t);
            }
        };
    }
}
interface TransactionProcessor<T> {
    void process(T transaction);
}