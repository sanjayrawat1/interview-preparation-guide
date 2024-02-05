package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in given tree.
 *
 * @author Sanjay Singh Rawat
 */
public class P6PathWithGivenSequence {

    public static void main(String[] args) {
        var root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        assertThat(findPath(root, new int[] {1, 0, 7})).isFalse();
        assertThat(findPath(root, new int[] {1, 1, 6})).isTrue();
    }

    /**
     * Track the element of the given sequence that we should match with the current node.
     * Also, we can return false as soon as we find a mismatch between the sequence and the node value.
     */
    private static boolean findPath(TreeNode root, int[] sequence) {
        if (root == null) {
            return sequence.length == 0;
        }
        return findPath(root, sequence, 0);
    }

    private static boolean findPath(TreeNode root, int[] sequence, int sequenceIndex) {
        if (root == null) {
            return false;
        }

        int sequenceLength = sequence.length;

        if (sequenceIndex >= sequenceLength || root.val != sequence[sequenceIndex]) {
            return false;
        }

        if (root.left == null && root.right == null && root.val == sequence[sequenceIndex]) {
            return true;
        }

        boolean sequencePresentInLeft = findPath(root.left, sequence, sequenceIndex + 1);
        boolean sequencePresentInRight = findPath(root.right, sequence, sequenceIndex + 1);

        return sequencePresentInLeft || sequencePresentInRight;
    }
}
