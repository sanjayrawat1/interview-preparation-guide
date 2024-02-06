package com.github.sanjayrawat1.interview.grokking.codingpattern.p8treedepthfirstsearch;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * <br>
 * Given a binary tree and a number `sum`, find all paths in the tree such that the sum of all the node values
 * of each path equals `sum`. Please note that the paths can start or end at any node but all paths must
 * follow direction from parent to child (top to bottom).
 *
 * @author Sanjay Singh Rawat
 */
public class P7CountPathsForSum {

    public static void main(String[] args) {
        var root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        assertThat(countPathsForSum(root, 11)).isEqualTo(2);

        // this test is for integer overflow scenario.
        root = new TreeNode(1000000000);
        root.left = new TreeNode(1000000000);
        root.left.left = new TreeNode(294967296);
        root.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left = new TreeNode(1000000000);
        root.left.left.left.left.left = new TreeNode(1000000000);

        assertThat(countPathsForSum(root, 0)).isEqualTo(0);
    }

    /**
     * <li> The time complexity of the above algorithm is O(N²) in the worst case, where N is the total
     * number of nodes in the tree. This is due to the fact that we traverse each node once, but for every
     * node, we iterate the current path. The current path, in the worst case, can be O(N) (in the case of
     * a skewed tree). But, if the tree is balanced, then the current path will be equal to the height of
     * the tree, i.e., O(logN). So the best case of our algorithm will be O(NlogN).
     * <li> The space complexity of the above algorithm will be O(N). This space will be used to store the
     * recursion stack. The worst case will happen when the given tree is a linked list (i.e., every node has
     * only one child). We also need O(N) space for storing the currentPath in the worst case. Overall space
     * complexity of our algorithm is O(N).
     */
    private static int countPathsForSum(TreeNode root, int sum) {
        return pathSum(root, sum, new ArrayList<>());
    }

    /**
     * <li> We will keep track of the current path in a list which will be passed to every recursive call.
     * <li> Whenever we traverse a node we will do two things:
     *      <ol>
     *          <li> Add the current node to the path.
     *          <li> As we add new node to the current path, we should find the sum of all sum-paths ending at
     *          the current node. If the sum of any sub-path is equal to `sum` we will increment out path count.
     *      </ol>
     * <li> We will traverse all paths and will not stop processing after finding the first path.
     * <li> Remove the current node from the current path before returning from the function. This is needed
     * to backtrack while we are going up the recursive call stack to process other paths.
     */
    private static int pathSum(TreeNode root, int sum, List<Integer> currentPath) {
        if (root == null) {
            return 0;
        }

        currentPath.add(root.val);

        long pathSum = 0;
        int pathCount = 0;

        for (int i = currentPath.size() - 1; i >= 0; i--) {
            pathSum += currentPath.get(i);
            if (pathSum == sum) {
                pathCount++;
            }
        }

        pathCount += pathSum(root.left, sum, currentPath);
        pathCount += pathSum(root.right, sum, currentPath);

        currentPath.remove(currentPath.size() - 1);
        return pathCount;
    }
}
