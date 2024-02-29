package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Find the maximum value in a given Bitonic array. An array is considered Bitonic if it is monotonically
 * increasing and then monotonically decreasing. Monotonically increasing or decreasing means that for any
 * index `i` in the array `arr[i] != arr[i + 1]`.
 *
 * @author Sanjay Singh Rawat
 */
public class P7BitonicArrayMaximum {

    /**
     * A bitonic array is a sorted array, the only difference is that its first part is sorted in ascending
     * order and the second part is sorted in descending order.
     * <br>
     * Since no two consecutive numbers are same (as the array is monotonically increasing or decreasing),
     * whenever we calculate the middle, we can compare the numbers pointed out by the index middle and
     * middle + 1 to find if we are in the ascending or descending part. So:
     * <ol>
     *     <li> If `arr[mid] > arr[mid + 1]`, we are in the second (descending) part of the bitonic array.
     *          Therefore, our required number could either be pointed out by middle or will be before middle.
     *          This means we will be doing: `end = mid`.
     *     <li> If `arr[mid] < arr[mid + 1]`, we are in the first (ascending) part of the bitonic array. Therefore,
     *          the required number will be after `mid`. This means we will be doing: `start = mid + 1`.
     * </ol>
     */
    public static int findMaxInBitonicArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return arr[start];
    }

    public static void main(String[] args) {
        assertThat(findMaxInBitonicArray(new int[] {1, 2, 3, 4, 3, 2, 1})).isEqualTo(4);
        assertThat(findMaxInBitonicArray(new int[] {1, 3, 8, 12, 4, 2})).isEqualTo(12);
        assertThat(findMaxInBitonicArray(new int[] {3, 8, 3, 1})).isEqualTo(8);
        assertThat(findMaxInBitonicArray(new int[] {1, 3, 8, 12})).isEqualTo(12);
        assertThat(findMaxInBitonicArray(new int[] {10, 9, 8})).isEqualTo(10);
    }
}
