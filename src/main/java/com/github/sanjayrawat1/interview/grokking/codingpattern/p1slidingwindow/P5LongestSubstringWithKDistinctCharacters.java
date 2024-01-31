package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P5LongestSubstringWithKDistinctCharacters {

    public static void main(String[] args) {
        assertThat(slidingWindow("araaci", 2)).isEqualTo(4);
        assertThat(slidingWindow("araaci", 1)).isEqualTo(2);
        assertThat(slidingWindow("cbbebi", 3)).isEqualTo(5);
    }

    private static int slidingWindow(String str, int k) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        int left = 0;
        int longestLength = 0;

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);

            while (charFrequency.size() > k) {
                char leftChar = str.charAt(left);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }
                left++;
            }
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }
}
