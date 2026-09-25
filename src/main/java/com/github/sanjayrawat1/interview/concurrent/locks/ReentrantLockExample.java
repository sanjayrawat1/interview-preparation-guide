package com.github.sanjayrawat1.interview.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * "Reentrant" means if a thread already holds the lock, it can acquire it again without blocking itself.
 *
 * @author sanjayrawat1
 */
public class ReentrantLockExample {

    static class DataStore {

        private int data;

        private final ReentrantLock lock = new ReentrantLock();

        public void modifyData() {
            // gotta get the lock first!
            lock.lock();
            try {
                // This is your critical section - only one thread can be in here
                data++;
                System.out.println(Thread.currentThread().getName() + " cranked up data to : " + data);
            } finally {
                // ALWAYS release the lock in finally block! Super important!
                lock.unlock();
            }
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DataStore store = new DataStore();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                store.modifyData();
            }
        };

        Thread t1 = new Thread(task, "Worker-1");
        Thread t2 = new Thread(task, "Worker-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final data : " + store.getData()); // should be 2000!
    }
}
