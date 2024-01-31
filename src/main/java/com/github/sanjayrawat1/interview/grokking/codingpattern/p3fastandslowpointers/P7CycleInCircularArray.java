package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/circular-array-loop/
 * <br>
 * We are given an array containing positive and negative numbers. Suppose the array contains a number 'M' at a particular index.
 * Now, if 'M' is positive we will move forward 'M' indices and if 'M' is negative move backwards 'M' indices.
 * You should assume that the array is circular which means two things:
 * <ol>
 *     <li> If, while moving forward, we reach the end of the array, we will jump to the first element to continue the movement.
 *     <li> If, while moving backward, we reach the beginning of the array, we will jump to the last element to continue the movement.
 * </ol>
 * Write a method to determine <b>if the array has a cycle.</b> The cycle should have more than one element and should follow
 * one direction which means the cycle should not contain both forward and backward movements.
 *
 * <ul>
 *     <li> Example 1: <br>
 *     Input: [1, 2, -1, 2, 2] <br>
 *     Output: true <br>
 *     Explanation: The array has a cycle among indices: 0 -> 1 -> 3 -> 0 <br>
 *     <li> Example 2: <br>
 *     Input: [2, 2, -1, 2] <br>
 *     Output: true <br>
 *     Explanation: The array has a cycle among indices: 1 -> 3 -> 1 <br>
 *     <li> Example 3: <br>
 *     Input: [2, 1, -1, -2] <br>
 *     Output: false <br>
 *     Explanation: The array does not have any cycle.
 * </ul>
 *
 * @author Sanjay Singh Rawat
 */
public class P7CycleInCircularArray {

    public static void main(String[] args) {
        assertThat(circularArrayLoopExists(new int[] {1, 2, -1, 2, 2})).isTrue();
        assertThat(circularArrayLoopExists(new int[] {2, 2, -1, 2})).isTrue();
        assertThat(circularArrayLoopExists(new int[] {2, 1, -1, -2})).isFalse();
    }

    private static boolean circularArrayLoopExists(int[] arr) {
        boolean[] visited = new boolean[arr.length];
        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {
            if (visited[currentIndex]) {
                continue;
            }
            boolean isForward = arr[currentIndex] >= 0;
            int slow = currentIndex;
            int fast = currentIndex;

            while (true) {
                slow = findNextIndex(arr, isForward, slow);
                fast = findNextIndex(arr, isForward, fast);
                if (fast != -1) {
                    fast = findNextIndex(arr, isForward, fast);
                }
                if (slow == -1 || fast == -1) {
                    break;
                }

                visited[slow] = true;

                if (slow == fast) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        boolean direction = arr[currentIndex] >= 0;
        if (isForward != direction) {
            // change in direction, return -1
            return -1;
        }

        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if (nextIndex < 0) {
            // wrap around for negative numbers
            nextIndex += arr.length;
        }

        // one element cycle, return -1
        if (nextIndex == currentIndex) {
            nextIndex = -1;
        }

        return nextIndex;
    }
}
