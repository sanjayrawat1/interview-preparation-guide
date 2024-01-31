package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/4sum/description/
 * <p>
 * Given an array nums of n integers, return an array of all the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 * <br>
 * 0 <= a, b, c, d < n
 * <br>
 * a, b, c, and d are distinct.
 * <br>
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * <br>
 * You may return the answer in any order.
 * <li>
 * Example 1:
 * <br>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * <br>
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <li>
 * Example 2:
 * <br>
 * Input: nums = [2,2,2,2,2], target = 8
 * <br>
 * Output: [[2,2,2,2]]
 *
 * @author Sanjay Singh Rawat
 */
public class P10QuadrupleSumToTarget {

    public static void main(String[] args) {
        assertThat(searchQuad(new int[] {4, 1, 2, -1, 1, -3}, 1))
                .containsExactly(List.of(-3, -1, 1, 4), List.of(-3, 1, 1, 2));
        assertThat(searchQuad(new int[] {2, 0, -1, 1, -2, 2}, 2))
                .containsExactly(List.of(-2, 0, 2, 2), List.of(-1, 0, 1, 2));
    }

    private static List<List<Integer>> searchQuad(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> quads = new ArrayList<>();

        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) {
                    continue;
                }
                searchPair(arr, target, i, j, quads);
            }
        }
        return quads;
    }

    private static void searchPair(int[] arr, int target, int first, int second, List<List<Integer>> quads) {
        int left = second + 1;
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[first] + arr[second] + arr[left] + arr[right];
            if (currentSum == target) {
                quads.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1]) {
                    left++;
                }
                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}
