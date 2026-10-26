package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

/**
 * <p>
 * A palindrome is a sequence of characters that reads the same forward and backward.
 * <p>
 * Given a string, determine if it's a palindrome after removing all non-alphanumeric characters. A character
 * is alphanumeric if it's either a letter or a number.
 * <p>
 * Example 1:
 * <br>
 * Input: s = "a dog! a panic in a pagoda."
 * <br>
 * Output: true
 * <p>
 * Example 2:
 * <br>
 * Input: "abc123"
 * <br>
 * Output: false
 * <p>
 * Constraint: The string may include a combination of lowercase English letters, numbers, spaces, and punctuations.
 *
 * @author sanjayrawat1
 */
public class C3IsPalindromeValid {

    /**
     * <p>
     * A string is a palindrome if it remains identical when read from left to right or right to left. In other
     * words, if we reverse the string, it should still read the same, disregarding space and punctuation.
     * <p>
     * An important observation is that if a string is a palindrome, the first character would be the same as
     * the last, the second character would be the same as the second-to-last, etc.
     * <p>
     * A palindrome of odd length is different because it has a middle character. In this case, the middle
     * character can be ignored since it has no "mirror" character elsewhere in the string.
     * <p>
     * Palindrome provides an ideal scenario for using two pointers. By initially setting the pointers at the
     * beginning and end of the string, we can compare the characters at these positions.
     * <ul>
     *     <li>
     *         Since non-alphanumeric characters don't affect whether a string is a palindrome, we should skip
     *         them:
     *         <ul>
     *             <li>
     *                 Increment left until the character it points to is alphanumeric.
     *             </li>
     *             <li>
     *                 Decrement right until the character it points to is alphanumeric.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         If the alphanumeric characters at left and right are the same, move both pointers inward to
     *         process the next pair of characters.
     *     </li>
     *     <li>
     *         If not, the string is not a palindrome: return false.
     *     </li>
     * </ul>
     * If  we successfully compare all character pair without returning false, the string is a palindrome and
     * we should return true.
     * <p>
     * We need to ensure we exit the loop when left equals right, or when left passes right. In other word,
     * the algorithm continues while left is less than right.
     * <p>
     * <b>Time complexity</b>: O(n)
     * <br>
     * <b>Space Complexity</b>: O(1)
     */
    public boolean isPalindromeValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Skip non-alphanumeric characters from the left.
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from the right.
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // If the characters at the left and right pointers don’t match, the string is not a palindrome.
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
