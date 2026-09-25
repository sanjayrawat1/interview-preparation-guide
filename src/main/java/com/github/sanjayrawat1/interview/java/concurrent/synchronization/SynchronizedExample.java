package com.github.sanjayrawat1.interview.java.concurrent.synchronization;

/**
 *
 * @author sanjayrawat1
 */
public class SynchronizedExample {

    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
            System.out.println(Thread.currentThread().getName() + " : " + count);
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count : " + counter.getCount()); // it should always see 200
    }
}
