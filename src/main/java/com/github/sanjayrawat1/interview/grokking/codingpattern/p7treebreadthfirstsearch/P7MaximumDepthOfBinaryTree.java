package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * <br>
 * Given a binary tree, find its maximum depth (or height).
 *
 * @author Sanjay Singh Rawat
 */
public class P7MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        assertThat(maximumDepth(root)).isEqualTo(3);

        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        assertThat(maximumDepth(root)).isEqualTo(4);

    }

    private static int maximumDepth(TreeNode root) {
        int maximumDepth = 0;
        if (root == null) {
            return maximumDepth;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            maximumDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return maximumDepth;
    }
}
