package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.stagedtraversal;

/**
 * <p>
 * Given a string of lowercase English letters, rearrange the characters to form a new string representing the
 * <b>next immediate sequence in lexicographical (alphabetical) order</b>. If the given string is already last in
 * lexicographical order among all possible rearrangements, return the arrangement that's first in
 * lexicographical order.
 * <p>
 * <b>Example</b>
 * <br>
 * <b>Input:</b> s = "abcd"
 * <br>
 * <b>Output:</b> abdc
 * <br>
 * <b>Explanation:</b> "abdc" is the next sequence in lexicographical order after rearranging "abcd".
 *
 * @author sanjayrawat1
 */
public class C6NextLexicographicalSequence {

    /**
     * <p>
     * An important detail is that a string's next lexicographical sequence is lexicographically larger than
     * the original string. Consider the string "abc" and all its permutations in a lexicographically ordered
     * sequence:
     * <dl>
     *     <dt>a b c</dt>
     *     <dt>a c b</dt>
     *     <dt>b a c</dt>
     *     <dt>b c a</dt>
     *     <dt>c a b</dt>
     *     <dt>c b a</dt>
     * </dl>
     * <p>
     * We can see the increasing order of the strings in the sequence when we translate each letter to its
     * position in the alphabet:
     * <dl>
     *     <dt>a b c ---> 1 2 3</dt>
     *     <dt>a c b ---> 1 3 2</dt>
     *     <dt>b a c ---> 2 1 3</dt>
     *     <dt>b c a ---> 2 3 1</dt>
     *     <dt>c a b ---> 3 1 2</dt>
     *     <dt>c b a ---> 3 2 1</dt>
     * </dl>
     * <p>
     * From this, we also notice the next string in the sequence after "abc" is "acb", which is the first string
     * larger than the original string.
     * <p>
     * This gives us some indication of what we need to find. The next lexicographical string:
     * <ol>
     *     <li>Incurs the <b>smallest possible lexicographical increase</b> from the original string</li>
     *     <li>Uses the same letters as the original string.</li>
     * </ol>
     * <b>Identifying which character to rearrange</b>
     * <p>
     * Since our goal is to make the smallest possible increase, we need to somehow rearrange the characters on
     * the right side of the string.
     * <p>
     * To understand why, imagine trying to increase a string's value. Increasing the right most letter causes
     * the resulting string to be closer to the original string lexicographically than increasing the leftmost
     * letter does.
     * <p>
     * Therefore, <b>we should focus on rearranging characters on the right-hand side of the string first</b>, if
     * possible.
     * <p>
     * A key insight is that the last string in a lexicographical sequence (i.e., the largest permutation) will
     * always follow a non-increasing order. We can see this with the string "abcc", for example, with its
     * largest possible permutation being "ccba":
     * <dt>
     *     a b c c  ------>  c c b a
     * </dt>
     * <p>
     * How does this help us? We know we need to rearrange the characters on the right of the string, but we
     * don't know how many to rearrange. Let's refer to the rightmost characters that should be rearranged as
     * the suffix.
     * <p>
     * Take the string "abcedda" as an example. We traverse it from right to left with the <b>goal of finding
     * the shortest suffix that can be rearranged to form a larger permutation.</b> The last 4 characters form
     * a non-increasing suffix, and cannot be rearranged to make the string larger.
     * <p>
     * However, the next character, 'c', breaks the non-increasing sequence. Let's call this character the
     * pivot.
     * <p>
     * If no pivot is found, it means the string is already the last lexicographical sequence. In this case,
     * we need to obtain its first lexicographically permutation as the problem states. This can be done by
     * reversing the string.
     * <dt>
     *     e d d a -----> a d d e
     * </dt>
     * <p>
     * <b>Rearranging characters</b>
     * <p>
     * Having identified the shortest suffix to rearrange, the next objective is to rearrange this suffix to
     * make the <b>smallest increase possible.</b> We have to start the rearrangement at the pivot since the
     * rest of the suffix is already arranged in its largest permutation.
     * <p>
     * To make the character at the pivot position larger, we'd need to <b>swap the pivot with a character
     * larger than it on the right,</b> but not too much larger because the increase should be as small as
     * possible.
     * <p>
     * Since the substring after the pivot is lexicographically non-increasing, we can find the closest
     * character larger than 'c' by <b>traversing this suffix from right to left and stopping at the first
     * character larger than it.</b>
     * <p>
     * Now, we swap the pivot and the rightmost successor:
     * <dt>
     *     a b c e d d a -------> a b d e d c a
     * </dt>
     * <p>
     * The character at the pivot has increased, so to get the next permutation, <b>we should make the substring
     * after the pivot as small as possible.</b> After the swap, substring "edca" is not at its smallest
     * permutation. So, we need to minimize the permutation of this substring.
     * <p>
     * An important observation is that after the previous swap, the substring after the pivot is still
     * lexicographically non-increasing. This means we can minimize this substring's permutations by reversing
     * it.
     * <dt>
     *     a b d e d c a    ----->     a b d a c d e
     * </dt>
     * <p>
     * <br>
     * <b>Summary:</b>
     * <ol>
     *     <li>
     *         Locate the pivot.
     *         <ul>
     *             <li>
     *                 The pivot is the first character that breaks the non-increasing sequence from the right
     *                 of the string.
     *             </li>
     *             <li>
     *                 If no pivot found, the string is already at its last lexicographical sequence, and the
     *                 result is just the reverse of the string.
     *             </li>
     *         </ul>
     *     </li>
     *     <li>
     *         Find the rightmost successor to the pivot (a character larger than pivot on the right).
     *     </li>
     *     <li>
     *         Swap the rightmost successor with the pivot to increase the lexicographical order of the suffix.
     *     </li>
     *     <li>
     *         Reverse the suffix after the pivot to minimize its permutation.
     *     </li>
     * </ol>
     * <p>
     * <b>Time Complexity:</b> O(n)
     * <br>
     * <b>Space Complexity:</b> O(n) -> Space taken by letters array.
     */
    public String nextLexicographicalSequence(String s) {
        char[] letters = s.toCharArray();

        // Locate the pivot, which is the first character from the right that breaks
        // non-increasing order. Start searching from the second-to-last position.
        int pivot = letters.length - 2;
        while (pivot >= 0 && letters[pivot] >= letters[pivot + 1]) {
            pivot--;
        }

        // If pivot is not found, the string is already in its largest permutation. In
        // this case, reverse the string to obtain the smallest permutation.
        if (pivot == -1) {
            reverse(letters, 0, letters.length - 1);
            return new String(letters);
        }

        // Find the rightmost successor to the pivot.
        int rightmostSuccessor = letters.length - 1;
        while (letters[rightmostSuccessor] <= letters[pivot]) {
            rightmostSuccessor--;
        }

        // Swap the rightmost successor with the pivot to increase the lexicographical order of the suffix.
        swap(letters, pivot, rightmostSuccessor);

        // Reverse the suffix after the pivot to minimize its permutation.
        reverse(letters, pivot + 1, letters.length - 1);

        return new String(letters);
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] arr, int idx1, int idx2) {
        char temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
