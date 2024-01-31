package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/sort-colors
 * <p>
 * Given an array containing 0s, 1, and 2s, sort array in-place.
 * You should treat numbers of the array as objects, hence, we can't count 0s, 1s, and 2s to recreate the array.
 *
 * @author Sanjay Singh Rawat
 */
public class P9DutchNationalFlagProblem {

    public static void main(String[] args) {
        int[] input1 = {1, 0, 2, 1, 0};
        twoPointers(input1);
        assertThat(input1).containsExactly(0, 0, 1, 1, 2);
        int[] input2 = new int[] {2, 2, 0, 1, 2, 0};
        twoPointers(input2);
        assertThat(input2).containsExactly(0, 0, 1, 2, 2, 2);
    }

    /**
     * While iterating, we will move all 0s before left and all 2s after right so that in the end,
     * all 1s will be between left and right.
     */
    private static void twoPointers(int[] arr) {
        int left = 0;
        int mid = 0;
        int right = arr.length - 1;

        while (mid <= right) {
            if (arr[mid] == 0) {
                swap(arr, left, mid);
                left++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, right);
                right--;
            }
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
