package com.github.sanjayrawat1.interview.grokking.codingpattern.p12bitwisexor;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/single-number-iii/
 * <p>
 * In a non-empty array of numbers, every number appears exactly twice except two numbers that appear only
 * once. Find the two numbers that appear only once.
 *
 * @author Sanjay Singh Rawat
 */
public class P3TwoSingleNumber {

    /**
     * This problem is quite similar to {@link P2SingleNumber}, the only difference is that, in this problem,
     * we have two single numbers instead of one. Can we still use XOR to solve this problem?
     * <p>
     * Let's assume `num1` and `num2` are the two single numbers. If we do XOR of all elements of the given
     * array, we will be left with XOR of `num1` and `num2` as all other numbers will cancel each other
     * because all of them appeared twice. Let's call this XOR `n1xn2`. Now that we have XOR of `num1` and
     * `num2`, how can we find these two single numbers?
     * <p>
     * As we know that `num1` and `num2` are two different numbers, therefore, they should have at least one
     * bit different between them. If a bit in `n1xn2` is `1`, this means that `num1` and `num2` have different
     * bits in that place, as we know that we can get `1` only when we do XOR of two different bits, i.e.,
     * `1 XOR 0 = 0 XOR 1 = 1`
     * <p>
     * We can take any bit which is `1` in `n1xn2` and partition all numbers in the given array into two groups
     * based on that bit. One group will have all those numbers with that bit set to `0` and the other with
     * the bit set to `1`. This will ensure that `num1` will be in one group and `num2` will be in the other.
     * We can take XOR of all numbers in each group separately to get `num1` and `num2`, as all other numbers
     * in each group will cancel each other. Here are the steps of our algorithm:
     * <ol>
     *     <li> Taking XOR of all numbers in the given array will give us XOR of `num1` and `num2`, calling
     *          this XOR as `n1xn2`.
     *     <li> Find any bit which is set in `n1xn2`. We can take the rightmost bit which is `1`. Let's call
     *          this `rightmostSetBit`.
     *     <li> Iterate through all numbers of the input array to partition them into two groups based on
     *          `rightmostSetBit`. Take XOR of all numbers in both the groups separately. Both these XORs
     *          are our required numbers.
     * </ol>
     */
    public static int[] findSingleNumbers(int[] arr) {
        // get the XOR of all the numbers
        int n1xn2 = 0;
        for (int num : arr) {
            n1xn2 = n1xn2 ^ num;
        }

        // get the right most bit which is 1
        int rightmostSetBit = 1;

        // & is bitwise AND
        // This operator expects two numbers and returns a number.
        // In case they are not numbers, they are cast to numbers.
        while ((n1xn2 & rightmostSetBit) == 0) {
            // The left shift operator ( << ) shifts the first operand the specified number of bits to the left.
            // Excess bits shifted off to the left are discarded.
            // Zero bits are shifted in from the right.
            rightmostSetBit = rightmostSetBit << 1;
        }

        int num1 = 0;
        int num2 = 0;

        for (int num : arr) {
            if ((num & rightmostSetBit) != 0) {
                // the bit is set
                num1 = num1 ^ num;
            } else {
                // the bit is not set
                num2 = num2 ^ num;
            }
        }

        return new int[]{num1, num2};
    }

    public static void main(String[] args) {
        assertThat(findSingleNumbers(new int[] {1, 4, 2, 1, 3, 5, 6, 2, 3, 5})).containsExactlyInAnyOrder(4, 6);
        assertThat(findSingleNumbers(new int[] {2, 1, 3, 2})).containsExactlyInAnyOrder(1, 3);
    }
}
