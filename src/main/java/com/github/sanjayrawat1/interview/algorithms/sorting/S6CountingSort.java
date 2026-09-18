package com.github.sanjayrawat1.interview.algorithms.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sanjayrawat1
 */
public class S6CountingSort {

    public static void main(String[] args) {
        List<Integer> nums = List.of(5, 3, 6, 9, 1, 3, 7, 7, 7, 2, 3, 9);
        sort(nums);
    }

    public static List<Integer> sort(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return new ArrayList<>();
        }
        int max = Collections.max(nums);
        int[] counts = new int[max + 1];

        for (int num : nums) {
            counts[num]++;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result.add(i);
            }
        }

        return result;
    }
}
