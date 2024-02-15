package com.github.sanjayrawat1.interview.grokking.codingpattern.p10subsets;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * <p>
 * For a given number N, write a function to generate all combination of N pairs of balanced parentheses.
 * <li> Example 1: <br>
 *      Input: n = 3 <br>
 *      Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <li> Example 2:
 *      Input: n = 1 <br>
 *      Output: ["()"]
 *
 * @author Sanjay Singh Rawat
 */
public class P5BalancedParentheses {

    record ParenthesesInfo(String str, int openCount, int closeCount) {}

    /**
     * Following the BFS approach, we will keep adding open parentheses '(' or close parentheses ')'. At each
     * step we need to keep two things in mind:
     * <ol>
     *     <li> We can't add more than N open parentheses.
     *     <li> To keep the parentheses balanced, we can add a close parentheses ')' only when we have already
     *          added enough open parentheses '('. For this, we can keep a count of open and close parentheses
     *          with every combination.
     * </ol>
     * Following this guideline, let's generate parentheses for N = 3:
     * <ol>
     *     <li> Start with an empty combination: ""
     *     <li> At every step, let's take all combination of previous step and add '(' or ')' keeping the
     *          above mentioned two rules in mind.
     *     <li> For the empty combination, we can add '(' since the count of open parentheses will be less
     *          than N. We can't add ')' as we don't have an equivalent open parentheses, so our list of
     *          combinations will now be: '('
     *     <li> For the next iteration, let's take all combinations of previous set. For '(' we can add another
     *          '(' to it since the count of open parentheses will be less than N. We can also add ')' as we
     *          do have an equivalent open parentheses, so our list of combinations will be: "((", "()".
     *     <li> In the next iteration, for the first combination "((", we can add another '(' to it as the count
     *          of open parenthesis will be less than N, we can also add ')' as we do have an equivalent open
     *          parenthesis. This gives us two new combinations: "(((" and "(()". For the second combination
     *          "()", we can add another '(' to it since the count of open parenthesis will be less than N.
     *          We can’t add ')' as we don’t have an equivalent open parenthesis, so our list of combinations
     *          will be: "(((", "(()", "()(".
     *     <li> Following the same approach, next we will get the following list of combinations: "((()",
     *          "(()(", "(())", "()((", "()()"
     *     <li> Next we will get: "((())", "(()()", "(())(", "()(()", "()()("
     *     <li> Finally, we will have the following combinations of balanced parentheses: "((()))", "(()())",
     *          "(())()", "()(())", "()()()"
     *     <li> We can’t add more parentheses to any of the combinations, so we stop here.
     * </ol>
     * <li> Let’s try to estimate how many combinations we can have for N pairs of balanced parentheses. If
     *      we don’t care for the ordering - that ) can only come after ( - then we have two options for
     *      every position, i.e., either put open parentheses or close parentheses. This means we can have
     *      a maximum of 2ᴺ combinations. Because of the ordering, the actual number will be less than 2ᴺ.
     * <li> If you visualize the representation of this closely you will realize that, in the worst case,
     *      it is equivalent to a binary tree, where each node will have two children. This means that we
     *      will have 2ᴺ leaf nodes and 2ᴺ-1 intermediate nodes. So the total number of elements pushed to
     *      the queue will be 2ᴺ−1, which is asymptotically equivalent to O(2ᴺ). While processing each
     *      element, we do need to concatenate the current string with ( or ). This operation will take O(N),
     *      so the overall time complexity of our algorithm will be O(N*2ᴺ). This is not completely accurate
     *      but reasonable enough to be presented in the interview.
     * <li> All the additional space used by our algorithm is for the output list. Since we can’t have more
     *      than O(2ᴺ) combinations, the space complexity of our algorithm is O(N*2ᴺ).
     */
    public static List<String > generateValidParentheses(int num) {
        List<String> validParentheses = new ArrayList<>();
        Deque<ParenthesesInfo> queue = new ArrayDeque<>();
        queue.offer(new ParenthesesInfo("", 0, 0));

        while (!queue.isEmpty()) {
            ParenthesesInfo parentheses = queue.poll();
            // if we've reached the maximum number of open and closed parentheses, add to the result
            if (parentheses.openCount == num && parentheses.closeCount == num) {
                validParentheses.add(parentheses.str);
            } else {
                // if we can add an open parentheses, add it
                if (parentheses.openCount < num) {
                    queue.offer(new ParenthesesInfo(parentheses.str + "(", parentheses.openCount + 1, parentheses.closeCount));
                }
                // if we can add a close parentheses, add it
                if (parentheses.closeCount < parentheses.openCount) {
                    queue.offer(new ParenthesesInfo(parentheses.str + ")", parentheses.openCount, parentheses.closeCount + 1));
                }
            }
        }
        return validParentheses;
    }

    public static void main(String[] args) {
        assertThat(generateValidParentheses(1)).containsExactlyInAnyOrder("()");
        assertThat(generateValidParentheses(3)).containsExactlyInAnyOrder("((()))", "(()())", "(())()", "()(())", "()()()");
    }
}
