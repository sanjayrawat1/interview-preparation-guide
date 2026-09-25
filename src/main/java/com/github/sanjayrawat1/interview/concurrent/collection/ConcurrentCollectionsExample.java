package com.github.sanjayrawat1.interview.concurrent.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     ConcurrentHashMap: A thread-safe HashMap that lets different threads read and write without
 *     blocking the whole map. Super efficient!
 * <p>
 *    CopyOnWriteArrayList and CopyOnWriteArraySet: When you change them (add, remove), they actually make
 *    a brand-new copy of themselves. This is great if you read from them a lot but rarely change them.
 * <p>
 *     BlockingQueue: These are fantastic for the "producer-consumer" pattern. If the queue is full,
 *     the producer waits. If it's empty, the consumer waits. Perfect for passing tasks around!
 *
 * @author sanjayrawat1
 */
public class ConcurrentCollectionsExample {

    public static void main(String[] args) throws InterruptedException {
        concurrentHashMapExample();

        System.out.println("Starting Producer-Consumer pattern");
        // BlockingQueue example (the Producer-Consumer pattern)
        blockingQueueExample();
    }

    private static void concurrentHashMapExample() throws InterruptedException {
        ConcurrentHashMap<String, Integer> wordCounts = new ConcurrentHashMap<>();
        wordCounts.put("apple", 1);
        wordCounts.put("banana", 2);

        // multiple threads can safely update
        Runnable updateTask = () -> {
            // compute() is a neat, atomic way to update a value
            wordCounts.compute("apple", (key, value) -> value == null ? 1 : value + 1);
            System.out.println(Thread.currentThread().getName() + " updated apple count");
        };

        new Thread(updateTask, "Updater-1").start();
        new Thread(updateTask, "Updater-2").start();

        // Just give some time for things to play out
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Final apple count : " + wordCounts.get("apple"));
    }

    private static void blockingQueueExample() {
        // a queue that holds upto 5 items
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        // The Producer: Puts items into the queue
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = "Message-" + i;
                    queue.put(message); // if the queue is full, this will wait
                    System.out.println("Produced : " + message);
                    TimeUnit.MILLISECONDS.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // The Consumer: Takes items from the queue.
        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = queue.take();// if the queue is empty, this will wait
                    System.out.println("Consumed : " + message);
                    TimeUnit.MILLISECONDS.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer").start();
    }
}
