package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same. Then return the number
 * of unique elements in nums.
 * <p>
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 * <li>
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present
 * in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * <li>
 * Return k.
 * <li>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * <p>
 * Output: 2, nums = [1,2,_]
 * <p>
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <li>
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * <p>
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * <p>
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * @author Sanjay Singh Rawat
 */
public class P2RemoveDuplicates {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {2, 7, 11, 15})).isEqualTo(4);
        assertThat(twoPointers(new int[] {1, 1, 2})).isEqualTo(2);
        assertThat(twoPointers(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4})).isEqualTo(5);
        assertThat(twoPointers(new int[] {2, 3, 3, 3, 6, 9, 9})).isEqualTo(4);
        assertThat(twoPointers(new int[] {2, 2, 2, 11})).isEqualTo(2);
        assertThat(twoPointers(new int[] {2, 2, 2})).isEqualTo(1);
    }

    private static int twoPointers(int[] arr) {
        // shift the elements left when we encounter duplicates

        // one pointer for iterating
        int idx = 1;
        // one pointer for placing this next non-duplicate
        int nextNonDuplicate = 1;

        while (idx < arr.length) {
            if (arr[nextNonDuplicate - 1] != arr[idx]) {
                arr[nextNonDuplicate] = arr[idx];
                nextNonDuplicate++;
            }
            idx++;
        }
        return nextNonDuplicate;
    }
}
