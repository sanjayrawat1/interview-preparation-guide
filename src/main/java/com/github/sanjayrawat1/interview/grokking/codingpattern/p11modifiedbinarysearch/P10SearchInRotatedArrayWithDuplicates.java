package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * How do we search in a sorted and rotated array that also has duplicates?
 *
 * @author Sanjay Singh Rawat
 */
public class P10SearchInRotatedArrayWithDuplicates {

    /**
     * The only difference from the {@link P9SearchInRotatedArray} solution:
     * <br>
     * The only problematic scenario is when the numbers at indices `start`, `mid`, and `end` are the same,
     * as in this case, we can't decide which part of the array is sorted. In such a case, the best we can
     * do is to skip one number from both ends: `start = start + 1` and `end = end - 1`.
     *
     * <li> This algorithm will run most of the time in O(logN). However, since we only skip two numbers in
     * case of duplicates instead of half of the numbers, the worst case time complexity will become O(N).
     */
    public static int searchRotatedArray(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            // if numbers at indexes start, mid, and end are same, we can't choose a side
            // the best we can do, is to skip one number from both ends as key !== arr[mid]
            if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
                start++;
                end--;
            } else if (arr[start] <= arr[mid]) {
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
        assertThat(searchRotatedArray(new int[] {3, 7, 3, 3, 3}, 7)).isEqualTo(1);
    }
}
