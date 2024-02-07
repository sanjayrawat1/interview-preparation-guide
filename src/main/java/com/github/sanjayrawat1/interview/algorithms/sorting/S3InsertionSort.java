package com.github.sanjayrawat1.interview.algorithms.sorting;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Insertion sort works by building up a sorted array at the beginning of the list. It begins the sorted array
 * with the first element. Then it inspects the next element and swaps it backwards into the sorted array until
 * it is in sorted position. It continues iterating through the list and swapping the new items backwards into
 * the sorted portion until it reaches the end.
 *
 * @author Sanjay Singh Rawat
 */
public class S3InsertionSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92};
        sort(arr);
        assertThat(arr).containsExactly(1, 1, 2, 2, 4, 8, 32, 43, 43, 55, 63, 92, 123, 123, 234, 345, 5643);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
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
