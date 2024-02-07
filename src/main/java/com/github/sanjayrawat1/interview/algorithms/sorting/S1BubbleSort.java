package com.github.sanjayrawat1.interview.algorithms.sorting;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The bubble sort method starts at the beginning of an unsorted array and 'bubbles up' unsorted values
 * towards the end, iterating through the array until it is completely sorted. It does this by comparing
 * adjacent items and swapping them if they are out of order. The method continues looping through the
 * array until no swaps occur at which point the array is sorted.
 *
 * @author Sanjay Singh Rawat
 */
public class S1BubbleSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92};
        sort(arr);
        assertThat(arr).containsExactly(1, 1, 2, 2, 4, 8, 32, 43, 43, 55, 63, 92, 123, 123, 234, 345, 5643);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
