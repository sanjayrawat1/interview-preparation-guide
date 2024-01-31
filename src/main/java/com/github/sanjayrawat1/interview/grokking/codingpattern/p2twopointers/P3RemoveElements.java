package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an unsorted array of numbers and a target 'key', remove all instances of key in-place
 * and return the new length of the array.
 *
 * @author Sanjay Singh Rawat
 */
public class P3RemoveElements {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {3, 2, 3, 6, 3, 10, 9, 3}, 3)).isEqualTo(4);
        assertThat(twoPointers(new int[] {2, 11, 2, 2, 1}, 2)).isEqualTo(2);
    }

    private static int twoPointers(int[] arr, int key) {
        // pointed for index of the next element which is not the key
        int nextElement = 0;

        for (int idx = 0; idx < arr.length; idx++) {
            if (key != arr[idx]) {
                arr[nextElement] = arr[idx];
                nextElement++;
            }
        }

        return nextElement;
    }
}
