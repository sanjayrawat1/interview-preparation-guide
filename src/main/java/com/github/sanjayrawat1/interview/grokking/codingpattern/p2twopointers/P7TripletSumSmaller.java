package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of unsorted numbers and a target sum, count all triplets in it such that
 * arr[i] + arr[j] + arr[k] < target where i, j and k are three different indices.
 *
 * @author Sanjay Singh Rawat
 */
public class P7TripletSumSmaller {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {-1, 0, 2, 3}, 3)).isEqualTo(2);
        assertThat(twoPointers(new int[] {-1, 4, 2, 1, 3}, 5)).isEqualTo(4);
        assertThat(twoPointers(new int[] {-2,0,1,3}, 2)).isEqualTo(2);
        assertThat(twoPointers(new int[] {-1, 0, 2, 3}, 3)).isEqualTo(2);
        assertThat(twoPointers(new int[] {}, 0)).isEqualTo(0);
        assertThat(twoPointers(new int[] {0}, 0)).isEqualTo(0);

        assertThat(tripletsWithSmallerSum(new int[] {-1, 0, 2, 3}, 3))
                .containsExactly(List.of(-1, 0, 3), List.of(-1, 0, 2));
        assertThat(tripletsWithSmallerSum(new int[] {-1, 4, 2, 1, 3}, 5))
                .containsExactly(List.of(-1, 1, 4), List.of(-1, 1, 3), List.of(-1, 1, 2), List.of(-1, 2, 3));
    }

    private static int twoPointers(int[] arr, int target) {
        Arrays.sort(arr);
        int count = 0;

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];
                if (currentSum < target) {
                    // we found the triplet
                    // since arr[right] >= arr[left], therefore, we can replace arr[right]
                    // by any number between left and right to get a sum less than the target
                    count += right - left;
                    left++;
                } else {
                    // we need a pair with a smaller sum
                    right--;
                }
            }
        }
        return count;
    }

    private static List<List<Integer>> tripletsWithSmallerSum(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];
                if (currentSum < target) {
                    for (int j = right; j > left; j--) {
                        triplets.add(Arrays.asList(arr[i], arr[left], arr[j]));
                    }
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
}
