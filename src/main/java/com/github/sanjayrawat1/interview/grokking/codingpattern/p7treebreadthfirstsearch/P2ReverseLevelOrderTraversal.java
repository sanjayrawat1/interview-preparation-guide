package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * <br>
 * Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e.,
 * the lowest level comes first. You should populate the values of all nodes in each level from left to right
 * in separate sub-array.
 *
 * @author Sanjay Singh Rawat
 */
public class P2ReverseLevelOrderTraversal {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(reverseLevelOrder(root)).containsSequence(List.of(9, 10, 5), List.of(7, 1), List.of(12));
    }

    private static List<List<Integer>> reverseLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevelOrder = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.remove();
                currentLevelOrder.add(current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result.add(0, currentLevelOrder);
        }

        return result;
    }
}
