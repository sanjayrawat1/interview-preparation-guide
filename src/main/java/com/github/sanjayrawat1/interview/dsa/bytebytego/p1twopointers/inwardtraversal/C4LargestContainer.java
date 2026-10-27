package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import java.util.List;

/**
 * <p>
 * Given an array of numbers, each representing the height of a vertical line on a graph. A container can be
 * formed with any pair of these lines, along with the x-axis of the graph. Return the amount of water which
 * the <b>largest container</b> can hold.
 *
 * @author sanjayrawat1
 */
public class C4LargestContainer {

    /**
     * <p>
     * If we have two vertical lines, heights[i] and heights[j], the amount of water that can be contained
     * between these two lines is min(heights[i], heights[j]) * (j - i), where j - i represents the width of the
     * container. We take the minimum height because filling water above this height would result in overflow.
     * <p>
     * In other words, the area of the container depends on two things:
     * <ul>
     *     <li>
     *         The width of the rectangle.
     *     </li>
     *     <li>
     *         The height of the rectangle, as directed by the shorter of the two lines.
     *     </li>
     * </ul>
     * <p>
     * <b>Time Complexity:</b> O(n^2)
     * <br>
     * <b>Space Complexity:</b> O(1)
     */
    public int largestContainerBruteForce(List<Integer> heights) {
        int n = heights.size();
        int maxWater = 0;
        // Find the maximum amount of water stored between all pairs of lines.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentWater = Math.min(heights.get(i), heights.get(j)) * (j - i);
                maxWater = Math.max(maxWater, currentWater);
            }
        }
        return maxWater;
    }

    /**
     * <p>
     * We would like both the height and width to be as large as possible to have the largest container.
     * It's not immediately obvious how to find the container with the largest height, as the heights of the
     * lines in the array don't follow a clear pattern. However, we do know the container with the maximum
     * width: the one starting at index 0 and ending at index n - 1.
     * <p>
     * So, we could start by maximizing the width by setting a pointer at each end of the array. Then, we can
     * gradually reduce the width by moving these two pointers inward, hoping to find a container with a larger
     * height that potentially yields a larger area.
     * <ul>
     *     Moving a pointer inward means shifting either the left pointer to the right, or the right pointer to
     *     the left, effectively narrowing the gap between them.
     * </ul>
     * <p>
     * Moving either pointer inwards yields a container with a shorter width. This leaves height as the determining
     * factor.
     * <ul>
     *     <li>
     *         If the left line is shorter than the right line, which means that the left line limits the water's
     *         height. Therefore, to find a larger container, let's move the left pointer inward.
     *     </li>
     *     <li>
     *         If the right line is shorter, limiting the water's height. To find a larger container, move the
     *         right pointer inward.
     *     </li>
     *     <li>
     *         If the height of the left and right lines are equal, which pointer should we move inward? Well,
     *         regardless of which one, the next container is guaranteed to store less water than the current one.
     *         Moving either pointer inward yields a container of shorter width, leaving height as the determining
     *         factor. However, regardless of which pointer we move inward, the other pointer remains at the same
     *         line. So, even if a pointer is moved to a taller line, the other pointer will restrict the height
     *         of the water, as we take the minimum of the two lines. Therefore, since we can't increase height
     *         by moving just one pointer, we can just move both pointers inward.
     *     </li>
     * </ul>
     * <p>
     * <b>Time complexity:</b> O(n)
     * <br>
     * <b>Time complexity:</b> O(1)
     */
    public int largestContainer(List<Integer> heights) {
        int left = 0;
        int right = heights.size() - 1;
        int maxWater = 0;

        while (left < right) {
            // Calculate the water contained between the current pair of lines.
            int currentWater = Math.min(heights.get(left), heights.get(right)) * (right - left);
            maxWater = Math.max(maxWater, currentWater);

            // Move the pointers inward, always moving the pointer at the shorter line. If
            // both lines have the same height, move both pointers inward.
            if (heights.get(left) < heights.get(right)) {
                left++;
            } else if (heights.get(left) > heights.get(right)) {
                right--;
            } else {
                left++;
                right--;
            }
        }

        return maxWater;
    }
}
