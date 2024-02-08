package com.github.sanjayrawat1.interview.grokking.codingpattern.p9twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/sliding-window-median/
 * <p>
 * Given an array of numbers and a number 'k', find the median of all the 'k' sized sub-arrays of the array.
 *
 * <li> Example 1:
 * <br> Input: nums = [1, 2, -1, 3, 5], k = 2
 * <br> Output: [1.5, 0.5, 1.0, 4.0]
 *
 * <br> Explanation: Lets consider all windows of size ‘2’: <br>
 * [1, 2] -> median is 1.5 <br>
 * [2, -1] -> median is 0.5 <br>
 * [-1, 3] -> median is 1.0 <br>
 * [3, 5] -> median is 4.0 <br>
 *
 * <li> Example 2:
 * <br> Input: nums = [1, 2, -1, 3, 5], k = 3
 * <br> Output: [1.0, 2.0, 3.0]
 * <br> Explanation: Lets consider all windows of size ‘3’: <br>
 * [1, 2, -1] -> median is 1.0 <br>
 * [2, -1, 3] -> median is 2.0 <br>
 * [-1, 3, 5] -> median is 3.0 <br>
 *
 * @author Sanjay Singh Rawat
 */
public class P2SlidingWindowMedian {

    private static Queue<Integer> maxHeap;

    private static Queue<Integer> minHeap;

    /**
     * We need to keep track of a sliding window of 'k' numbers. In each iteration, when we insert a new
     * number in the heaps, we need to remove one number from the heaps which is going out of the sliding
     * window. After the removal we need to re-balance the heaps in the same way as we did while inserting.
     *
     * <li> The time complexity of our algorithm is O(N * K) where N is the total number of elements in the
     * input array and K is the size of the sliding window. This is due to the fact that we are going through
     * all the N numbers and, while doing so, we are doing two things:
     * <ol>
     *     <li> Inserting/removing numbers from heaps of size K. This will take O(log K).
     *     <li> Removing the element going out of the sliding window. This will take O(K) as we will be
     *          searching this element in an array of size K (i.e., a heap).
     * </ol>
     * <li> Ignoring the space needed for the output array, the space complexity will be O(K) because, at
     * any time, we will be storing all the numbers within the sliding window.
     */
    public static double[] findSlidingWindowMedian(int[] nums, int k) {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();

        double[] medians = new double[nums.length - k + 1];
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            int currentNumber = nums[right];
            if (maxHeap.isEmpty() || currentNumber <= maxHeap.peek()) {
                maxHeap.offer(currentNumber);
            } else {
                minHeap.offer(currentNumber);
            }

            // balance the two heaps after number insertion
            balance();

            if (right >= k - 1) {
                if (maxHeap.size() > minHeap.size()) {
                    medians[left] = maxHeap.peek();
                } else if (maxHeap.size() == minHeap.size()) {
                    medians[left] = ((long) maxHeap.peek() + (long) minHeap.peek()) / 2.0;
                }

                int numberToRemove = nums[left];

                if (numberToRemove <= maxHeap.peek()) {
                    maxHeap.remove(numberToRemove);
                } else {
                    minHeap.remove(numberToRemove);
                }
                left++;

                // balance the two heaps after number removal
                balance();
            }
        }

        return medians;
    }

    private static void balance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        assertThat(findSlidingWindowMedian(new int[] {1, 2, -1, 3, 5}, 2)).containsExactly(1.5, 0.5, 1.0, 4.0);
        assertThat(findSlidingWindowMedian(new int[] {1, 2, -1, 3, 5}, 3)).containsExactly(1.0, 2.0, 3.0);
    }
}
