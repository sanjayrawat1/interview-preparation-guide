package com.github.sanjayrawat1.interview.concurrent.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronizers are used to make threads wait for each other or coordinate their action.
 * <li>
 *     CountDownLatch: Think of this as a countdown timer. One or more threads can wait until
 *     a certain number of events happen.
 * </li>
 * @author sanjayrawat1
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3); // we're waiting for three things to happen.
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    System.out.println("Task " + taskId + " just started");
                    Thread.sleep(1000 + (long) (Math.random() * 1000)); // simulating some work
                    System.out.println("Task " + taskId + " finished up!");
                    latch.countDown(); // Ding! One less thing to wait for.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        System.out.println("Main thread is chilling, waiting for tasks to wrap up...");
        latch.await(); // main thread waits until the countdown hits zero
        System.out.println("Alright! All tasks are done. Main thread can continue.");
        executor.shutdown();
        executor.close();
    }
}
