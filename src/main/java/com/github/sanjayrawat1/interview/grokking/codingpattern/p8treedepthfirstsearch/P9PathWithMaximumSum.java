package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * <br>
 * Find the path with the maximum sum in a given binary tree. Write a function that return the maximum sum.
 * <br>
 * A path can be defined as a sequence of nodes between any two nodes and doesn't necessarily pass through
 * the root. The path must contain at least one node.
 *
 * @author Sanjay Singh Rawat
 */
public class P9PathWithMaximumSum {

    public static void main(String[] args) {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertThat(findMaximumPathSum(root)).isEqualTo(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        assertThat(findMaximumPathSum(root)).isEqualTo(31);

        var root1 = new TreeNode(-3);
        root1.left = new TreeNode(-1);
        assertThat(findMaximumPathSum(root1)).isEqualTo(-1);
    }

    private static int globalMaximumSum;

    private static int findMaximumPathSum(TreeNode root) {
        globalMaximumSum = Integer.MIN_VALUE;
        findMaximumPathSumRecursive(root);
        return globalMaximumSum;
    }

    /**
     * Ignore the paths with negative sums. Since we need to find the overall maximum sum, we should ignore
     * any path which has an overall negative sum.
     */
    private static int findMaximumPathSumRecursive(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftMaximumSum = findMaximumPathSumRecursive(currentNode.left);
        int rightMaximumSum = findMaximumPathSumRecursive(currentNode.right);

        // ignore paths with negative sums, since we need to find the maximum sum
        // we should ignore any path which has an overall negative sum
        leftMaximumSum = Math.max(leftMaximumSum, 0);
        rightMaximumSum = Math.max(rightMaximumSum, 0);

        // maximum path sum at the currentNode will be equal to the sum from the
        // left subtree + the sum from the right subtree + value of the currentNode
        int localMaximumSum = leftMaximumSum + rightMaximumSum + currentNode.val;

        // update the globalMaximumSum
        globalMaximumSum = Math.max(globalMaximumSum, localMaximumSum);

        // maximum sum of any path from the currentNode will be equal to the maximum
        // of the sums from left to right subtree plus the value of the currentNode
        return Math.max(leftMaximumSum, rightMaximumSum) + currentNode.val;
    }
}
