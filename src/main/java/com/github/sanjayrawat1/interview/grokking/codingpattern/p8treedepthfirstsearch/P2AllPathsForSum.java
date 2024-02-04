package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * <br>
 * Given a binary tree and a number `S`, find all paths from root-to-leaf such that the sum of all the node
 * values of each path equals `S`.
 *
 * @author Sanjay Singh Rawat
 */
public class P2AllPathsForSum {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(findPaths(root, 23)).containsSequence(List.of(12, 7, 4), List.of(12, 1, 10));
    }

    private static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        findPaths(root, sum, new ArrayList<>(), allPaths);
        return allPaths;
    }

    /**
     * <ol>
     *     <li> Every time we find a root-to-leaf path, we will store it in a list.
     *     <li> We will traverse all paths and will not stop processing after finding the first path.
     * </ol>
     * <ul>
     *     <li> The time complexity of the above algorithm is O(N^2), where N is the total number of nodes
     *     in the tree. This is due to the fact that we traverse each node once (which will take O(N)), and
     *     for every leaf node, we might have to store its path (by making a copy of the current path) which
     *     will take O(N). We can calculate a tighter time complexity of O(NlogN) from the space complexity
     *     discussion below.
     *     <li> If we ignore the space required for the allPaths list, the space complexity of the above
     *     algorithm will be O(N) in the worst case. This space will be used to store the recursion stack.
     *     The worst-case will happen when the given tree is a linked list (i.e., every node has only one child).
     * </ul>
     */
    private static void findPaths(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (root == null) {
            return;
        }

        // add the current node to the path
        currentPath.add(root.val);

        // if the current node is a leaf, and it's value is equal to sum, save the current path
        if (root.val == sum && root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findPaths(root.left, sum - root.val, currentPath, allPaths);
            findPaths(root.right, sum - root.val, currentPath, allPaths);
        }

        // remove the current node for the path to backtrack, we need to remove the currentNode while we are going
        // up the recursive call stack
        currentPath.remove(currentPath.size() - 1);
    }
}
