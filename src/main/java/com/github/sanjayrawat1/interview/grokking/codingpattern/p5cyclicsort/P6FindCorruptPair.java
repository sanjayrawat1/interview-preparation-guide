package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * We are given an unsorted array containing `n` numbers taken from the range `1 to n`. The array originally
 * contained all the numbers from `1 to n`, but due to a data error, one of the numbers got duplicated which
 * also resulted in one number going missing. Find both these numbers.
 *
 * @author Sanjay Singh Rawat
 */
public class P6FindCorruptPair {

    public static void main(String[] args) {
        assertThat(corruptPair(new int[] {3, 1, 2, 5, 2})).containsSequence(2, 4);
        assertThat(corruptPair(new int[] {3, 1, 2, 3, 6, 4})).containsSequence(3, 5);
    }

    private static int[] corruptPair(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[] {nums[i], i + 1};
            }
        }

        return new int[] {-1, -1};
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
