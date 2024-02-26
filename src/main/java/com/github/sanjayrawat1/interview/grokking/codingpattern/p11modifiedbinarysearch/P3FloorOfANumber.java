package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of numbers sorted in ascending order, find the floor of a given number 'key'. The floor of
 * the 'key' will be the biggest element in the given array smaller than or equal to the 'key'.
 * <br>
 * Write a function to return the index of the floor of the 'key'. If there isn't a floor, return -1.
 *
 * @author Sanjay Singh Rawat
 */
public class P3FloorOfANumber {

    public static int searchFloorOfNumber(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        if (key < arr[start]) {
            return -1;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == key) {
                return mid;
            }

            if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // since the loop is running until 'start <= end', so at the end of the while loop, 'start === end+1'
        // we are not able to find the element in the given array, so the next smaller number will be arr[end]
        return end;
    }

    public static void main(String[] args) {
        assertThat(searchFloorOfNumber(new int[] {4, 6, 10}, 6)).isEqualTo(1);
        assertThat(searchFloorOfNumber(new int[] {1, 3, 8, 10, 15}, 12)).isEqualTo(3);
        assertThat(searchFloorOfNumber(new int[] {4, 6, 10}, 17)).isEqualTo(2);
        assertThat(searchFloorOfNumber(new int[] {4, 6, 10}, -1)).isEqualTo(-1);
        assertThat(searchFloorOfNumber(new int[] {1, 2, 3, 4, 8, 10, 10, 12, 19}, 5)).isEqualTo(3);
    }
}
