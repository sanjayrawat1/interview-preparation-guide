package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 * <li>
 * Example 1:
 * <br>
 * Input: nums = [-4,-1,0,3,10]
 * <br>
 * Output: [0,1,9,16,100]
 * <br>
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * <br>
 * After sorting, it becomes [0,1,9,16,100].
 * <li>
 * Example 2:
 * <br>
 * Input: nums = [-7,-3,2,3,11]
 * <br>
 * Output: [4,9,9,49,121]
 *
 * @author Sanjay Singh Rawat
 */
public class P4SquaringSortedArray {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {-4, -1, 0, 3, 10})).containsExactly(0, 1, 9, 16, 100);
        assertThat(twoPointers(new int[] {-7, -3, 2, 3, 11})).containsExactly(4, 9, 9, 49, 121);
        assertThat(twoPointers(new int[] {-2, -1, 0, 2, 3})).containsExactly(0, 1, 4, 4, 9);
        assertThat(twoPointers(new int[] {-3, -1, 0, 1, 2})).containsExactly(0, 1, 1, 4, 9);
    }

    /**
     * This is a straightforward question, the only trick is that we can have negative numbers in the input array,
     * which will make it a little difficult to generate the output array with squares in sorted order.
     * <p>
     * Since the numbers at the both ends can give us the largest square, an alternate approach could be to use
     * two pointers starting at both ends of the input array.
     * At any step, whichever pointer gives us the bigger square, we add it to the result array and move to the
     * next/previous number according to the pointer.
     */
    private static int[] twoPointers(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int highestSquareIndex = end;
        int[] squares = new int[arr.length];

        while (start <= end) {
            int startSquare = arr[start] * arr[start];
            int endSquare = arr[end] * arr[end];

            if (startSquare > endSquare) {
                squares[highestSquareIndex] = startSquare;
                start++;
            } else {
                squares[highestSquareIndex] = endSquare;
                end--;
            }

            highestSquareIndex--;
        }

        return squares;
    }
}
