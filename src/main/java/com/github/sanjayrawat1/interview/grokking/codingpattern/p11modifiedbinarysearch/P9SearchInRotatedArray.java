package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number,
 * find if a given `key` is present in it.
 * <br>
 * Write a function to return the index of the `key` in the rotated array. If the `key` is not present, return -1.
 * You can assume that the given array does not have any duplicates.
 *
 * @author Sanjay Singh Rawat
 */
public class P9SearchInRotatedArray {

    /**
     * After calculating the middle, we can compare the numbers at indices `start` and `mid`. This will give
     * us two options:
     * <ol>
     *     <li> If `arr[start] <= arr[mid]`, the numbers from `start` to `mid` are sorted in ascending order.
     *     <li> Else, the numbers from `mid + 1` to `end` are sorted in ascending order.
     * </ol>
     * Once we know which part of the array is sorted, it is easy to adjust our ranges. For example, if
     * option 1 is true, we have two choices:
     * <ol>
     *     <li> By comparing the `key` with the numbers at index `start` and `mid` we can easily find out if
     *          the key lies between indices `start` and `mid`; if it does, we can skip the second part:
     *          `end = mid - 1`.
     *     <li> Else, we can skip the first part: `start = mid + 1`.
     * </ol>
     */
    public static int searchRotatedArray(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            if (arr[start] <= arr[mid]) {
                if (key >= arr[start] && key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (key > arr[mid] && key <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        assertThat(searchRotatedArray(new int[] {10, 15, 1, 3, 8}, 15)).isEqualTo(1);
        assertThat(searchRotatedArray(new int[] {4, 5, 7, 9, 10, -1, 2}, 10)).isEqualTo(4);
    }
}
