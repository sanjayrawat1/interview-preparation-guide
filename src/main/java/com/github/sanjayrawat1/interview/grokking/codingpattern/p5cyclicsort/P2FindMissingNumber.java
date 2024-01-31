package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/missing-number/
 * <br>
 * We are given an array containing `n` distinct numbers taken from the range `0 to n`.
 * Since the array has only `n` numbers out of the total `n + 1` numbers, find out the missing number.
 *
 * @author Sanjay Singh Rawat
 */
public class P2FindMissingNumber {

    public static void main(String[] args) {
        assertThat(findMissing(new int[] {0, 1})).isEqualTo(2);
        assertThat(findMissing(new int[] {3, 0, 1})).isEqualTo(2);
        assertThat(findMissing(new int[] {4, 0, 3, 1})).isEqualTo(2);
        assertThat(findMissing(new int[] {2, 0, 3, 1})).isEqualTo(4);
        assertThat(findMissing(new int[] {8, 3, 5, 2, 4, 6, 0, 1})).isEqualTo(7);
    }

    private static int findMissing(int[] nums) {
        int i = 0;
        int n = nums.length;
        // [3, 0, 1]
        // sort the array
        while (i < n) {
            int j = nums[i];
            if (nums[i] < n && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        // find the first number missing from its index
        // that will be our required number
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return n;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}