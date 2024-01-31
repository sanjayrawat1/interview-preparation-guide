package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P11CompareStringContainingBackspaces {

    public static void main(String[] args) {
        assertThat(backspaceCompare("ab#c", "ad#c")).isTrue();
        assertThat(backspaceCompare("xy#z", "xzz#")).isTrue();
        assertThat(backspaceCompare("xy#z", "xyz#")).isFalse();
        assertThat(backspaceCompare("xp#", "xyz##")).isTrue();
        assertThat(backspaceCompare("xywrrmp", "xywrrmu#p")).isTrue();
        assertThat(backspaceCompare("bbbextm", "bbb#extm")).isFalse();
    }

    /**
     * To compare the given strings, first, we need to apply the backspace.
     * An efficient way to do this would be from the end of both the strings.
     * We can have separate pointers, pointing to the last element of the given strings.
     * We can start comparing the characters pointed out by both the pointers to see if the strings are equal.
     * If, at any stage, the characters pointed out by any of the pointers is a backspace (#),
     * we will skip and apply the backspace until we have a valid character available for comparison.
     *
     */
    private static boolean backspaceCompare(String str1, String str2) {
        int str1Index = str1.length() - 1;
        int str2Index = str2.length() - 1;

        while (str1Index >= 0 || str2Index >= 0) {
            int i = getNextValidCharacterIndex(str1, str1Index);
            int j = getNextValidCharacterIndex(str2, str2Index);

            if (i < 0 && j < 0) {
                // reached the end of both strings
                return true;
            }
            if (i < 0 || j < 0) {
                // reached the end of both strings
                return false;
            }
            if (str1.charAt(i) != str2.charAt(j)) {
                // check if the characters are equal
                return false;
            }

            str1Index = i - 1;
            str2Index = j - 1;
        }
        return true;
    }

    private static int getNextValidCharacterIndex(String str, int index) {
        int backspaceCount = 0;
        while (index >= 0) {
            if (str.charAt(index) == '#') {
                // found a backspace character
                backspaceCount++;
            } else if (backspaceCount > 0) {
                // a non-backspace character
                backspaceCount--;
            } else {
                break;
            }
            // skip a backspace or valid character
            index--;
        }
        return index;
    }
}
