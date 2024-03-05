package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an unsorted array of numbers, find Kth smallest number in it.
 * Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.
 *
 * @author Sanjay Singh Rawat
 */
public class P2KthSmallestNumber {

    /**
     * One way to solve this problem is to first sort the input array and then find the Kth smallest element.
     * Time complexity of this solution would be O(N log N) as we are sorting the array first. To reduce the
     * time complexity to O(N log K) we can use max heap.
     *
     * <li> Time Complexity: O(N log K)
     * <li> Space Complexity: O(K)
     */
    public static int findKthSmallestNumber(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("Invalid input");
        }

        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.peek();
    }

    public static void main(String[] args) {
        assertThat(findKthSmallestNumber(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)).isEqualTo(3);
        assertThat(findKthSmallestNumber(new int[] {3, 1, 5, 12, 2, 11}, 3)).isEqualTo(3);
        assertThat(findKthSmallestNumber(new int[] {5, 12, 11, -1, 12}, 3)).isEqualTo(11);
    }
}
