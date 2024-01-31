package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/largest-subarray-length-k/
 * <p>
 * Given an array of positive numbers and a positive number k.
 * Find the maximum sum of any contiguous sub array of size k.
 * <p>
 * max_sub_array_of_size_k(3, [2, 1, 5, 1, 3, 2])//9
 * max_sub_array_of_size_k(2, [2, 3, 4, 1, 5])//7
 * @author Sanjay Singh Rawat
 */
public class P3FindMaxSumOfSubArray {

    public static void main(String[] args) {
        assertThat(bruteForce(new int[] {2, 1, 5, 1, 3, 2}, 3)).isEqualTo(9);
        assertThat(bruteForce(new int[] {2, 3, 4, 1, 5}, 2)).isEqualTo(7);

        assertThat(slidingWindow(new int[] {2, 1, 5, 1, 3, 2}, 3)).isEqualTo(9);
        assertThat(slidingWindow(new int[] {2, 3, 4, 1, 5}, 2)).isEqualTo(7);
    }

    /*
    Time Complexity = O(N*K), where N is the total number of elements in the given array.
     */
    private static int bruteForce(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int left = 0; left < arr.length - k + 1; left++) {

            // keep track of sum in current window
            int windowSum = 0;
            for (int right = left; right < left + k; right++) {
                windowSum += arr[right];
            }

            // if currentWindowSum is > maxWindowSum
            // set currentWindowSum to maxWindowSum
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    /*
    Time Complexity = O(N), where N is the total number of elements in the given array.
     */
    private static int slidingWindow(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int windowSum = 0;

        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right];

            if (right >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[left];
                left++;
            }
        }

        return maxSum;
    }
}
