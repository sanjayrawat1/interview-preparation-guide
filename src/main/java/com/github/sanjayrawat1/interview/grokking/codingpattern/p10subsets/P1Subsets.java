package com.github.sanjayrawat1.interview.grokking.codingpattern.p10subsets;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/subsets/
 * <p>
 * Given a set with distinct elements, find all of its distinct subsets.
 *
 * @author Sanjay Singh Rawat
 */
public class P1Subsets {

    /**
     * To generate all subsets of the given set, we can use the Breath First Approach. We can start with an
     * empty set, iterate through all numbers one-by-one, and add them to existing sets to create new subsets.
     * <p>
     * Given set: [1, 5, 3]
     * <ol>
     *     <li> Start with an empty set: [[]]
     *     <li> Add the first number 1 to all the existing subsets to create new subsets: [[],[1]];
     *     <li> Add the second number 5 to all the existing subsets: [[], [1], [5], [1,5]];
     *     <li> Add the third number 3 to all the existing subsets: [[], [1], [5], [1,5], [3], [1,3], [5,3], [1,5,3]].
     * </ol>
     *
     * <li> Since, in each step, the number of subsets doubles as we add each element to all the existing
     *      subsets, therefore, we will have a total of O(2ᴺ) subsets, where N is the total number of
     *      elements in the input set. And since we construct a new subset from an existing set, therefore,
     *      the time complexity of the above algorithm will be O(N*2ᴺ).
     * <li> All the additional space used by our algorithm is for the output list. Since we will have a
     *      total of O(2ᴺ) subsets, and each subset can take up to O(N) space, therefore, the space complexity
     *      of our algorithm will be O(N*2ᴺ).
     */
    public static List<List<Integer>> subsets(int[] nums) {
        // start by adding the empty subset
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            // we will take all existing subsets and insert the current number in them to create new subsets
            int subsetLength = subsets.size();
            // create a new subset from the existing subset and insert the current element to it
            for (int j = 0; j < subsetLength; j++) {
                List<Integer> newSubset = new ArrayList<>(subsets.get(j));
                newSubset.add(currentNumber);
                subsets.add(newSubset);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        assertThat(subsets(new int[] {1, 2, 3}))
                .containsExactly(List.of(), List.of(1), List.of(2), List.of(1, 2), List.of(3), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3));

        assertThat(subsets(new int[] {1, 3})).containsExactly(List.of(), List.of(1), List.of(3), List.of(1, 3));
    }
}
