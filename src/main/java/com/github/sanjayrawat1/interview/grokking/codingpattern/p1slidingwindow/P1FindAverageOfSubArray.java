package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array, find the average of all contiguous subarrays of size 'K' in it.
 * Array = [1, 3, 2, 6, -1, 4, 1, 8, 2]
 * K = 5
 *
 * @author Sanjay Singh Rawat
 */
public class P1FindAverageOfSubArray {

    public static void main(String[] args) {
        assertThat(bruteForce(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)).containsExactly(2.2, 2.8, 2.4, 3.6, 2.8);
        assertThat(slidingWindow(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)).containsExactly(2.2, 2.8, 2.4, 3.6, 2.8);
    }

    private static double[] bruteForce(int[] arr, int k) {
        double[] result = new double[arr.length - k + 1];

        for (int left = 0; left < arr.length - k + 1; left++) {
            int sum = 0;
            for (int right = left; right < left + k; right++) {
                sum += arr[right];
            }
            result[left] = (double) sum / k;
        }
        return result;
    }

    private static double[] slidingWindow(int[] arr, int k) {
        double[] ans = new double[arr.length - k + 1];
        int curr = 0;
        int left = 0;

        for (int right = 0; right < arr.length; right++) {
            // add next element
            curr += arr[right];

            // slide the window forward
            // we don't need to slide if we have not hit the required window size k
            if (right >= k - 1) {
                // we are **AUTOMATICALLY** returning the window average once we hit the window size
                // and pushing to the output array
                ans[left] = (double) curr / k;

                // subtracting the element going out
                curr -= arr[left];

                // then sliding the window forward
                left++;

                // adding the element coming in, in the outer/previous loop
                // and repeating this process until we hit the end of the array
            }

        }
        return ans;
    }
}
