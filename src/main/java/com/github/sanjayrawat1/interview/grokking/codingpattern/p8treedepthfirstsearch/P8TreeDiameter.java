package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * <br>
 * Given a binary tree, find the length of its diameter. The diameter of a tree is the number of nodes on the
 * longest path between any two leaf nodes. The diameter of a tree may or may not pass through the root.
 *
 * @author Sanjay Singh Rawat
 */
public class P8TreeDiameter {

    public static void main(String[] args) {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        assertThat(treeDiameter(root)).isEqualTo(5);

        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);

        assertThat(treeDiameter(root)).isEqualTo(7);
    }

    private static int treeDiameter = 0;

    /**
     * <ol>
     *     <li> At every step, we need to find the height of both children of the current node
     *     <li> The height of the current node will be equal to the maximum of the heights of its left or
     *     right children, plus 1 for the current node.
     *     <li> The tree diameter at the current node will be equal to the height of the left child plus the
     *     height of the right child plus 1 for the current node: `diameter = leftTreeHeight + rightTreeHeight + 1.
     *     To find the overall tree diameter, we will use a class level variable. The variable will store the
     *     maximum `diameter` of all the nodes visited so far, hence, eventually, it will have the final tree
     *     diameter.
     * </ol>
     */
    private static int treeDiameter(TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }

    private static int calculateHeight(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftTreeHeight = calculateHeight(currentNode.left);
        int rightTreeHeight = calculateHeight(currentNode.right);

        // if the current node doesn't have a left or right subtree, we can't have a path passing through it,
        // since we need a leaf node on each side.
        if (leftTreeHeight != 0 && rightTreeHeight != 0) {
            // diameter at the currentNode will be equal to the height of the left
            // subtrees + height of right subtrees + 1 for the currentNode
            int diameter = leftTreeHeight + rightTreeHeight + 1;

            // update the global tree diameter
            treeDiameter = Math.max(treeDiameter, diameter);
        }

        // height of the currentNode will be equal to the maximum of the heights of
        // left or right subtrees plus 1
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }
}
