package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of numbers sorted in ascending order, find the ceiling of a given number 'key'. The ceiling
 * of the 'key' will be the smallest element in the given array greater than or equal to the 'key'.
 * <br>
 * Write a function to return the index of the ceiling of the key. If there isn't any ceiling return -1.
 *
 * @author Sanjay Singh Rawat
 */
public class P2CeilingOfANumber {

    public static int searchCeilingOfNumber(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        if (key > arr[end]) {
            return -1;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                // found the key
                return mid;
            }
            if (arr[mid] > key) {
                // key is in first half
                end = mid - 1;
            } else {
                // key is in second half
                start = mid + 1;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end + 1'
        // we are not able to find the element in the given array, so the next big number will be 'arr[start]'
        return start;
    }

    public static void main(String[] args) {
        assertThat(searchCeilingOfNumber(new int[] {4, 6, 10}, 6)).isEqualTo(1);
        assertThat(searchCeilingOfNumber(new int[] {1, 3, 8, 10, 15}, 12)).isEqualTo(4);
        assertThat(searchCeilingOfNumber(new int[] {4, 6, 10}, 17)).isEqualTo(-1);
        assertThat(searchCeilingOfNumber(new int[] {4, 6, 10}, -1)).isEqualTo(0);
        assertThat(searchCeilingOfNumber(new int[] {1, 2, 3, 4, 8, 10, 10, 12, 19}, 5)).isEqualTo(4);
    }
}
