package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicInteger;

public class RandomIdGenerator {
    private static AtomicInteger uniqueId = new AtomicInteger(0);

    public static int generateId() {
        return uniqueId.incrementAndGet();
    }
}