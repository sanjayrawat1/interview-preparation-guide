package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an unsorted array of numbers, find the `K` largest numbers in it.
 *
 * @author Sanjay Singh Rawat
 */
public class P1TopKNumbers {

    /**
     * A brute force solution could be to sort the array and return the largest K numbers. The time
     * complexity of such an algorithm will be O(N*logN).
     * <br>
     * The best data structure that comes to mind to keep track of K elements is Heap.
     * Here we need K largest numbers so we can use min heap to solve this problem.
     */
    public static int[] findKLargestNumbers(int[] arr, int k) {
        int[] result = new int[k];

        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            result[--k] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        assertThat(findKLargestNumbers(new int[] {3, 1, 5, 12, 2, 11}, 3)).containsExactly(12, 11, 5);
        assertThat(findKLargestNumbers(new int[] {5, 12, 11, -1, 12}, 3)).containsExactly(12, 12, 11);
    }
}
