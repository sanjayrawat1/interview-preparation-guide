package com.github.sanjayrawat1.interview.grokking.codingpattern.p7treebreadthfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * <br>
 * Given a binary tree, populate an array to represent its level-by-level traversal.
 * You should populate the values of all nodes of each level from left to right in separate sub-arrays.
 *
 * @author Sanjay Singh Rawat
 */
public class P1BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        var root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        assertThat(levelOrder(root)).containsSequence(List.of(3), List.of(9, 20), List.of(15, 7));
    }

    /**
     * Since we need to traverse all nodes of each level before moving onto the next level, we can use the
     * Breadth First Search (BFS) technique to solve this problem.
     * <br>
     * We can use a Queue to efficiently traverse in BFS fashion. Here are the steps of our algorithm:
     * <ol>
     *     <li> Start by pushing the root node to the queue.
     *     <li> Keep iterating until the queue is empty.
     *     <li> In each iteration, first count the elements in the queue (let’s call it levelSize).
     *          We will have these many nodes in the current level.
     *     <li> Next, remove levelSize nodes from the queue and push their value in an array to represent the current level.
     *     <li> After removing each node from the queue, insert both of its children into the queue.
     *     <li> If the queue is not empty, repeat from step 3 for the next level.
     * </ol>
     *
     * <li> The time complexity of the above algorithm is O(N), where N is the total number of nodes in the tree.
     * This is due to the fact that we traverse each node once.
     * <li> The space complexity of the above algorithm will be O(N) as we need to return a list containing the level
     * order traversal. We will also need O(N) space for the queue. Since we can have a maximum of N/2 nodes at any
     * level (this could happen only at the lowest level), therefore we will need O(N) space to store them in the queue.
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

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
            result.add(currentLevelOrder);
        }
        return result;
    }
}
