package com.github.sanjayrawat1.interview.grokking.codingpattern.p10subsets;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/permutations/
 * <p>
 * Given a set of distinct numbers, find all of its permutations.
 * <br>
 * Permutation is defined as the re-arranging of the elements of the set. For example, {1, 2, 3} has the
 * following six permutations:
 * <ol>
 *     <li> {1, 2, 3}
 *     <li> {1, 3, 2}
*      <li> {2, 1, 3}
*      <li> {2, 3, 1}
*      <li> {3, 1, 2}
*      <li> {3, 2, 1}
 * </ol>
 * If a set has 'n' distinct elements it will have 'n!' permutations.
 *
 * @author Sanjay Singh Rawat
 */
public class P3Permutations {

    /**
     * Following a BFS approach, we will consider one number at a time:
     * <ol>
     *     <li> If the given set is empty then we have only an empty permutation set: []
     *     <li> Let's add the first element 1, the permutation will be: [1]
     *     <li> Let's add the second element 2, the permutation will be: [2, 1], [1, 2]
     *     <li> Let's add the third element 3, the permutation will be: [3, 2, 1], [2, 3, 1], [2, 1, 3],
     *          [3, 1, 2], [1, 3, 2], [1, 2, 3]
     * </ol>
     * Let's analyze the permutations in the 3rd and 4th steps. How can we generate permutations in the 4th
     * step from the permutations of the 3rd steps?
     * <br>
     * If we look closely, we will realize that when we add a new number '3', we take each permutation of the
     * previous step and insert the new number in every position to generate the new permutations. For example,
     * inserting 3 in different positions of [2, 1] will give us the following permutations:
     * <ol>
     *     <li> Inserting 3 before 2: [3, 2, 1]
     *     <li> Inserting 3 between 2 and 1: [2, 3, 1]
     *     <li> Inserting 3 after 1: [2, 1, 3]
     * </ol>
     * <li> We know that there are a total of N! permutations of a set with N numbers. In the algorithm above,
     *      we are iterating through all of these permutations with the help of the two ‘for’ loops. In each
     *      iteration, we go through all the current permutations to insert a new number in them. To insert
     *      a number into a permutation of size N will take O(N), which makes the overall time complexity of
     *      our algorithm O(N*N!).
     * <li> All the additional space used by our algorithm is for the result list and the queue to store the
     *      intermediate permutations. If you see closely, at any time, we don’t have more than N! permutations
     *      between the result list and the queue. Therefore, the overall space complexity to store N!
     *      permutations each containing N elements will be O(N*N!).
     */
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>());

        for (int currentNumber : nums) {
            // we will take all existing permutations and add the current number to create a new permutation
            int n = permutations.size();
            for (int p = 0; p < n; p++) {
                List<Integer> oldPermutation = permutations.remove(0);
                // create a new permutation by adding the current number at every position
                for (int j = 0; j < oldPermutation.size() + 1; j++) {
                    // clone the permutation
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    // insert the current number at index j
                    newPermutation.add(j, currentNumber);
                    if (newPermutation.size() == nums.length) {
                        result.add(newPermutation);
                    } else {
                        permutations.add(newPermutation);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]
        assertThat(findPermutations(new int[] {1, 2, 3}))
                .containsExactlyInAnyOrder(List.of(3, 2, 1), List.of(2, 3, 1), List.of(2, 1, 3), List.of(3, 1, 2), List.of(1, 3, 2), List.of(1, 2, 3));

        assertThat(findPermutations(new int[] {0, 1})).containsExactlyInAnyOrder(List.of(0, 1), List.of(1, 0));

        assertThat(findPermutations(new int[] {1})).containsExactlyInAnyOrder(List.of(1));
    }

}
