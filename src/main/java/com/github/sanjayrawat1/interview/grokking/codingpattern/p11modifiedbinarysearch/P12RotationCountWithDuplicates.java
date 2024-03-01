package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * <p>
 * How do we find the rotation count of a sorted and rotated array that has duplicates?
 *
 * @author Sanjay Singh Rawat
 */
public class P12RotationCountWithDuplicates {

    /**
     * The only difference from {@link P11RotationCount} solution:
     * <br>
     * Before incrementing `start` or decrementing `end`, we will check if either of them is the smallest
     * number.
     * <li> This algorithm will run in O(logN) most of the time, but since we only skip two numbers in case
     * of duplicates instead of the half of the numbers, therefore the worst case time complexity will become
     * O(N).
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

            // if mid is smaller than the next element
            if (mid > start && arr[mid - 1] > arr[mid]) {
                return mid;
            }

            // if numbers at indices start, mid, and end are same, we can't choose a side
            // the best we can do is to skip one number from both ends if they are not the smallest number
            if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
                if (arr[start] > arr[start + 1]) {
                    // if element at start+1 is not the smallest
                    return start + 1;
                }
                start++;
                if (arr[end - 1] > arr[end]) {
                    // if the element at end is not the smallest
                    return end;
                }
                end--;
            }
            // left side is sorted, so the pivot is on right side
            else if (arr[start] < arr[mid] || (arr[start] == arr[mid] && arr[mid] > arr[end])) {
                start = mid + 1;
            } else {
                // right side is sorted, so the pivot is on the left side
                end = mid - 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        assertThat(countRotations(new int[] {3, 3, 7, 3})).isEqualTo(3);
        assertThat(countRotations(new int[] {3, 3, 7, 7, 3})).isEqualTo(4);
    }
}
