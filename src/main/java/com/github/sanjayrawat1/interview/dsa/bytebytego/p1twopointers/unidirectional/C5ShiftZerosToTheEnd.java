package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.unidirectional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Given an array of integers, modify the array in place to <b>move all zeros to the end</b> while maintaining
 * the relative order of non-zero elements.
 * <p>
 * <b>Example</b>
 * <br>
 * <b>Input:</b> nums = [0, 1, 0, 3, 2]
 * <br>
 * <b>Output:</b> [1, 3, 2, 0, 0]
 *
 * @author sanjayrawat1
 */
public class C5ShiftZerosToTheEnd {

    /**
     * <p>
     * This problem has three main requirements:
     * <ul>
     *     <li>Move all zeros to the end of the array.</li>
     *     <li>Maintain the relative order of the non-zero elements.</li>
     *     <li>Perform the modification in place.</li>
     * </ul>
     * A naive approach to this problem is to build the output using a separate array (temp). We can add all
     * non-zero elements from the left of nums to this temporary array and leave the rest of it as zeros. Then,
     * we just set the input array equal to temp.
     * <ul>
     *     By identifying and moving the non-zero elements from the left side of the array first, we ensure
     *     their order is preserved when we add them to the output.
     * </ul>
     * <p>
     * <b>Unfortunately, this solution breaks the third requirement of modifying the input array in place.</b>
     * <p>
     * <b>Time Complexity:</b> O(n)
     * <br>
     * <b>Space Complexity:</b> O(n) -> Space taken by temp array.
     */
    public void shiftZerosToTheEndNaive(List<Integer> nums) {
        List<Integer> temp = new ArrayList<>();
        // Add all non-zero elements to the left of 'temp'.
        for (Integer num : nums) {
            if (num != 0) {
                temp.add(num);
            }
        }

        // Fill the rest of 'temp' with zeros.
        while (temp.size() < nums.size()) {
            temp.add(0);
        }

        // Set 'nums' to 'temp'.
        for (int i = 0; i < nums.size(); i++) {
            nums.set(i, temp.get(i));
        }
    }

    /**
     * <p>
     * From the naive approach a valuable insight to be gained. In particular, notice that this solution
     * focuses on the non-zero elements instead of zeros. This means if we change our goal to move all
     * non-zero elements to the left of the array, the zeros will consequently end up on the right.
     * Therefore, we only need to focus on non-zero elements.
     * <p>
     * We can use two pointers for this:
     * <ul>
     *     <li>
     *         A left pointer to iterate over the left of the array where the non-zero elements should be placed.
     *     </li>
     *     <li>
     *         A right pointer to find non-zero elements.
     *     </li>
     * </ul>
     * <p>
     * Start by placing the left and right pointers at the start of the array. Before we move non-zero elements
     * to the left, we need the right pointer to be pointing at a non-zero element.
     * <p>
     * We only use left pointer to keep track of where non-zero elements should be placed. So, until we find a
     * non-zero element, we shouldn't move this pointer.
     * <p>
     * When the value at the right pointer is non-zero:
     * <ol>
     *     <li>
     *         Swap the element at left and right.
     *     </li>
     *     <li>
     *         Increment the pointer:
     *         <ul>
     *             <li>
     *                 After the swap is complete, we should increment the left pointer to position it where
     *                 the next non-zero element should go.
     *             </li>
     *             <li>
     *                 Also increment the right pointer in search of the next non-zero element.
     *             </li>
     *         </ul>
     *     </li>
     * </ol>
     * <p>
     * <b>Time Complexity:</b> O(n)
     * <br>
     * <b>Space Complexity:</b> O(1)
     */
    public void shiftZerosToTheEnd(List<Integer> nums) {
        // The 'left' pointer is used to position non-zero elements.
        int left = 0;

        // Iterate through the array using a 'right' pointer to locate non-zero elements.
        int right = 0;
        while (right < nums.size()) {
            if (nums.get(right) != 0) {
                // Optimization to prevent unnecessary swaps. If right and left are already at the same position
                // and nums[right] is non-zero, it means the non-zero element is already in the "correct"
                // position relative to the left pointer (it's part of the non-zero prefix). Swapping it with
                // itself doesn't accomplish anything useful and adds a tiny bit of overhead.
                if (right != left) {
                    // Swap nums[left] and nums[right].
                    int temp = nums.get(left);
                    nums.set(left, nums.get(right));
                    nums.set(right, temp);
                }
                // Increment 'left' since it now points to a position already occupied by a non-zero element.
                left++;
            }
            // Always move the right pointer forward, regardless of whether it points to a zero or a non-zero.
            right++;
        }
    }
}
