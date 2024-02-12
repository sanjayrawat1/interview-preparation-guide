package com.github.sanjayrawat1.interview.grokking.codingpattern.p10subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/subsets-ii/
 * <p>
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 *
 * @author Sanjay Singh Rawat
 */
public class P2SubsetsWithDuplicate {

    /**
     * Since the given set can have duplicate numbers, if we follow the same approach discussed in subsets,
     * we will end up with duplicate subsets. To handle this, we will do two extra things:
     * <ol>
     *     <li> Sort all numbers of the given set. This will ensure that duplicate numbers are next to each other.
     *     <li> Whenever we are about to process a duplicate, instead of adding the current number (which is a
     *          duplicate) to all the existing subsets, only add it to the subsets which were created in the
     *          previous step.
     * </ol>
     * Let’s take an example mentioned below to go through each step of our algorithm:
     * <br> Given set: [1, 5, 3, 3]
     * <br> Sorted set: [1, 3, 3, 5]
     * <ol>
     *     <li> Start with an empty set: [[]]
     *     <li> Add the first number 1 to all the existing subsets to create new subsets: [[], [1]];
     *     <li> Add the second number 3 to all the existing subsets: [[], [1], [3], [1,3]].
     *     <li> The next number 3 is a duplicate. If we add it to all existing subsets we will get:
     *          [[], [1], [3], [1,3], [3], [1,3], [3,3], [1,3,3]]
     *          <br> We got two duplicate subsets: [3], [1,3]
     *          <br> Whereas we only needed the new subsets: [3,3], [1,3,3]
     *          <br> To handle this instead of adding 3 to all the existing subsets, we only add it to the new subsets
     *          which were created in the previous 3rd step:
     *          [[], [1], [3], [1,3], [3,3], [1,3,3]]
     *     <li> Finally, add the forth number 5 to all the existing subsets: [[], [1], [3], [1,3], [3,3], [1,3,3],
     *          [5], [1,5], [3,5], [1,3,5], [3,3,5], [1,3,3,5]]
     * </ol>
     */
    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int start = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            start = 0;
            // if current and the previous elements are the same, create new subsets only from the subsets added in the previous step
            if (i > 0 && nums[i] == nums[i - 1]) {
                start = end + 1;
            }
            end = subsets.size() - 1;

            for (int j = start; j < end + 1; j++) {
                List<Integer> newSubset = new ArrayList<>(subsets.get(j));
                newSubset.add(currentNumber);
                subsets.add(newSubset);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        assertThat(subsets(new int[] {1, 3, 3}))
                .containsExactly(List.of(), List.of(1), List.of(3), List.of(1, 3), List.of(3, 3), List.of(1, 3, 3));

        assertThat(subsets(new int[] {1, 5, 3, 3})).containsExactly(List.of(), List.of(1), List.of(3), List.of(1, 3), List.of(3, 3), List.of(1, 3, 3),
                List.of(5), List.of(1, 5), List.of(3, 5), List.of(1, 3, 5), List.of(3, 3, 5), List.of(1, 3, 3, 5));
    }
}
