package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * <br>
 * Find the minimum depth of a binary tree. The minimum depth is the number of nodes along the shortest path
 * from the root node to the nearest leaf node.
 *
 * @author Sanjay Singh Rawat
 */
public class P6MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        assertThat(minimumDepth(root)).isEqualTo(2);

        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        assertThat(minimumDepth(root)).isEqualTo(3);
    }

    private static int minimumDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int minimumDepth = 0;

        while (!queue.isEmpty()) {
            minimumDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left == null && current.right == null) {
                    return minimumDepth;
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return minimumDepth;
    }
}
