package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * <br>
 * Given a binary tree, connect each node with its level order successor.
 * The last node of each level should point to a null node.
 *
 * @author Sanjay Singh Rawat
 */
public class P9ConnectLevelOrderSiblings {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        TreeNode treeNode = connectLevelOrderSiblings(root);
        print(treeNode);
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

    private static TreeNode connectLevelOrderSiblings(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode previousNode = null;
            for (int i = 0; i < levelSize; i++) {
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
        }

        return root;
    }

    private static void print(TreeNode root) {
        TreeNode nextLevelRoot = root;
        while (nextLevelRoot != null) {
            TreeNode currentNode = nextLevelRoot;
            nextLevelRoot = null;
            while (currentNode != null) {
                System.out.print(currentNode.val + " -> ");
                if (nextLevelRoot == null) {
                    if (currentNode.left != null) {
                        nextLevelRoot = currentNode.left;
                    } else if (currentNode.right != null) {
                        nextLevelRoot = currentNode.right;
                    }
                }
                currentNode = currentNode.next;
            }
            System.out.print("NULL");
            System.out.println();
        }
    }
}
