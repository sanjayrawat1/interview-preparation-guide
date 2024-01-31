package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * <p>
 * Output: 3
 * <p>
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * <p>
 * Output: 1
 * <p>
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * <p>
 * Output: 3
 * <p>
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * @author Sanjay Singh Rawat
 */
public class P8LongestSubstringWithoutRepeatingChars {

    public static void main(String[] args) {
        assertThat(slidingWindow("abcabcbb")).isEqualTo(3);
        assertThat(slidingWindow("bbbbb")).isEqualTo(1);
        assertThat(slidingWindow("pwwkew")).isEqualTo(3);

        assertThat(lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
        assertThat(lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
        assertThat(lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
    }

    private static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> charsIndex = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            if (charsIndex.containsKey(rightChar)) {
                left = Math.max(left, charsIndex.get(rightChar) + 1);
            }

            charsIndex.put(rightChar, right);

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    private static int slidingWindow(String str) {
        int left = 0;
        int maxLength = 0;
        Set<Character> seen = new HashSet<>();

        for (int right = 0; right < str.length();) {
            char rightChar = str.charAt(right);
            if (!seen.contains(rightChar)) {
                seen.add(rightChar);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                char leftChar = str.charAt(left);
                seen.remove(leftChar);
                left++;
            }

        }

        return maxLength;
    }
}
