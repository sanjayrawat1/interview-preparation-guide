package com.github.sanjayrawat1.interview.java.concurrent.synchronizers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore: Think of a Semaphore as a gatekeeper for a limited number of resources.
 * It only lets a certain number of threads through at a time.
 *
 * @author sanjayrawat1
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // only 2 threads can access at once.
        ExecutorService executor = Executors.newFixedThreadPool(5); // 5 users wanting access
        for (int i = 0; i < 5; i++) {
            final int userId = i;
            executor.submit(() -> {
                try {
                    System.out.println("User " + userId + " wants to get a permit.");
                    semaphore.acquire(); // try to get a permit (will wait if none are free)
                    System.out.println("User " + userId + " got a permit! Using the share resources.");
                    TimeUnit.SECONDS.sleep(2); // Pretend to use the resource for 2 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release(); // IMPORTANT: release the permit when you are done!
                }
            });
        }
        executor.shutdown();
    }
}
