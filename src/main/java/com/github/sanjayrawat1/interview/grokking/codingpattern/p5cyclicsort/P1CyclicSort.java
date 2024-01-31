package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This pattern describes an interesting approach to deal with problems involving arrays containing numbers in a given range.
 * <br>
 * You are given an unsorted array containing numbers taken from the range 1 to n.
 * Write a function to sort the array in-place in O(n).
 *
 * @author Sanjay Singh Rawat
 */
public class P1CyclicSort {

    public static void main(String[] args) {
        assertThat(cyclicSort(new int[] {3, 1, 5, 4, 2})).containsSequence(1, 2, 3, 4, 5);
        assertThat(cyclicSort(new int[] {2, 6, 4, 3, 1, 5})).containsSequence(1, 2, 3, 4, 5, 6);
        assertThat(cyclicSort(new int[] {1, 5, 6, 4, 3, 2})).containsSequence(1, 2, 3, 4, 5, 6);
    }

    /**
     * <li> The time complexity of the above algorithm is O(n). Although we are not incrementing the index i when swapping the numbers,
     * this will result in more than n iterations of the loop, but in the worst-case scenario, the while loop will swap a total of
     * n-1 numbers and once a number is at its correct index, we will move on to the next number by incrementing i. So overall,
     * our algorithm will take O(n) + O(n-1) which is asymptotically equivalent to O(n).
     * <li> The algorithm runs in constant space O(1).
     */
    private static int[] cyclicSort(int[] nums) {
        // [2, 6, 4, 3, 1, 5]
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
