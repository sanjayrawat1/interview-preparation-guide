package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of sorted numbers and a target sum, find a pair in the array whose sum is
 * equal to the given target.
 *
 * @author Sanjay Singh Rawat
 */
public class P1PairWithTargetSum {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {1, 2, 3, 4, 6}, 6)).containsExactly(1, 3);
        assertThat(twoPointers(new int[] {2, 5, 9, 11}, 11)).containsExactly(0, 2);
        assertThat(twoPointers(new int[] {3,3}, 6)).containsExactly(0, 1);
        assertThat(twoPointers(new int[] {2,7,11,15}, 9)).containsExactly(0, 1);
        assertThat(twoPointers(new int[] {2,7,11,15}, 16)).isEmpty();

        assertThat(hashMap(new int[] {1, 2, 3, 4, 6}, 6)).containsExactly(1, 3);
        assertThat(hashMap(new int[] {2, 5, 9, 11}, 11)).containsExactly(0, 2);
        assertThat(hashMap(new int[] {3,3}, 6)).containsExactly(0, 1);
        assertThat(hashMap(new int[] {2,7,11,15}, 9)).containsExactly(0, 1);
        assertThat(hashMap(new int[] {2,7,11,15}, 16)).isEmpty();
    }

    /**
     * Time complexity: O(N)
     * <br>
     * Space complexity: O(1)
     */
    private static int[] twoPointers(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int currentSum = arr[start] + arr[end];
            if (currentSum == target) {
                return new int[] {start, end};
            }
            if (currentSum < target) {
                start++;
            } else {
                end--;
            }
        }
        return new int[]{};
    }

    /**
     * Time complexity: O(N)
     * <br>
     * Space complexity: O(N)
     */
    private static int[] hashMap(int[] arr, int target) {
        Map<Integer, Integer> numbersIndex = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (numbersIndex.containsKey(complement)) {
                return new int[] {numbersIndex.get(complement), i};
            }
            numbersIndex.put(arr[i], i);
        }
        return new int[] {};
    }
}
