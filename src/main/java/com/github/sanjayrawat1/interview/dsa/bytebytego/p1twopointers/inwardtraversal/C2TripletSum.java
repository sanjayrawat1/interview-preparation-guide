package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Given an array of integers, return all triplets [a, b, c] such that a + b + c = 0. the solution must not contain
 * duplicate triplets (e.g., [1, 2, 3] and [2, 3, 1] are considered duplicates). If no such triplets are found,
 * return an empty array.
 * <p>
 * Each triplet can be arranged in any order, and the output can be returned in any order.
 *
 * @author sanjayrawat1
 */
public class C2TripletSum {

    /**
     * <p>
     * A brute force solution involves checking every possible triplet in the array to see if they sum to zero.
     * This can be done using three nested loops, iterating through each combination of three elements.
     * <p>
     * Duplicate triplets can be avoided by sorting each triplet, which ensures that identical triplets with
     * different representations (e.g., [1, 3, 2] and [3, 2, 1]) are ordered consistently (e.g., [1, 2, 3]).
     * Once sorted, we can add these triplets to a hash set. This way, if the same triplet is encountered again,
     * the hash set will only keep one instance.
     * <p>
     * Time complexity: O(n^3)
     * <br>
     * Space Complexity: O(1)
     */
    public List<List<Integer>> tripletSumBruteForce(List<Integer> nums) {
        // Use a hash set to ensure we don't add duplicate triplets.
        Set<List<Integer>> triplets = new HashSet<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                for (int k = j + 1; k < nums.size(); k++) {
                    if (nums.get(i) + nums.get(j) + nums.get(k) == 0) {
                        List<Integer> triplet = new ArrayList<>(List.of(nums.get(i), nums.get(j), nums.get(k)));
                        // Sort the triplet before including it in the hash set.
                        Collections.sort(triplet);
                        triplets.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(triplets);
    }

    /**
     * <p>
     * Let's see if we can find at least one triplet that sums to 0. Notice that if we fix one of the numbers in
     * a triplet, the problem can be reduced to finding the other two. This leads to the following observation:
     * <ul>
     *     For any triplet [a, b, c], if we fix 'a', we can focus on finding a pair [b, c] that sums to '-a'
     *     (a + b + c = 0 -> b + c = -a).
     * </ul>
     * <p>
     * To prevent duplicate triplets, ensure 'a' is not a repeat of the previous element in the sorted array.
     * <p>
     * For a fixed target value ('-a'), pairs that start with the same number 'b' will always be the same.
     * The remedy for this is the same as before: ensure the current 'b' value isn't the same as the previous value.
     * <p>
     * It's important to note that we don’t need to explicitly handle duplicate 'c' values. The adjustments made to
     * avoid duplicate 'a' and 'b' values ensure each pair [a, b] is unique. Since 'c' is determined by the equation
     * c = -(a + b), each unique [a, b] pair will result in a unique 'c' value. Therefore, by just avoiding duplicates
     * in 'a' and 'b', we automatically avoid duplicates in the [a, b, c] triplets.
     * <p>
     * <b>Time complexity</b>: O(n^2). We first sort the array, which takes O(n log(n)) time. Then, for each of the n
     * elements in the array, we call pariSumSortedAllPairs at most once, which runs in O(n) times.
     * Therefore, the overall time complexity is O(n log(n)) + O(n^2) = O(n^2).
     * <br>
     * <b>Space Complexity</b>: O(n) -> space taken up by sorting algorithm. If we include the output array then it would
     * be O(n^2), because the pariSumSortedAllPairs method, in worst case, can add approximately n pairs to the output.
     * Since this method is called approximately n times, the overall space complexity is O(n^2).
     */
    public List<List<Integer>> tripletSum(List<Integer> nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        // Sort the input list.
        Collections.sort(nums);
        for (int i = 0; i < nums.size(); i++) {
            // Optimization: triplets consisting of only positive numbers will never sum to 0.
            if (nums.get(i) > 0) {
                break;
            }

            // To avoid duplicate triplets, skip 'a' if it's the same as the previous number.
            if (i > 0 && nums.get(i).equals(nums.get(i - 1))) {
                continue;
            }

            // Find all pairs that sum to a target of '-a' ('-nums[i]').
            List<List<Integer>> pairs = pairSumSortedAllPairs(nums, i + 1, -nums.get(i));
            for (List<Integer> pair : pairs) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums.get(i));
                triplet.addAll(pair);
                triplets.add(triplet);
            }
        }
        return triplets;
    }

    private List<List<Integer>> pairSumSortedAllPairs(List<Integer> nums, int start, int target) {
        List<List<Integer>> pairs = new ArrayList<>();

        int left = start;
        int right = nums.size() - 1;

        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum == target) {
                List<Integer> pair = new ArrayList<>(List.of(nums.get(left), nums.get(right)));
                pairs.add(pair);
                left++;

                // To avoid duplicate '[b, c]' pairs, skip 'b' if it’s the same as the previous number.
                if (left < right && nums.get(left).equals(nums.get(left - 1))) {
                    left++;
                }
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return pairs;
    }
}
