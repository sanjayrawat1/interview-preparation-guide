package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of numbers sorted in ascending order, find the element in the array that has the minimum
 * difference with the given key.
 *
 * @author Sanjay Singh Rawat
 */
public class P6MinimumDifferenceElement {

    /**
     * We will try to search for the `key` in the given array. If we find the `key` we will return it as the
     * minimum difference number. If we can't find the key, (at the end of the loop) we can find the differences
     * between the `key` and the numbers pointed out by indices `start` and `end`, as these two numbers will
     * be closest to the key. The number that gives minimum difference will be our required number.
     */
    public static int searchMinDiffElement(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        if (key <= arr[start]) {
            return arr[start];
        }
        if (key >= arr[end]) {
            return arr[end];
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return arr[mid];
            }

            if (key > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // at the end of the while loop, 'start === end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'
        if (arr[start] - key < key - arr[end]) {
            return arr[start];
        }

        return arr[end];
    }

    public static void main(String[] args) {
        assertThat(searchMinDiffElement(new int[] {4, 6, 10}, 7)).isEqualTo(6);
        assertThat(searchMinDiffElement(new int[] {4, 6, 10}, 4)).isEqualTo(4);
        assertThat(searchMinDiffElement(new int[] {4, 6, 10}, 17)).isEqualTo(10);
        assertThat(searchMinDiffElement(new int[] {1, 3, 8, 10, 15}, 12)).isEqualTo(10);
    }
}
