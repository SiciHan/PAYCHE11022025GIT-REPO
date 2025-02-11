package com.demo.java17.m1diamond;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

// Sample Payment class
class Payment {
    private String id;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Payment(String id, BigDecimal amount, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Payment{id='%s', amount=%s, date=%s}",
                id, amount, timestamp.truncatedTo(ChronoUnit.MINUTES));
    }
}

public class DiamondOperatorWithStreamsDemo {
    public static void main(String[] args) {
        // Pre-Java 7: Explicit type parameters required
        List<Payment> legacyPayments = new ArrayList<Payment>();
        legacyPayments. add(new Payment("P10", new BigDecimal("50"), LocalDateTime.now().minusDays(2)));
        Comparator<Payment> legacyComparator = new Comparator<Payment>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        // Java 7+: Diamond operator simplifies generic type inference
        List<Payment> modernPayments = new ArrayList<>();

        // Java 9+: Diamond operator with anonymous inner class
        Comparator<Payment> modernComparator = new Comparator<>() {
            @Override
            public int compare(Payment p1, Payment p2) {
                return p1.getAmount().compareTo(p2.getAmount());
            }
        };

        // Adding sample data across different days
        legacyPayments.add(new Payment("P11", new BigDecimal("100"), LocalDateTime.now()));
        legacyPayments.add(new Payment("P21", new BigDecimal("200"), LocalDateTime.now().minusDays(1)));
        legacyPayments.add(new Payment("P31", new BigDecimal("150"), LocalDateTime.now().minusDays(2)));
        modernPayments.add(new Payment("P1", new BigDecimal("100"), LocalDateTime.now()));
        modernPayments.add(new Payment("P2", new BigDecimal("200"), LocalDateTime.now().minusDays(1)));
        modernPayments.add(new Payment("P3", new BigDecimal("300"), LocalDateTime.now().minusDays(2)));

        // Pre-Java 8: Manual grouping implementation with TreeMap
        Map<String, List<Payment>> groupedLegacyPayments = new TreeMap<>();
        for (Payment payment : legacyPayments) {
            String dayOfWeek = payment.getTimestamp().getDayOfWeek().toString();
            if (!groupedLegacyPayments.containsKey(dayOfWeek)) {
                groupedLegacyPayments.put(dayOfWeek, new ArrayList<>());
            }
            groupedLegacyPayments.get(dayOfWeek).add(payment);
        }

        // sort using the legacy comparator
        legacyPayments.sort(legacyComparator);

        // Using diamond operator with Stream grouping
        Map<String, List<Payment>> groupedPayments = modernPayments.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getTimestamp().getDayOfWeek().toString(),
                        () -> new TreeMap<>(),  // Diamond operator in constructor
//                        TreeMap::new, // Using method reference instead of lambda
                        Collectors.toCollection(ArrayList::new)
                ));

        // Sort using the modern comparator
        modernPayments.sort(modernComparator);

        // Print results with formatting
        System.out.println("=== Legacy Payments (Sorted by Amount) ===");
        legacyPayments.forEach(p -> System.out.printf("  %s: $%s on %s%n",
                p.getId(), p.getAmount(), p.getTimestamp().getDayOfWeek()));

        System.out.println("\n=== Legacy Payments Grouped by Day ===");
        groupedLegacyPayments.forEach((day, payments) -> {
            System.out.printf("%s:%n", day);
            payments.forEach(p -> System.out.printf("  - %s: $%s%n", p.getId(), p.getAmount()));
        });

        System.out.println("\n=== Modern Payments (Sorted by Amount) ===");
        modernPayments.forEach(p -> System.out.printf("  %s: $%s on %s%n",
                p.getId(), p.getAmount(), p.getTimestamp().getDayOfWeek()));

        System.out.println("\n=== Modern Payments Grouped by Day ===");
        groupedPayments.forEach((day, payments) -> {
            System.out.printf("%s:%n", day);
            payments.forEach(p -> System.out.printf("  - %s: $%s%n", p.getId(), p.getAmount()));
        });
    }
}
