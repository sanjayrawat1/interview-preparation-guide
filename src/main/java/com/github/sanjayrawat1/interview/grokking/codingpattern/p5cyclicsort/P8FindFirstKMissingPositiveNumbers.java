package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P8FindFirstKMissingPositiveNumbers {

    public static void main(String[] args) {
        assertThat(firstKMissing(new int[] {3, -1, 4, 5, 5}, 3)).containsSequence(1, 2, 6);
        assertThat(firstKMissing(new int[] {2, 3, 4}, 3)).containsSequence(1, 5, 6);
        assertThat(firstKMissing(new int[] {-2, -3, 4}, 2)).containsSequence(1, 2);
        assertThat(firstKMissing(new int[] {2, 1, 3, 6, 5}, 2)).containsSequence(4, 7);
    }

    /**
     * <li> The time complexity of the above algorithm is O(n + k), as the last two for loops will run for O(n) and O(k) times respectively.
     * <li> The algorithm needs O(k) space to store the extraNumbers.
     */
    private static List<Integer> firstKMissing(int[] nums, int k) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            int j = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        Set<Integer> extraNumbers = new HashSet<>();
        for (i = 0; i < n; i++) {
            if (missingNumbers.size() < k) {
                if (nums[i] != i + 1) {
                    missingNumbers.add(i + 1);
                    extraNumbers.add(nums[i]);
                }
            }
        }

        // add the remaining missing numbers
        int j = 1;
        while (missingNumbers.size() < k) {
            int currentNumber = j + n;
            // ignore if the array contains the current number
            if (!extraNumbers.contains(currentNumber)) {
                missingNumbers.add(currentNumber);
            }
            j++;
        }

        return missingNumbers;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
