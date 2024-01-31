package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * <br>
 * Given an unsorted array containing numbers, find the smallest missing positive number in it.
 *
 * @author Sanjay Singh Rawat
 */
public class P7FindSmallestMissingPositiveNumber {

    public static void main(String[] args) {
        assertThat(findSmallestMissing(new int[] {-3, 1, 5, 4, 2})).isEqualTo(3);
        assertThat(findSmallestMissing(new int[] {3, -2, 0, 1, 2})).isEqualTo(4);
        assertThat(findSmallestMissing(new int[] {3, 2, 5, 1})).isEqualTo(4);
    }

    private static int findSmallestMissing(int[] nums) {
        int i = 0;

        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
