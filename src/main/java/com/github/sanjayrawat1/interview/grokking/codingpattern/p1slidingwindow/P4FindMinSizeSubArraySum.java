package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 * <p>
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a sub array whose sum is greater than or equal to target.
 * If there is no such sub array, return 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * <p>
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * @author Sanjay Singh Rawat
 */
public class P4FindMinSizeSubArraySum {

    public static void main(String[] args) {
        assertThat(slidingWindow(new int[] {2, 3, 1, 2, 4, 3}, 7)).isEqualTo(2);
        assertThat(slidingWindow(new int[] {1, 4, 4}, 4)).isEqualTo(1);
        assertThat(slidingWindow(new int[] {1, 1, 1, 1, 1, 1, 1, 1}, 11)).isEqualTo(0);
        assertThat(slidingWindow(new int[] {2, 1, 5, 2, 3, 2}, 7)).isEqualTo(2);
        assertThat(slidingWindow(new int[] {2, 1, 5, 2, 8}, 7)).isEqualTo(1);
        assertThat(slidingWindow(new int[] {3, 4, 1, 1, 6}, 8)).isEqualTo(3);
    }

    /*
    Time complexity: O(N).
    The outer loop runs for all elements, and the inner while loop processes each element only once;
    Therefore, the time complexity of the algorithm will be O(N+N), which is asymptotically equivalent to O(N).

    Space Complexity: O(1)
     */
    private static int slidingWindow(int[] arr, int target) {
        // sliding window, BUT the window size is not fixed
        int minSize = Integer.MAX_VALUE;
        int left = 0;
        int windowSum = 0;

        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right];

            // shrink the window as small as possible until windowSum is smaller than target
            while (windowSum >= target) {
                minSize = Math.min(minSize, right - left + 1);
                windowSum -= arr[left];
                left++;
            }
        }

        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
}
