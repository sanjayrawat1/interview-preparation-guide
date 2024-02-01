package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a binary tree and a node, find the level order successor of the given node in the tree. The level
 * order successor is the node that appears right after the given node in the level order traversal.
 *
 * @author Sanjay Singh Rawat
 */
public class P8LevelOrderSuccessor {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        TreeNode result = levelOrderSuccessor(root, new TreeNode(12));
        assertThat(result).isNotNull();
        assertThat(result.val).isEqualTo(7);

        result = levelOrderSuccessor(root, new TreeNode(9));
        assertThat(result).isNotNull();
        assertThat(result.val).isEqualTo(10);

        result = levelOrderSuccessor(root, new TreeNode(7));
        assertThat(result).isNotNull();
        assertThat(result.val).isEqualTo(1);
    }

    /**
     * We can follow the BFS approach, the only difference will be that we will not keep track of all the levels.
     * Instead, we will keep inserting child nodes to the queue. As soon as we find the given node, we will
     * return the next node from the queue as the level order successor.
     */
    private static TreeNode levelOrderSuccessor(TreeNode root, TreeNode node) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }

            if (current.val == node.val) {
                break;
            }
        }

        return queue.isEmpty() ? null : queue.peek();
    }
}
