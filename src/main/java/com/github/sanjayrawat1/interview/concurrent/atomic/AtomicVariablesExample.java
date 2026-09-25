package com.github.sanjayrawat1.interview.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * If you just need to update a single variable (like an integer or boolean) safely, using 'synchronized'
 * or 'Lock' can be overkill. That's where 'java.util.concurrent.atomic' comes in. It provides classed that
 * do these operations superfast, without needing explicit locks.
 * <p>
 * AtomicInteger, AtomicLong, AtomicBoolean, AtomicReference: These gives you methods like getAndIncrement(),
 * comapreAndSet(), and so on, which are all guaranteed to be atomic (meaning they complete in one go,
 * without interference.
 *
 * @author sanjayrawat1
 */
public class AtomicVariablesExample {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // this is an atomic operation! Safe and fast!
            }
        };

        Thread t1 = new Thread(task, "Atomic-Thread-1");
        Thread t2 = new Thread(task, "Atomic-Thread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final atomic count : " + counter.get());
    }
}
