package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a binary tree, find the root-to-leaf path with the maximum sum.
 *
 * @author Sanjay Singh Rawat
 */
public class P4MaxPathSum {

    public static void main(String[] args) {
        /*
                    12
                  /    \
                 7      1
               /      /   \
              4      10    5
         */
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(maxPathSum(root)).isEqualTo(23);

        root.left.right = new TreeNode(8);
        assertThat(maxPathSum(root)).isEqualTo(27);
    }

    private static int maxSum = Integer.MIN_VALUE;

    private static int maxPathSum(TreeNode root) {
        maxPathSum(root, new ArrayList<>());
        return maxSum;
    }

    private static void maxPathSum(TreeNode root, List<Integer> currentPath) {
        if (root == null) {
            return;
        }

        currentPath.add(root.val);

        if (root.left == null && root.right == null) {
            int pathMax = 0;
            for (int i = 0; i < currentPath.size(); i++) {
                pathMax += currentPath.get(i);
            }
            maxSum = Math.max(maxSum, pathMax);
        } else {
            maxPathSum(root.left, currentPath);
            maxPathSum(root.right, currentPath);
        }
        currentPath.remove(currentPath.size() - 1);
    }
}
