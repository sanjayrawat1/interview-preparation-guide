package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * <br>
 * We are given an unsorted array containing `n` numbers taken from the range `1 to n`. The array has
 * some numbers appearing twice, find all these duplicate numbers without using extra space.
 * <br>
 * <li> Example 1:
 * <br>
 * Input: nums = [4,3,2,7,8,2,3,1]
 * <br>
 * Output: [2,3]
 *
 * <li> Example 2:
 * <br>
 * Input: nums = [1,1,2]
 * <br>
 * Output: [1]
 *
 * <li> Example 3:
 *
 * Input: nums = [1]
 * <br>
 * Output: []
 *
 * @author Sanjay Singh Rawat
 */
public class P5FindAllDuplicateNumbers {

    public static void main(String[] args) {
        assertThat(findAllDuplicates(new int[] {4, 3, 2, 7, 8, 2, 3, 1})).contains(2, 3);
        assertThat(findAllDuplicates(new int[] {1, 1, 2})).contains(1);
        assertThat(findAllDuplicates(new int[] {1})).containsSequence();
    }

    private static List<Integer> findAllDuplicates(int[] nums) {
        int i = 0;

        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        List<Integer> duplicates = new ArrayList<>();

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                duplicates.add(nums[i]);
            }
        }

        return duplicates;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
