package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/minimum-window-substring/description/
 * <p>
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <li>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * <li>
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * <p>
 * Output: "a"
 * <p>
 * Explanation: The entire string s is the minimum window.
 * <li>
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * <p>
 * Output: ""
 * <p>
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 * @author Sanjay Singh Rawat
 */
public class P13SmallestWindowContainingSubstring {

    public static void main(String[] args) {
        assertThat(slidingWindow("ADOBECODEBANC", "ABC")).isEqualTo("BANC");
        assertThat(slidingWindow("a", "a")).isEqualTo("a");
        assertThat(slidingWindow("a", "aa")).isEqualTo("");
        assertThat(slidingWindow("aabdec", "abc")).isEqualTo("abdec");
        assertThat(slidingWindow("abdbca", "abc")).isEqualTo("bca");
        assertThat(slidingWindow("adcad", "abc")).isEqualTo("");
    }

    private static String slidingWindow(String str, String pattern) {
        if (pattern.length() > str.length()) {
            return "";
        }

        Map<Character, Integer> charsFrequency = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            charsFrequency.put(ch, charsFrequency.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int minLength = str.length() + 1;
        int substringLeft = 0;
        int matched = 0;

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            if (charsFrequency.containsKey(rightChar)) {
                charsFrequency.put(rightChar, charsFrequency.get(rightChar) - 1);
                // count every matching of a character
                if (charsFrequency.get(rightChar) >= 0) {
                    matched++;
                }
            }

            // Shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length()) {
                if (minLength > right - left + 1) {
                    minLength = right - left + 1;
                    substringLeft = left;
                }

                char leftChar = str.charAt(left);
                left++;

                if (charsFrequency.containsKey(leftChar)) {
                    if (charsFrequency.get(leftChar) == 0) {
                        matched--;
                    }
                    charsFrequency.put(leftChar, charsFrequency.get(leftChar) + 1);
                }
            }
        }
        return minLength > str.length() ? "" : str.substring(substringLeft, substringLeft + minLength);
    }
}
