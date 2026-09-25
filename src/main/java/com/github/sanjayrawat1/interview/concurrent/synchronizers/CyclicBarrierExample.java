package com.github.sanjayrawat1.interview.concurrent.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier: This is like a meeting point for threads. All threads wait until everyone gets there,
 * and then they all go forward together. It's cyclic because you can reuse it.
 *
 * @author sanjayrawat1
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        // our barrier. 3 threads need to arrive, and then we'll run the 'barrier action'
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("---All participants are here! Let's go!---"));
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int participantId = i;
            executor.submit(() -> {
                try {
                    System.out.println("Participant " + participantId + " is doing some warm-up work");
                    TimeUnit.SECONDS.sleep(1 + (long) (Math.random() * 2));
                    System.out.println("Participant " + participantId + " arrived at the barrier");
                    barrier.await(); // wait for everyone else to show up
                    System.out.println("Participant " + participantId + " is now continuing after the barrier");
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executor.shutdown();
    }
}
