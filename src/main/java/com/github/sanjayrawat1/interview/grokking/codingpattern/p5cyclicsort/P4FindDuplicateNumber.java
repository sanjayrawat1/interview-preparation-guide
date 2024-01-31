package com.github.sanjayrawat1.interview.grokking.codingpattern.p5cyclicsort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * <br>
 * We are given an unsorted array containing `n + 1` numbers taken from the range `1` to `n`. The array
 * has only one duplicate, but it can be repeated multiple times. Find that duplicate number without using
 * any extra space. You are, however, allowed to modify the input array.
 *
 * <li>Example 1:
 * <br>
 * Input: nums = [1,3,4,2,2]
 * <br>
 * Output: 2
 *
 * <li> Example 2:
 * <br>
 * Input: nums = [3,1,3,4,2]
 * <br>
 * Output: 3
 *
 * @author Sanjay Singh Rawat
 */
public class P4FindDuplicateNumber {

    public static void main(String[] args) {
        assertThat(findDuplicate(new int[] {1, 3, 4, 2, 2})).isEqualTo(2);
        assertThat(findDuplicate(new int[] {3, 1, 3, 4, 2})).isEqualTo(3);
        assertThat(findDuplicate(new int[] {1, 4, 4, 3, 2})).isEqualTo(4);
        assertThat(findDuplicate(new int[] {2, 1, 3, 3, 5, 4})).isEqualTo(3);
        assertThat(findDuplicate(new int[] {2, 4, 1, 4, 4})).isEqualTo(4);

        assertThat(findDuplicateWithoutModifyingInput(new int[] {1, 3, 4, 2, 2})).isEqualTo(2);
        assertThat(findDuplicateWithoutModifyingInput(new int[] {3, 1, 3, 4, 2})).isEqualTo(3);
        assertThat(findDuplicateWithoutModifyingInput(new int[] {1, 4, 4, 3, 2})).isEqualTo(4);
        assertThat(findDuplicateWithoutModifyingInput(new int[] {2, 1, 3, 3, 5, 4})).isEqualTo(3);
        assertThat(findDuplicateWithoutModifyingInput(new int[] {2, 4, 1, 4, 4})).isEqualTo(4);
    }

    /**
     * We will try to place each number on its correct index. Since there is only one duplicate, if while
     * swapping the number with its index both the numbers being swapped are same, we have found the duplicate!
     */
    private static int findDuplicate(int[] nums) {
        int i = 0;
        while (i < nums.length) {
//            if (nums[i] != i + 1) {
                int j = nums[i] - 1;
                if (nums[i] != nums[j]) {
                    swap(nums, i, j);
//                } else {
//                    return nums[i];
//                }
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * While doing the Cyclic Sort, we realized that the array will have a cycle due to the duplicate number
     * and that the start of the cycle will always point to the duplicate number. This means that we can use
     * the fast & the slow pointer method to find the duplicate number or the start of the cycle
     */
    private static int findDuplicateWithoutModifyingInput(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);


        slow = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
