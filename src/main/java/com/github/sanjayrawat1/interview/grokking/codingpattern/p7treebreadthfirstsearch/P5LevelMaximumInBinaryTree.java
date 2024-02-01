package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Find the largest value on each level of a binary tree.
 *
 * @author Sanjay Singh Rawat
 */
public class P5LevelMaximumInBinaryTree {

    public static void main(String[] args) {
        assertThat(levelMax(null)).isEmpty();

        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(levelMax(root)).containsSequence(12, 7, 10);
    }

    private static List<Integer> levelMax(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelMax = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                levelMax = Math.max(levelMax, current.val);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            result.add(levelMax);
        }

        return result;
    }
}
