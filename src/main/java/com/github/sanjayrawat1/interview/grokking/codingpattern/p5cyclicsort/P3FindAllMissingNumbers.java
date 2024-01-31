package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * <br>
 * We are given an unsorted array containing numbers taken from the range `1 to n`. The array can have duplicates,
 * which means some numbers will be missing. Find all those missing numbers.
 *
 * @author Sanjay Singh Rawat
 */
public class P3FindAllMissingNumbers {

    public static void main(String[] args) {
        assertThat(findMissingNumbers(new int[] {2, 3, 1, 8, 2, 3, 5, 1})).containsSequence(4, 6, 7);
    }

    private static List<Integer> findMissingNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
            }
        }
        return missingNumbers;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
