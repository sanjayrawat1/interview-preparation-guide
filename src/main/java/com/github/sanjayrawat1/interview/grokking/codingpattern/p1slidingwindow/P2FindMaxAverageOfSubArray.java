package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/maximum-average-subarray-i/description/
 * <p>
 * You are given an integer array nums consisting of n elements, and an integer k.
 * Find a contiguous sub array whose length is equal to k that has the maximum average
 * value and return this value. Any answer with a calculation error less than 10-5 will
 * be accepted.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 * @author Sanjay Singh Rawat
 */
public class P2FindMaxAverageOfSubArray {

    public static void main(String[] args) {
        assertThat(bruteForce(new int[] {1, 12, -5, -6, 50, 3}, 4)).isEqualTo(12.75);
        assertThat(bruteForce(new int[] {5}, 1)).isEqualTo(5.0);
        assertThat(bruteForce(new int[] {-1}, 1)).isEqualTo(-1.0);

        assertThat(slidingWindow(new int[] {1, 12, -5, -6, 50, 3}, 4)).isEqualTo(12.75);
        assertThat(slidingWindow(new int[] {5}, 1)).isEqualTo(5.0);
        assertThat(slidingWindow(new int[] {-1}, 1)).isEqualTo(-1.0);
    }

    private static double bruteForce(int[] arr, int k) {
        double maxAvg = Integer.MIN_VALUE;
        for (int left = 0; left < arr.length - k + 1; left++) {
            int sum = 0;
            for (int right = left; right < left + k; right++) {
                sum += arr[right];
            }
            maxAvg = Math.max(maxAvg, (double) sum / k);
        }
        return maxAvg;
    }

    private static double slidingWindow(int[] arr, int k) {
        int left = 0;
        double maxAvg = Integer.MIN_VALUE;
        int currSum = 0;

        for (int right = 0; right < arr.length; right++) {
            currSum += arr[right];

            if (right >= k - 1) {
                maxAvg = Math.max(maxAvg, (double) currSum / k);
                currSum -= arr[left];
                left++;
            }
        }

        return maxAvg;
    }
}
