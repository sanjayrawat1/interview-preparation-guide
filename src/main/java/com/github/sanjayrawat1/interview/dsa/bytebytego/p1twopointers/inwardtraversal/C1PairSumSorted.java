package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import java.util.List;

/**
 * <p>
 * Given an array of integers sorted in ascending order and a target value, return the <b>indexes of any
 * pair of numbers</b> in the array that <b>sum to the target</b>. The order of the indexes in the result
 * doesn't matter. If no pair is found, return an empty array.
 *
 * @author sanjayrawat1
 */
public class C1PairSumSorted {

    /**
     * <p>
     * Time complexity: O(n^2)
     * <br>
     * Space Complexity: O(1)
     */
    public List<Integer> pairSumBruteForce(List<Integer> nums, int target) {
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) == target) {
                    return List.of(i, j);
                }
            }
        }
        return List.of();
    }

    /**
     * <p>
     * A two pointer approach is worth considering here because a sorted array allows us to move pointers
     * in a logical way.
     * <p>
     * For any pair of values at left and right:
     * <ul>
     *     <li>
     *         If their sum is less than the target, increment left, aiming to increase the sum towards the target value
     *     </li>
     *     <li>
     *         If their sum is greater than the target, decrement right, aiming to decrease the sum towards the target value
     *     </li>
     *     <li>
     *         If their sum is equal to the target, return [left, right]
     *     </li>
     * </ul>
     * We can stop moving the left and right pointers when they meet, as this indicates no pair summing to the target was found.
     * <p>
     * Time complexity: O(n)
     * <br>
     * Space Complexity: O(1)
     */
    public List<Integer> pairSumTwoPointer(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            // If the sum is smaller, increment the left pointer, aiming to increase the
            // sum towards the target value.
            if (sum < target) {
                left++;
            }
            // If the sum is larger, decrement the right pointer, aiming to decrease the
            // sum towards the target value.
            else if (sum > target) {
                right--;
            }
            // If the target pair is found, return its indexes.
            else {
                return List.of(left, right);
            }
        }
        return List.of();
    }
}
