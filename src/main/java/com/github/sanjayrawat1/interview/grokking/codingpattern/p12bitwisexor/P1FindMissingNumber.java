package com.github.sanjayrawat1.interview.grokking.codingpattern.p12bitwisexor;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of `n - 1` integers in the range from 1 to n, find the one number that is missing from
 * the array.
 *
 * @author Sanjay Singh Rawat
 */
public class P1FindMissingNumber {

    /**
     * A straight forward approach to solve this problem can be:
     * <ol>
     *     <li> Find the sum of all integers from `1` to `n`; let's call it `s1`.
     *     <li> Subtract all the numbers in the input array from `s1`; this will give us the missing number.
     * </ol>
     * <b>What could go wrong with the above algorithm?</b> <br>
     * While finding the sum of numbers from `1` to `n`, we can get integer overflow when `n` is large.
     * How can we avoid this? Can XOR help us here?
     */
    public static int findMissingNumber(int[] arr) {
        int n = arr.length + 1;

        // find sum of all numbers from 1 to n
        int s1 = 0;
        for (int i = 1; i <= n; i++) {
            s1 += i;
        }

        // subtract all numbers in input from sum
        for (int num : arr) {
            s1 -= num;
        }

        // s1, is now the missing number
        return s1;
    }

    /**
     * Remember the important property of XOR that it returns `0` if both the bits in comparison are the
     * same. In other words, XOR of a number with itself will always result in `0`. This means that if we
     * XOR all the numbers in the input array with all numbers from the range `1` to `n` then each number
     * in the input is going to get zeroed out except the missing number. Following are the set of steps
     * to find the missing number using XOR:
     * <ol>
     *     <li> XOR all the numbers from `1` to `n`, let's call it `x1`.
     *     <li> XOR all the numbers in the input array, let's call it `x2`.
     *     <li> The missing number can be found by `x1` XOR `x2`.
     * </ol>
     *
     * <li> The time complexity of the above algorithm is O(n) and the space complexity is O(1). The time
     * and space complexities are the same as that of the previous solution but, in this algorithm, we will
     * not have any integer overflow problem.
     */
    public static int findMissingNumberUsingXOR(int[] arr) {
        int n = arr.length + 1;
        int x1 = 1;
        for (int i = 2; i <= n; i++) {
            x1 = x1 ^ i;
        }

        int x2 = arr[0];
        for (int i = 1; i < n - 1; i++) {
            x2 = x2 ^ arr[i];
        }

        return x1 ^ x2;
    }

    public static void main(String[] args) {
        assertThat(findMissingNumber(new int[] {1, 5, 2, 6, 4})).isEqualTo(3);
        assertThat(findMissingNumber(new int[] {1, 2, 3, 4, 5, 6})).isEqualTo(7);
        assertThat(findMissingNumber(new int[] {7, 2, 3, 4, 5, 6})).isEqualTo(1);

        assertThat(findMissingNumberUsingXOR(new int[] {1, 5, 2, 6, 4})).isEqualTo(3);
        assertThat(findMissingNumberUsingXOR(new int[] {1, 2, 3, 4, 5, 6})).isEqualTo(7);
        assertThat(findMissingNumberUsingXOR(new int[] {7, 2, 3, 4, 5, 6})).isEqualTo(1);
    }
}
