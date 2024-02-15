package com.github.sanjayrawat1.interview.grokking.codingpattern.p10subsets;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 * <p>
 * Given a string, find all of its permutations preserving the character sequence but changing case.
 * <li> Example 1: <br>
 *      Input: s = "a1b2" <br>
 *      Output: ["a1b2","a1B2","A1b2","A1B2"]
 * <li> Example 2: <br>
 *      Input: s = "3z4" <br>
 *      Output: ["3z4","3Z4"]
 * <li> Example 3: <br>
 *      Input: s = "ab7c" <br>
 *      Output: ["ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"]
 *
 * @author Sanjay Singh Rawat
 */
public class P4StringPermutationByChangingCase {

    /**
     * Following a BFS approach, we will consider one character at a time. Since we need to preserve the
     * character sequence, we can start with the actual string and process each character (i.e., make it
     * upper-case or lower-case) one by one:
     * <ol>
     *     <li> Starting with the actual string: "ab7c"
     *     <li> Processing the first character 'a', we will get two permutations: "ab7c", "Ab7c"
     *     <li> Processing the second character 'b' we will get four permutations: "ab7c", "Ab7c", "aB7c",
     *          "AB7c"
     *     <li> Since the third character is a digit, we can skip it.
     *     <li> Processing the fourth character 'c', we will get eight permutations: "ab7c", "Ab7c", "aB7c",
     *          "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
     * </ol>
     * <li> Since we can have 2ᴺ permutations at the most and while processing each permutation we convert
     *      it into a character array, the overall time complexity of the algorithm will be O(N*2ᴺ).
     * <li> All the additional space used by our algorithm is for the output list. Since we can have a total
     *      of O(2ᴺ) permutations, the space complexity of our algorithm is O(N*2ᴺ).
     */
    public static List<String> findPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        permutations.add(str);

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                int size = permutations.size();
                for (int j = 0; j < size; j++) {
                    char[] chars = permutations.get(j).toCharArray();
                    if (Character.isLowerCase(chars[i])) {
                        chars[i] = Character.toUpperCase(chars[i]);
                    } else {
                        chars[i] = Character.toLowerCase(chars[i]);
                    }
                    permutations.add(new String(chars));
                }
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        assertThat(findPermutations("a1b2")).containsExactlyInAnyOrder("a1b2", "a1B2", "A1b2", "A1B2");
        assertThat(findPermutations("3z4")).containsExactlyInAnyOrder("3z4", "3Z4");
        assertThat(findPermutations("ab7c")).containsExactlyInAnyOrder("ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C");
    }
}
