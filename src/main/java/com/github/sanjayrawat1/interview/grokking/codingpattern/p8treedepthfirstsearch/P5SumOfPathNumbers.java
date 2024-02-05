package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * <br>
 * Given a binary tree where each node can only have digit (0-9) value, each root-to-leaf path will represent
 * a number. Find the total sum of all the numbers represented by all paths.
 *
 * @author Sanjay Singh Rawat
 */
public class P5SumOfPathNumbers {

    public static void main(String[] args) {
        var root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        assertThat(findRootToLeafPathSum(root)).isEqualTo(332);
    }

    private static int findRootToLeafPathSum(TreeNode root) {
        return findRootToLeafPathSum(root, 0);
    }

    /**
     * How do we calculate the path number for a node? Taking the first example mentioned above, say we are at node 0.
     * As we know, the path number for this node is 10, which was calculated by: 1 * 10 + 0 => 10. We will follow the
     * same approach to calculate the path number of each node.
     */
    private static int findRootToLeafPathSum(TreeNode root, int pathSum) {
        if (root == null) {
            return 0;
        }

        // calculate the path number of the current node
        pathSum = 10 * pathSum + root.val;

        // if the currentNode is a leaf, return the current pathSum
        if (root.left == null && root.right == null) {
            return pathSum;
        }

        // traverse the left and the right subtree
        int leftPathNumber = findRootToLeafPathSum(root.left, pathSum);
        int rightPathNumber = findRootToLeafPathSum(root.right, pathSum);
        return leftPathNumber + rightPathNumber;
    }
}
