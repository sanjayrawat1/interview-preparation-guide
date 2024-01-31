package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 * <p>
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <li>
 * Example 1:
 * <p>
 * Input: s = "cbaebabacd", p = "abc"
 * <p>
 * Output: [0,6]
 * <p>
 * Explanation:
 * <p>
 * The substring with start index = 0 is "cba", which is an anagram of "abc".<br>
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <li>
 * Example 2:
 * <p>
 * Input: s = "abab", p = "ab"
 * <p>
 * Output: [0,1,2]
 * <p>
 * Explanation:
 * <p>
 * The substring with start index = 0 is "ab", which is an anagram of "ab".<br>
 * The substring with start index = 1 is "ba", which is an anagram of "ab".<br>
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * @author Sanjay Singh Rawat
 */
public class P12StringAnagrams {

    public static void main(String[] args) {
        assertThat(slidingWindow("cbaebabacd", "abc")).contains(0, 6);
        assertThat(slidingWindow("abab", "ab")).contains(0, 1, 2);
        assertThat(slidingWindow("ppqp", "pq")).contains(1, 2);
        assertThat(slidingWindow("abbcabc", "abc")).contains(2, 3, 4);
    }

    private static List<Integer> slidingWindow(String str, String pattern) {
        if (pattern.length() > str.length()) {
            return List.of();
        }

        Map<Character, Integer> charsFrequency = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            charsFrequency.put(ch, charsFrequency.getOrDefault(ch, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        int left = 0;
        int matched = 0;

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            if (charsFrequency.containsKey(rightChar)) {
                charsFrequency.put(rightChar, charsFrequency.get(rightChar) - 1);
                if (charsFrequency.get(rightChar) == 0) {
                    matched++;
                }
            }

            if (matched == charsFrequency.size()) {
                answer.add(left);
            }

            if (right - left + 1 >= pattern.length()) {
                char leftChar = str.charAt(left);
                if (charsFrequency.containsKey(leftChar)) {
                    if (charsFrequency.get(leftChar) == 0) {
                        matched--;
                    }
                    charsFrequency.put(leftChar, charsFrequency.get(leftChar) + 1);
                }
                left++;
            }
        }
        return answer;
    }
}
