package com.github.sanjayrawat1.interview.java.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Imagine you have data that's read a lot but written to only occasionally.
 * With 'synchronized' or 'ReentrantLock', even readers would block each other.
 * 'ReadWriteLock' lets multiple readers go at it simultaneously, but writers get
 * exclusive access.
 *
 * @author sanjayrawat1
 */
public class ReadWriteLockExample {

    static class SharedResource {

        private String message = "Hello";

        private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();

        private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        public String readMessage() {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is reading : " + message);
                return message;
            } finally {
                readLock.unlock();
            }
        }

        public void writeMessage(String message) {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is writing : " + message);
                this.message = message;
            } finally {
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        // let's have a bunch of readers
        for (int i = 0; i < 5; i++) {
            new Thread(resource::readMessage, "Reader-" + i).start();
        }

        // and one writer
        new Thread(() -> resource.writeMessage("Goodbye"), "Writer-1").start();

        // another reader, maybe after the writer has done its thing.
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        new Thread(resource::readMessage, "Reader-After-Write").start();
    }
}
