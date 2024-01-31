package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P8SubArraysWithProductLessThanTarget {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {2, 5, 3, 10}, 30))
                .containsExactly(
                        List.of(2),
                        List.of(5),
                        List.of(2, 5),
                        List.of(3),
                        List.of(5, 3),
                        List.of(10)
                );
    }

    /**
     * Time complexity: (N^3)
     * <br>
     * Space Complexity: O(N)
     */
    private static List<List<Integer>> twoPointers(int[] arr, int target) {
        List<List<Integer>> subArrays = new ArrayList<>();
        int left = 0;
        int product = 1;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            while (product >= target && left < arr.length) {
                product /= arr[left];
                left++;
            }

            List<Integer> tempList = new ArrayList<>();
            for (int i = right; i > left - 1; i--) {
                tempList.add(0, arr[i]);
                subArrays.add(new ArrayList<>(tempList));
            }
        }
        return subArrays;
    }
}
