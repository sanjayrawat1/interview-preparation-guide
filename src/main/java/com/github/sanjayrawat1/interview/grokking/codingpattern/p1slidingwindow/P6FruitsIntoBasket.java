package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/fruit-into-baskets/description/
 * <br>
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * <br>
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * <br>
 * You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
 * while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * <li>
 * Example 1:
 * <br>
 * Input: fruits = [1,2,1]
 * <br>
 * Output: 3
 * <br>
 * Explanation: We can pick from all 3 trees.
 * <li>
 * Example 2:
 * <br>
 * Input: fruits = [0,1,2,2]
 * <br>
 * Output: 3
 * <br>
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * <li>
 * Example 3:
 * <br>
 * Input: fruits = [1,2,3,2,2]
 * <br>
 * Output: 4
 * <br>
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 *
 * @author Sanjay Singh Rawat
 */
public class P6FruitsIntoBasket {

    public static void main(String[] args) {
        assertThat(slidingWindow(new int[] {1, 2, 1})).isEqualTo(3);
        assertThat(slidingWindow(new int[] {0, 1, 2, 2})).isEqualTo(3);
        assertThat(slidingWindow(new int[] {1, 2, 3, 2, 2})).isEqualTo(4);
    }

    /**
     * The algorithm's time complexity will be O(N), where N is the number of integers in the input array.
     * The outer for loop runs for all characters, and the inner while loop processes each character only once;
     * Therefore, the time complexity of the algorithm will be O(N+N), which is asymptotically equivalent to O(N).
     * <p>
     * The algorithm runs in constant space O(1) as there can be a maximum of three types of fruits stored in the frequency map.
     *
     * @param fruits
     * @return maximum fruits
     */
    private static int slidingWindow(int[] fruits) {
        int baskets = 2;
        int left = 0;
        int maxFruits = 0;
        Map<Integer, Integer> fruitsFrequency = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {
            int rightFruit = fruits[right];
            fruitsFrequency.put(rightFruit, fruitsFrequency.getOrDefault(rightFruit, 0) + 1);

            while (fruitsFrequency.size() > baskets) {
                int leftFruit = fruits[left];
                fruitsFrequency.put(leftFruit, fruitsFrequency.get(leftFruit) - 1);
                if (fruitsFrequency.get(leftFruit) == 0) {
                    fruitsFrequency.remove(leftFruit);
                }
                left++;
            }
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }
}
