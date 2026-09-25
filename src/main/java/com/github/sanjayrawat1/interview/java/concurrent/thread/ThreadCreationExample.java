package com.github.sanjayrawat1.interview.java.concurrent.thread;

/**
 *
 * @author sanjayrawat1
 */
public class ThreadCreationExample {

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hey, I'm MyThread. " + Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello from MyRunnable. " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread starting. "  + Thread.currentThread().getName());
        MyThread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

        Thread thread3 = new Thread(() -> System.out.println("Whoa, a lambda thread! " + Thread.currentThread().getName()));
        thread3.start();

        System.out.println("Main thread ending. "  + Thread.currentThread().getName());

        Thread vt = Thread.ofVirtual().start(() -> System.out.println("hello from virtual thread : " + Thread.currentThread().threadId()));
        Thread pt = Thread.ofPlatform().start(() -> System.out.println("hello from platform thread : " + Thread.currentThread().threadId()));

        Thread.Builder builder = Thread.ofVirtual().name("MyThread");
        Runnable task = () -> {
            System.out.println("Running thread");
        };
        Thread t = builder.start(task);
        System.out.println("Thread t name: " + t.getName());
        t.join();
    }
}
