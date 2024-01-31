package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <br>
 * Given a binary tree, populate an array to represent its zigzag level order traversal. You should populate
 * the values of all nodes of the first level from left to right, then right to left for the next level and
 * keep alternating in the same manner for the following levels.
 *
 * @author Sanjay Singh Rawat
 */
public class P3ZigZagTraversal {

    public static void main(String[] args) {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        assertThat(zigzagLevelOrder(root)).containsSequence(List.of(1), List.of(3, 2), List.of(4, 5, 6, 7));

        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        assertThat(zigzagLevelOrder(root)).containsSequence(List.of(3), List.of(20, 9), List.of(15, 7));

        root = new TreeNode(1);
        assertThat(zigzagLevelOrder(root)).containsSequence(List.of(1));

        assertThat(zigzagLevelOrder(null)).isEmpty();
    }

    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.remove();

                // add the node to the current level based on the traverse direction
                if (leftToRight) {
                    currentLevel.add(current.val);
                } else {
                    currentLevel.add(0, current.val);
                }

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            // level has been finished. push into output array
            result.add(currentLevel);

            // reverse the traversal direction
            leftToRight = !leftToRight;
        }

        return result;
    }
}
