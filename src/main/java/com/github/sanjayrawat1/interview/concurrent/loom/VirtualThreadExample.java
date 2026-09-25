package com.github.sanjayrawat1.interview.concurrent.loom;

/**
 *
 * @author sanjayrawat1
 */
public class VirtualThreadExample {

    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);
    }
}
