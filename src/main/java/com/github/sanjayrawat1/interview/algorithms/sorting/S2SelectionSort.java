package com.github.sanjayrawat1.interview.algorithms.sorting;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Selection sort works by selecting the minimum value in a list and swapping it with the first value in the
 * list. It then starts at the second position, selects the smallest value in the remaining list, and swaps
 * it with the second element. It continues iterating through the list and swapping elements until it reaches
 * the end of the list.
 *
 * @author Sanjay Singh Rawat
 */
public class S2SelectionSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92};
        sort(arr);
        assertThat(arr).containsExactly(1, 1, 2, 2, 4, 8, 32, 43, 43, 55, 63, 92, 123, 123, 234, 345, 5643);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
