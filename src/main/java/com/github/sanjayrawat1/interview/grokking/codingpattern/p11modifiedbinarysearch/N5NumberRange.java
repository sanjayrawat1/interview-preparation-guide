package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * Given an array of numbers sorted in ascending order, find the range of a given number 'key'. The range of
 * the 'key' will be the first and last position of the 'key' in the array.
 * <br>
 * Write a function to return the range of the key. If the key is not present return [-1, -1].
 *
 * @author Sanjay Singh Rawat
 */
public class N5NumberRange {

    /**
     * We will try to search for the 'key' in the given range; if the 'key' is found (i.e., `key == arr[mid]`)
     * we have two options:
     * <ol>
     *     <li> When trying to find the first position of the key, we can update `end = mid - 1` to see if the
     *          key is present before middle.
     *     <li> When trying to find the last position of the key, we can update `start = mid + 1` to see if the
     *          key is present after middle.
     * </ol>
     * In both cases, we will keep track of the last position where we found the key. These position will be
     * the required range.
     */
    public static int[] findRange(int[] arr, int key) {
        int[] range = {-1, -1};

        range[0] = binarySearch(arr, key, false);
        if (range[0] != -1) {
            range[1] = binarySearch(arr, key, true);
        }
        return range;
    }

    private static int binarySearch(int[] arr, int key, boolean findMaxIndex) {
        int keyIndex = -1;

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                keyIndex = mid;
                if (findMaxIndex) {
                    // search ahead to find the last index of key
                    start = mid + 1;
                } else {
                    // search behind to find the last index of key
                    end = mid - 1;
                }
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return keyIndex;
    }

    public static void main(String[] args) {
        assertThat(findRange(new int[] {4, 6, 10}, 6)).containsExactly(1, 1);
        assertThat(findRange(new int[] {4, 6, 6, 6, 9}, 6)).containsExactly(1, 3);
        assertThat(findRange(new int[] {1, 3, 8, 10, 15}, 10)).containsExactly(3, 3);
        assertThat(findRange(new int[] {1, 3, 8, 10, 15}, 12)).containsExactly(-1, -1);
    }
}
