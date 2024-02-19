package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-search/
 * <p>
 * Given a sorted array of numbers, find if a given number 'key' is present in the array. Though we know that
 * the array is sorted, we don't know if it is sorted in ascending or descending order. You should assume that
 * the array can have duplicates.
 * <br>
 * Write a function to return the index of the 'key' if it is present in the array, otherwise return -1.
 *
 * @author Sanjay Singh Rawat
 */
public class P1OrderAgnosticBinarySearch {

    /**
     * Let's first solve this problem assuming that the input array is sorted in ascending order. Here are the
     * steps for binary search:
     * <ol>
     *     <li> Let's assume 'start' is pointing to the first index and 'end' is pointing to the last index of
     *          the input array (let's call it 'arr'). This means: <br>
     *          int start = 0; <br>
     *          int end = arr.length - 1;
     *     <li> First, we will find the 'middle' of 'start' and 'end'. An easy way to find the middle could be
     *          middle = (start + end)/2. The safest way to find the middle of two numbers without getting an
     *          overflow is as follows: <br>
     *          middle = start + (end - start) / 2
     *     <li> Next, we will see if the 'key' is equal to the number at index 'middle'. If it is equal we
     *          return 'middle' as the required index.
     *     <li> If 'key' is not equal to the number at index 'middle', we have to check two things:
     *          <ol>
     *              <li> If key < arr[middle], then we can conclude that the key will be smaller than all the
     *                   numbers after index middle as the array is sorted in the ascending order. Hence, we
     *                   can reduce our search to end = mid - 1.
     *              <li> If key > arr[middle], then we can conclude that the key will be greater than all the
     *                   numbers before index middle as the array is sorted in the ascending order. Hence, we
     *                   can reduce our search to stat = mid + 1.
     *              <li> We will repeat step 2-4 with new ranges of start to end. If any time start becomes
     *                   greater than end, this means that we can't find the key in the input array and we
     *                   must return -1.
     *          </ol>
     *          If the array is sorted in the descending order, we have to update the step 4 above as:
     *          <ul>
     *              <li> If key > arr[middle], then we can conclude that the key will be greater than all the
     *                   numbers after index middle as the array is sorted in the descending order. Hence, we
     *                   can reduce our search to end = mid - 1.
     *              <li> If key < arr[middle], then we can conclude that the key will be smaller than all the
     *                   numbers before index middle as the array is sorted in the descending order. Hence,
     *                   we can reduce our search to start = mid + 1.
     *          </ul>
     *      Finally, how can we figure out the sort order of the input array? We can compare the numbers
     *      pointed out by start and end index to find the sort order. If arr[start] < arr[end], it means
     *      that the numbers are sorted in ascending order otherwise they are sorted in the descending order.
     * </ol>
     * <li> Since, we are reducing the search range by half at every step, this means that the time complexity
     *      of our algorithm will be O(log N) where N is the total elements in the given array.
     * <li> The algorithm runs in constant space O(1).
     */
    public static int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        // check to see if arr is sorted ascending or descending
        boolean isAscending = arr[start] < arr[end];

        while (start <= end) {
            // calculate the middle of the current range
            int middle = start + (end - start) / 2;

            if (key == arr[middle]) {
                return middle;
            }

            if (isAscending) {
                // ascending order
                if (key < arr[middle]) {
                    // the key can be in the first half
                    end = middle - 1;
                } else {
                    // key > arr[middle], so the key can be in the second half
                    start = middle + 1;
                }
            } else {
                // descending order
                if (key > arr[middle]) {
                    // the key can be in the first half
                    end = middle - 1;
                } else {
                    // key < arr[middle], the key can be in the second half
                    start = middle + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assertThat(binarySearch(new int[] {4, 6, 10}, 10)).isEqualTo(2);
        assertThat(binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7}, 5)).isEqualTo(4);
        assertThat(binarySearch(new int[] {10, 6, 4}, 10)).isEqualTo(0);
        assertThat(binarySearch(new int[] {10, 6, 4}, 4)).isEqualTo(2);
    }
}
