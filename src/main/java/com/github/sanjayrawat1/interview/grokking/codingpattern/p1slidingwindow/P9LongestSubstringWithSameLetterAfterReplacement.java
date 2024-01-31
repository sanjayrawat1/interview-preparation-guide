package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 * <p>
 * You are given a string s and an integer k. You can choose any character of the string and change
 * it to any other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the
 * above operations.
 * <li>
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * <p>
 * Output: 4
 * <p>
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * <li>
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * <p>
 * Output: 4
 * <p>
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 *
 * @author Sanjay Singh Rawat
 */
public class P9LongestSubstringWithSameLetterAfterReplacement {

    public static void main(String[] args) {
        assertThat(slidingWindow("ABAB", 2)).isEqualTo(4);
        assertThat(slidingWindow("AABABBA", 1)).isEqualTo(4);
        assertThat(slidingWindow("AABCCBB", 2)).isEqualTo(5);
        assertThat(slidingWindow("ABBCB", 1)).isEqualTo(4);
        assertThat(slidingWindow("ABCCDE", 1)).isEqualTo(3);
    }

    /**
     * <li> We will iterate through the string to add one letter at a time in the window.
     * <li> We will also keep track of the count of the maximum repeating letter in any window (let's call it maxRepeatedLetterCount).
     * <li> So, at any time, we know that we do have a window with one letter repeating `maxRepeatedLetterCount` times. This means we should try to replace
     * the remaining letters.
     *    <ul>
     *    <li> if the remaining letters are less than or equal to `k`, we can replace them all.
     *    <li> if we have more than `k` remaining letters, we should shrink the window as we cannot replace more than `k` letters.
     *    </ul>
     * <p>
     * While shrinking the window, we don’t need to update maxRepeatedLetterCount. Since we are only interested in the longest valid substring,
     * our sliding windows do not have to shrink, even if a window may cover an invalid substring. Either we expand the window by appending
     * a character to the right, or we shift the entire window to the right by one. We only expand the window when the frequency of the
     * newly added character exceeds the historical maximum frequency (from a previous window that included a valid substring).
     * In other words, we do not need to know the exact maximum count of the current window. The only thing we need to know is whether the
     * maximum count exceeds the historical maximum count, and that can only happen because of the newly added char.
     *
     */
    private static int slidingWindow(String str, int k) {
        int maxLength = 0;
        int left = 0;
        int maxRepeatedCharacterCount = 0;
        Map<Character, Integer> charsFrequency = new HashMap<>();

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            charsFrequency.put(rightChar, charsFrequency.getOrDefault(rightChar, 0) + 1);
            maxRepeatedCharacterCount = Math.max(maxRepeatedCharacterCount, charsFrequency.get(rightChar));

            int charactersToReplace = right - left + 1 - maxRepeatedCharacterCount;
            if (charactersToReplace > k) {
                char leftChar = str.charAt(left);
                charsFrequency.put(leftChar, charsFrequency.get(leftChar) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
