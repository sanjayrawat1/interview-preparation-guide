package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a Bitonic array, find if a given `key` is present in it. An array is considered bitonic if it is
 * monotonically increasing and then monotonically decreasing. Monotonically increasing or decreasing means
 * that for any index i in the array arr[i] != arr[i+1].
 * <br>
 * Write a function to return the index of the key. If the key is not present, return -1.
 *
 * @author Sanjay Singh Rawat
 */
public class P8SearchBitonicArray {

    /**
     * Here is how we can search in a bitonic array:
     * <ol>
     *     <li> First, we can find the index of the maximum value of the bitonic array. Let's call the index
     *          of the maximum number maxIndex.
     *     <li> Now, we can break the array into two sub-arrays:
     *          <ul>
     *              <li> Array from index 0 to maxIndex, sorted in ascending order.
     *              <li> Array from index maxIndex + 1 to array_length - 1, sorted in descending order.
     *          </ul>
     *     <li> We can then call binary search separately in these two arrays to search the key. We can use
     *          the Order-agnostic Binary Search for searching.
     * </ol>
     */
    public static int searchBitonicArray(int[] arr, int key) {
        int maxIndex = findMaxIndex(arr);
        int keyIndex = binarySearch(arr, key, 0, maxIndex);

        if (keyIndex != -1) {
            return keyIndex;
        }

        return binarySearch(arr, key, maxIndex + 1, arr.length - 1);
    }

    private static int findMaxIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                // arr[mid] < arr[mid+1]
                start = mid + 1;
            }
        }

        // at the end of the while loop start === end
        return start;
    }

    private static int binarySearch(int[] arr, int key, int start, int end) {
        // check to see if arr is sorted ascending or descending
        boolean isAscending = arr[start] < arr[end];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            if (isAscending) {
                if (key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (key < arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        assertThat(searchBitonicArray(new int[] {1, 3, 8, 4, 3}, 4)).isEqualTo(3);
        assertThat(searchBitonicArray(new int[] {3, 8, 3, 1}, 8)).isEqualTo(1);
        assertThat(searchBitonicArray(new int[] {1, 3, 8, 12}, 12)).isEqualTo(3);
        assertThat(searchBitonicArray(new int[] {10, 9, 8}, 10)).isEqualTo(0);
        assertThat(searchBitonicArray(new int[] {10, 9, 8, 7, 6}, 12)).isEqualTo(-1);
    }
}
