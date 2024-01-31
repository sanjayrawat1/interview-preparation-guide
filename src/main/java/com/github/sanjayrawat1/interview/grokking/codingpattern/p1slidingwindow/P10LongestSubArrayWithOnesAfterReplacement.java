package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * <p>
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * <li>
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * <p>
 * Output: 6
 * <p>
 * Explanation: [1,1,1,0,0,<b>1</b>,1,1,1,1,<b>1</b>]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <li>
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * <p>
 * Output: 10
 * <p>
 * Explanation: [0,0,1,1,<b>1,1</b>,1,1,1,<b>1</b>,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * @author Sanjay Singh Rawat
 */
public class P10LongestSubArrayWithOnesAfterReplacement {

    public static void main(String[] args) {
        assertThat(slidingWindow(new int[] {0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2)).isEqualTo(6);
        assertThat(slidingWindow(new int[] {0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3)).isEqualTo(9);
    }

    /**
     * We'll iterate through the array to add one number at a time in the window. We'll also keep track of the maximum number
     * of repeating 1s in the current window (let's call it maxOnesCount). So at any time, we know that we can have a window
     * with 1s repeating `maxOnesCount` times, so we should try to replace the remaining 0s. If we have more than `k` remaining
     * 0s, we should shrink the window as we are not allowed to replace more than `k` 0s.
     */
    private static int slidingWindow(int[] arr, int k) {
        int left = 0;
        int maxOnesCount = 0;
        int maxLength = 0;

        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == 1) {
                maxOnesCount++;
            }

            // current window size is from left to right, overall we have a
            // maximum of 1s repeating maxOnesCount times, this means we can have a window
            // with maxOnesCount 1s and the remaining are 0s which should replace with 1s
            // now, if the remaining 0s are more that k, it is the time to shrink the
            // window as we are not allowed to replace more than k 0s
            int zerosToReplace = right - left + 1 - maxOnesCount;
            if (zerosToReplace > k) {
                if (arr[left] == 1) {
                    maxOnesCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
