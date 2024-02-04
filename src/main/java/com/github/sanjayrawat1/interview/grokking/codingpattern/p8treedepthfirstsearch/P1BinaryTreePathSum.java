package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/path-sum/
 * <br>
 * Given a binary tree and a number `S`, find if a tree has a path from root to leaf such that the sum of all
 * the node values of that path equals to `S`.
 *
 * @author Sanjay Singh Rawat
 */
public class P1BinaryTreePathSum {

    public static void main(String[] args) {
        /*
                        12
                      /    \
                    7       1
                  /       /   \
                9       10     5
         */
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(hashPath(root, 23)).isTrue();
        assertThat(hashPath(root, 16)).isFalse();
    }

    /**
     * As we are trying to search for a root-to-leaf path, we can use the Depth First Search (DFS) technique
     * to solve this problem.
     * <br>
     * To recursively traverse a binary tree in a DFS fashion, we can start from the root and at every step,
     * make two recursive calls one for the left and one for the right child.
     * <br>
     * Here are the steps:
     * <ol>
     *     <li> Start DFS with the root of the tree.
     *     <li> If the current node is not a leaf node, do two things:
     *          <ul>
     *              <li> Subtract the value of the current node from the given number to get a new sum.
     *                  sum = sum - node.val
     *              <li> Make two recursive calls for both the children of the current node with the new number
     *                  calculated in previous step.
     *          </ul>
     *     <li> At every step, see if the current node being visited is a leaf node and if its value is equal
     *          to the given number `sum`. If both these conditions are true, we have found the required
     *          root-to-leaf path, therefore return true.
     *     <li> If the current node is a leaf but its value is not equal to the given number sum, return false.
     * </ol>
     */
    private static boolean hashPath(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // start DFS with the root of the tree, if the current node is a leaf, and it's value is
        // equal to the sum then we've found a path
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }

        // recursively call to traverse the left and right subtree
        // return true if any of the two recursive calls return true
        boolean pathExistInLeft = hashPath(root.left, sum - root.val);
        boolean pathExistInRight = hashPath(root.right, sum - root.val);

        return pathExistInLeft || pathExistInRight;
    }
}
