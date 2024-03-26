package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://leetcode.com/problems/kth-largest-element-in-a-stream/">kth-largest-element-in-a-stream</a>
 * <p>
 * Design a class to efficiently find the `Kth` largest element in a stream of numbers.
 * <br>
 * The class should have the following two things:
 * <ol>
 *     <li> The constructor of the class should accept an integer array containing initial numbers from the
 *          stream and an integer `K`.
 *     <li> The class should expose a function `add(num)` which will store the given number and return the
 *          `Kth largest` number.
 * </ol>
 *
 * @author sanjayrawat1
 */
public class P7KthLargestNumberInStream {

    private final Queue<Integer> minHeap = new PriorityQueue<>();

    private final int k;

    public P7KthLargestNumberInStream(int[] arr, int k) {
        this.k = k;
        for (int num : arr) {
            add(num);
        }
    }

    public int add(int num) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        var kthLargest = new P7KthLargestNumberInStream(new int[] {3, 1, 5, 12, 2, 11}, 4);
        assertThat(kthLargest.add(6)).isEqualTo(5);
        assertThat(kthLargest.add(13)).isEqualTo(6);
        assertThat(kthLargest.add(4)).isEqualTo(6);
    }
}
