package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 *
 * @author Sanjay Singh Rawat
 */
public class P5TripletSumToZero {

    public static void main(String[] args) {
        assertThat(twoPointersSpaceEfficient(new int[] {-3, 0, 1, 2, -1, 1, -2}))
                .containsExactly(List.of(-3, 1, 2), List.of(-2, 0, 2), List.of(-2, 1, 1), List.of(-1, 0, 1));
        assertThat(twoPointersSpaceEfficient(new int[] {-5, 2, -1, -2, 3})).
                containsExactly(List.of(-5, 2, 3), List.of(-2, -1, 3));
        // below input will help to understand the logic to skip the duplicates.
        assertThat(twoPointersSpaceEfficient(new int[] {-2, -2, -2, -1, -1, -1, 0, 0, 0, 2, 2, 2, 2})).
                containsExactly(List.of(-2, 0, 2), List.of(-1, -1, 2), List.of(0, 0, 0));

        assertThat(twoPointers(new int[] {-3, 0, 1, 2, -1, 1, -2}))
                .containsExactly(List.of(-3, 1, 2), List.of(-2, 0, 2), List.of(-2, 1, 1), List.of(-1, 0, 1));
        assertThat(twoPointers(new int[] {-5, 2, -1, -2, 3})).
                containsExactly(List.of(-5, 2, 3), List.of(-2, -1, 3));
        // below input will help to understand the logic to skip the duplicates.
        assertThat(twoPointers(new int[] {-2, -2, -2, -1, -1, -1, 0, 0, 0, 2, 2, 2, 2})).
                containsExactly(List.of(-2, 0, 2), List.of(-1, -1, 2), List.of(0, 0, 0));
    }

    /**
     * First we will sort the input array and then iterate through it taking one number at a time.
     * Let's say during our iteration we are at number 'X', so we need to find 'Y' and 'Z' such that X + Y + Z = 0.
     * At this stage, our problem translates into finding a pair whose sum is equal to "-X" (as from the above equation Y + Z = -X).
     * <p>
     * We need to find the unique triplets, to handle this, we have to skip any duplicate numbers.
     * Since we will be sorting the array, so all the duplicates number will be next to each other and are easier to skip.
     * <li>
     * Time Complexity: Sorting the algorithm will take O(N * log N). The searchPair() will take O(N). As we are calling searchPair()
     * for every number in the input array, this means that overall searchTriplets() will take O(N * log N + N^2), which is asymptotically
     * equivalent to O(N^2).
     * <li>
     * Space Complexity: Ignoring the space required for the output array, the space complexity of the algorithm will be O(1).
     */
    private static List<List<Integer>> twoPointersSpaceEfficient(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                // skip the same element to avoid dupes
                continue;
            }
            searchPair(arr, -arr[i], i + 1, triplets);
        }

        return triplets;
    }

    private static void searchPair(int[] arr, int targetSum, int start, List<List<Integer>> triplets) {
        int end = arr.length - 1;
        while (start < end) {
            int currentSum = arr[start] + arr[end];
            if (currentSum == targetSum) {
                triplets.add(Arrays.asList(-targetSum, arr[start], arr[end]));
                start++;
                end--;

                while (start < end && arr[start] == arr[start - 1]) {
                    // skip same element to avoid duplicates
                    start++;
                }

                while (start < end && arr[end] == arr[end + 1]) {
                    // skip same element to avoid duplicates
                    end--;
                }
            } else if (targetSum > currentSum) {
                start++;
            } else {
                end--;
            }
        }
    }

    /**
     * Space Complexity: O(N) because of the set.
     */
    private static List<List<Integer>> twoPointers(int[] arr) {
        Set<List<Integer>> uniqueTriplets = new LinkedHashSet<>();
        Arrays.sort(arr);
        int targetSum = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];
                if (currentSum == targetSum) {
                    uniqueTriplets.add(List.of(arr[i], arr[left], arr[right]));
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(uniqueTriplets);
    }
}
