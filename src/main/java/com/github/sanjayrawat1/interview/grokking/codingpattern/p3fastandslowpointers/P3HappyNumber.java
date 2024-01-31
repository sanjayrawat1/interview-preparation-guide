package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/happy-number/
 * <br>
 * Write an algorithm to determine if a number n is happy.
 * <br>
 * A happy number is a number defined by the following process:
 * <br>
 * <li> Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * <li> Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * <li> Those numbers for which this process ends in 1 are happy.
 * <br>
 * Return true if n is a happy number, and false if not.
 * <li>
 * Example 1:
 * <br>
 * Input: n = 19
 * <br>
 * Output: true
 * <br>
 * Explanation:
 * <br>
 * 12 + 92 = 82
 * <br>
 * 82 + 22 = 68
 * <br>
 * 62 + 82 = 100
 * <br>
 * 12 + 02 + 02 = 1
 * <li>
 * Example 2:
 * <br>
 * Input: n = 2
 * <br>
 * Output: false
 *
 * @author Sanjay Singh Rawat
 */
public class P3HappyNumber {

    public static void main(String[] args) {
        assertThat(isHappy(19)).isTrue();
        assertThat(isHappy(2)).isFalse();
        assertThat(isHappy(12)).isFalse();
        assertThat(isHappy(23)).isTrue();
    }

    /**
     * Any number will be called a happy number if, after repeatedly replacing it with a number equal to the
     * sum of the square of all of its digits, leads us to the number '1'.
     * All other (not-happy) numbers will never reach '1'. Instead, they will be stuck in a cycle of numbers
     * which does not include '1'.
     * The process defined to find out if a number is a happy number or not, always ends in a cycle.
     * If the number is a happy number, the process will be stuck in a cycle on number '1', and if the number
     * is not a happy number then the process will be stuck in a cycle with a set of numbers.
     */
    private static boolean isHappy(int num) {
        int slow = num;
        int fast = num;

        while (true) {
            slow = findSquareSum(slow);
            fast = findSquareSum(findSquareSum(fast));

            if (slow == fast) {
                break;
            }
        }
        return slow == 1;
    }

    private static int findSquareSum(int num) {
        int squaredSum = 0;
        while (num > 0) {
            int remainder = num % 10;
            squaredSum += remainder * remainder;
            num = num / 2;
        }
        return squaredSum;
    }
}
