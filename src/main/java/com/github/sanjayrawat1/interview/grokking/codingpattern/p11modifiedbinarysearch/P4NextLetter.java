package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 * <p>
 * Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array
 * greater than a given 'key'.
 * <P>
 * Assume the given array is a circular list, which means that the last letter is assumed to be connected with
 * the first letter. This also means that the smallest letter in the given array is greater than the last letter
 * of the array and is also the first letter of the array.
 * <br>
 * Write a function to return the next letter of the given 'key'.
 *
 * @author Sanjay Singh Rawat
 */
public class P4NextLetter {

    /**
     * <ol>
     *     <li> The array is considered circular, which means if the 'key' is bigger than the last letter of
     *          the array or if it is smaller than the first letter of the array, the key's next letter will
     *          be the first letter of the array.
     *     <li> We have to find the next biggest letter which can't be equal to the 'key'. This means that
     *          we will ignore the case where key == arr[middle]. To handle this case, we can update our start
     *          range to start = middle + 1.
     *     <li> In the end, instead of returning the element pointed out by start, we have to return the letter
     *          pointed out by `start % array.length`. This is needed because of point 2. Imagine that the
     *          last letter of the array is equal to the 'key'. In that case, we have to return the first
     *          letter of the input array.
     * </ol>
     */
    public static char nextLetter(char[] letters, int key) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (letters[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // since the loop is running until 'start <= end', so at the end of the while loop, 'start === end+1'
        return letters[start % letters.length];
    }

    public static void main(String[] args) {
        assertThat(nextLetter(new char[] {'a', 'c', 'f', 'h'}, 'f')).isEqualTo('h');
        assertThat(nextLetter(new char[] {'a', 'c', 'f', 'h'}, 'b')).isEqualTo('c');
        assertThat(nextLetter(new char[] {'a', 'c', 'f', 'h'}, 'm')).isEqualTo('a');
        assertThat(nextLetter(new char[] {'a', 'c', 'f', 'h'}, 'h')).isEqualTo('a');
    }
}
