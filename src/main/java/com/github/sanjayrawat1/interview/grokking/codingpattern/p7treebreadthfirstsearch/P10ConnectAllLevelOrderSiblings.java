package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each level should
 * point to the first node of next level.
 *
 * @author Sanjay Singh Rawat
 */
public class P10ConnectAllLevelOrderSiblings {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root = connectAllSiblings(root);
        print(root);

    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static TreeNode connectAllSiblings(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        TreeNode previousNode = null;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (previousNode != null) {
                previousNode.next = current;
            }
            previousNode = current;

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return root;
    }

    private static void print(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }
}
