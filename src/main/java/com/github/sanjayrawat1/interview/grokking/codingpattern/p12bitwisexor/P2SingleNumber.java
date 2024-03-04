package com.github.sanjayrawat1.interview.grokking.codingpattern.p12bitwisexor;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/single-number/
 * <p>
 * In a non-empty array of integers, every number appears twice except for one, find that single number.
 *
 * @author Sanjay Singh Rawat
 */
public class P2SingleNumber {

    /**
     * One straight forward solution can be to use a HashMap kind of data structure and iterate through
     * the input:
     * <ol>
     *     <li> If number is already present in HashMap, remove it.
     *     <li> If number is not present in HashMap, add it.
     *     <li> In the end, only number left in the HashMap is our required single number.
     * </ol>
     *
     * Or we can also count the frequencies of each number and in the end we will be having one number
     * with count `1`.
     */
    public static int findSingleNumberUsingMap(int[] arr) {
        Map<Integer, Integer> numbersFrequency = new HashMap<>();
        for (int num : arr) {
            numbersFrequency.put(num, numbersFrequency.getOrDefault(num, 0) + 1);
        }

        int singleNumber = 0;
        // can be collapsed with stream findFirst()
        for (Integer key : numbersFrequency.keySet()) {
            if (numbersFrequency.get(key) == 1) {
                singleNumber = key;
                break;
            }
        }
        return singleNumber;
    }

    /**
     * Recall the following two properties of XOR:
     * <ol>
     *     <li> It returns zero if we take XOR of two same numbers.
     *     <li> It returns the same number if we XOR with zero. So we can XOR all the numbers in the input;
     *          duplicate numbers will zero out each other and we will be left with the single number.
     * </ol>
     */
    public static int findSingleNumberUsingXOR(int[] arr) {
        int singleNumber = 0;

        for (int i = 0; i < arr.length; i++) {
            singleNumber = singleNumber ^ arr[i];
        }

        return singleNumber;
    }

    public static void main(String[] args) {
        assertThat(findSingleNumberUsingMap(new int[] {1, 1, 2, 3, 2})).isEqualTo(3);
        assertThat(findSingleNumberUsingMap(new int[] {1, 4, 2, 1, 3, 2, 3})).isEqualTo(4);
        assertThat(findSingleNumberUsingMap(new int[] {7, 9, 7})).isEqualTo(9);
        assertThat(findSingleNumberUsingMap(new int[] {7})).isEqualTo(7);

        assertThat(findSingleNumberUsingXOR(new int[] {1, 1, 2, 3, 2})).isEqualTo(3);
        assertThat(findSingleNumberUsingXOR(new int[] {1, 4, 2, 1, 3, 2, 3})).isEqualTo(4);
        assertThat(findSingleNumberUsingXOR(new int[] {7, 9, 7})).isEqualTo(9);
        assertThat(findSingleNumberUsingXOR(new int[] {7})).isEqualTo(7);
    }
}
