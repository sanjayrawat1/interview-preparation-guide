package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * <p>
 * Given an array of numbers which is sorted in ascending order and is rotated `k` times around a pivot, find `k`.
 * <br>
 * You can assume that the array does not have any duplicates.
 *
 * @author Sanjay Singh Rawat
 */
public class P11RotationCount {

    /**
     * In this problem, actually, we are asked to find the index of minimum element. The number of times the
     * minimum element is moved to the right will be equal to the number of rotations. An interesting fact
     * about the minimum element is that it is the only element in the given array which is smaller than its
     * previous element. Since the array is sorted in ascending order, all other elements are bigger than
     * their previous element.
     * <p>
     * After calculating the middle, we can compare the number at index `middle` with its `previous` and `next`
     * number. This will give us two options:
     * <ol>
     *     <li> If `arr[mid] > arr[mid + 1]`, then the element at `mid + 1` is the smallest.
     *     <li> If `arr[mid - 1] > arr[mid]`, then the element at `mid` is the smallest.
     * </ol>
     * To adjust the ranges we can compare the numbers at indices `start` and `middle` will give us two options:
     * <ol>
     *     <li> If `arr[start] < arr[mid]`, then the numbers from `start` to `middle` are sorted.
     *     <li> Else, the numbers from `middle + 1` to `end` are sorted.
     * </ol>
     */
    public static int countRotations(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            // if mid is greater than the next element
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid + 1;
            }

            // if mid is smaller than the previous element
            if (mid > start && arr[mid - 1] > arr[mid]) {
                return mid;
            }

            if (arr[start] < arr[mid]) {
                // first half is sorted, so pivot is in second half
                start = mid + 1;
            } else {
                // second half is sorted, so pivot is in first half
                end = mid - 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        assertThat(countRotations(new int[] {4, 5, 6, 7, 1, 2, 3})).isEqualTo(4);
        assertThat(countRotations(new int[] {10, 15, 1, 3, 8})).isEqualTo(2);
        assertThat(countRotations(new int[] {4, 5, 7, 9, 10, -1, 2})).isEqualTo(5);
        assertThat(countRotations(new int[] {1, 3, 8, 10})).isEqualTo(0);
    }
}
