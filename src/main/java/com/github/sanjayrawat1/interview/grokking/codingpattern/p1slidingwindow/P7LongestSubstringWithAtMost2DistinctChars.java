package com.github.sanjayrawat1.interview.grokking.codingpattern.p1slidingwindow;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P7LongestSubstringWithAtMost2DistinctChars {

    public static void main(String[] args) {
        assertThat(slidingWindow("araaci")).isEqualTo(3);
    }

    private static int slidingWindow(String str) {
        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> seen = new HashMap<>();

        for (int right = 0; right < str.length(); right++) {
            char rightChar = str.charAt(right);
            if (seen.containsKey(rightChar)) {

            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
