package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 * <br>
 * Given a binary tree, return all root-to-leaf paths.
 *
 * @author Sanjay Singh Rawat
 */
public class P3AllPaths {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(findPaths(root)).containsSequence(List.of(12, 7, 4), List.of(12, 1, 10));
    }

    private static List<List<Integer>> findPaths(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        findAPath(root, new ArrayList<>(), allPaths);
        return allPaths;
    }

    private static void findAPath(TreeNode root, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (root == null) {
            return;
        }

        currentPath.add(root.val);

        if (root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findAPath(root.left, currentPath, allPaths);
            findAPath(root.right, currentPath, allPaths);
        }

        currentPath.remove(currentPath.size() - 1);
    }
}
