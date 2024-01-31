package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/permutation-in-string/
 * <p>
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <li>
 * Example 1:
 * <p>
 * Input: s1 = "ab", s2 = "eidbaooo"
 * <p>
 * Output: true
 * <p>
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <li>
 * Example 2:
 * <p>
 * Input: s1 = "ab", s2 = "eidboaoo"
 * <p>
 * Output: false
 *
 * @author Sanjay Singh Rawat
 */
public class P11PermutationInString {

    public static void main(String[] args) {
        assertThat(slidingWindow("eidbaooo", "ab")).isTrue();
        assertThat(slidingWindow("eidboaoo", "ab")).isFalse();
        assertThat(slidingWindow("oidbcaf", "abc")).isTrue();
        assertThat(slidingWindow("odicf", "dc")).isFalse();
        assertThat(slidingWindow("bcdxabcdy", "bcdxabcdy")).isTrue();
        assertThat(slidingWindow("aaacb", "abc")).isTrue();
    }

    /**
     * We can use a HashMap to remember the frequencies of all characters in the given pattern.
     * Our goal will be to match all the characters from this HashMap with a sliding window in the given string.
     * Here are the steps of our algorithm:
     * <ul>
     * <li> Create a HashMap to calculate the frequencies of all characters in the pattern.
     * <li> Iterate through the string, adding one character at a time in the sliding window.
     * <li> If the character being added matches a character in the HashMap, decrement its frequency in the map.
     *      If the character frequency becomes zero, we got a complete match.
     * <li> If at any time, the number of characters matched is equal to the number of distinct characters in the
     *      pattern (i.e., total characters in the HashMap), we have gotten our required permutation.
     * <li> If the window size is greater than the length of the pattern, shrink the window to make it equal to the pattern's size.
     *      At the same time, if the character going out was part of the pattern, put it back in the frequency HashMap.
     * </ul>
     * Time complexity will be O(N + M), where N and M are the number of characters in the input string and the pattern, respectively.
     * <p>
     * Space complexity is O(M) since, in the worst case, the whole pattern can have distinct characters that will go into the HashMap.
     */
    private static boolean slidingWindow(String str, String pattern) {
        if (pattern.length() > str.length()) {
            return false;
        }
        // Create a HashMap to calculate the frequencies of all characters in the pattern.
        Map<Character, Integer> charsFrequency = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            charsFrequency.put(ch, charsFrequency.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int patternCharMatchCount = 0;

        // Iterate through the string, adding one character at a time in the sliding window.
        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);

            // If the character being added matches a character in the HashMap, decrement its frequency in the map.
            // If the character frequency becomes zero, we got a complete match.
            if (charsFrequency.containsKey(rightChar)) {
                charsFrequency.put(rightChar, charsFrequency.get(rightChar) - 1);
                if (charsFrequency.get(rightChar) == 0) {
                    patternCharMatchCount++;
                }
            }

            // If at any time, the number of characters matched is equal to the number of distinct characters in the
            // pattern (i.e., total characters in the HashMap), we have gotten our required permutation.
            if (patternCharMatchCount == charsFrequency.size()) {
                return true;
            }

            // If the window size is greater than the length of the pattern, shrink the window to make it equal to the pattern's size.
            // At the same time, if the character going out was part of the pattern, put it back in the frequency HashMap.
            if (right - left + 1 >= pattern.length()) {
                char leftChar = str.charAt(left);
                if (charsFrequency.containsKey(leftChar)) {
                    if (charsFrequency.get(leftChar) == 0) {
                        patternCharMatchCount--;
                    }
                    charsFrequency.put(leftChar, charsFrequency.getOrDefault(leftChar, 0) + 1);
                }
                left++;
            }
        }

        return false;
    }
}
