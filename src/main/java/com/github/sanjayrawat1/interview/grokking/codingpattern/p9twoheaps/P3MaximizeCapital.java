package com.github.sanjayrawat1.interview.grokking.codingpattern.p9twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/ipo/
 * <p>
 * Given a set of investment projects with their respective profits, we need to find the most profitable
 * projects. We are given an initial capital and are allowed to invest only in a fixed number of projects.
 * Our goal is to choose projects that give us the maximum profit. Write a function that returns the maximum
 * total capital after selecting the most profitable projects.
 * <p>
 * We can start an investment project only when we have the required capital. Once a project is selected, we
 * can assume that its profit has become our capital.
 *
 * <li> Example 1
 * <br>
 * Input:
 * <br>
 * Project Capitals = [0,1,2] <br>
 * Project Profits = [1,2,3] <br>
 * Initial Capital = 1 <br>
 * Number of Projects = 2
 * <br>
 * Output: 6
 * <br>
 * Explanation: <br>
 * With initial capital of ‘1’, we will start the second project which will give us profit of ‘2’. <br>
 * Once we selected our first project, our total capital will become 3 (profit + initial capital). <br>
 * With ‘3’ capital, we will select the third project, which will give us ‘3’ profit. <br>
 * After the completion of the two projects, our total capital will be 6 (1+2+3).
 *
 * <li> Example 2
 * <br>
 * Input
 * <br>
 * Project Capitals = [0,1,2,3] <br>
 * Project Profits = [1,2,3,5] <br>
 * Initial Capital = 0 <br>
 * Number of Projects = 3
 * <br>
 * Output: 8
 * <br>
 * Explanation: <br>
 * With ‘0’ capital, we can only select the first project, bringing out capital to 1. <br>
 * Next, we will select the second project, which will bring our capital to 3. <br>
 * Next, we will select the fourth project, giving us a profit of 5. <br>
 * After selecting the three projects, our total capital will be 8 (1+2+5).
 *
 * @author Sanjay Singh Rawat
 */
public class P3MaximizeCapital {

    record CapitalIndexPair(int capital, int index) {}

    /**
     * While selecting projects we have two constraints:
     * <ol>
     *     <li> We can select a project only when we have the required capital.
     *     <li> There is a maximum limit on how many projects we can select. Since we do not have any
     *          constraint on time, we should choose a project, among the projects for which we have
     *          enough capital, which gives us a maximum profit.
     * </ol>
     *
     * While selecting a project, we will do two thing:
     * <ol>
     *     <li> Find all the projects that we can choose with the available capital.
     *     <li> From the list of projects in the 1st step, choose the project that gives us a maximum profit.
     *          We can follow the two heaps approach. Here are the steps of our algorithm:
     *          <ol>
     *              <li> Add all project capitals to a min-heap, so that we can select a project with the
     *                   smallest capital requirement.
     *              <li> Go through the top projects of the min-heap and filter the projects that can be
     *                   completed within out available capital. Insert the profits of all these projects
     *                   into a max-heap, so that we can choose a project with the maximum profit.
     *              <li> Finally, select the top project of the max-heap for investment.
     *              <li> Repeat 2nd and 3rd steps for the required number of projects.
     *          </ol>
     * </ol>
     *
     * <li> Since, at the most, all the projects will be pushed to both the heaps once, the time complexity
     * of our algorithm is O(N log N + K log N), where N is the total number of projects and K is the number
     * of projects we are selecting.
     * <li> The space complexity will be O(N) because we will be storing all the projects in the heaps.
     */
    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        Queue<CapitalIndexPair> minCapitalHeap = new PriorityQueue<>(Comparator.comparingInt(CapitalIndexPair::capital));
        Queue<Integer> maxProfitHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int idx = 0; idx < capital.length; idx++) {
            minCapitalHeap.offer(new CapitalIndexPair(capital[idx], idx));
        }

        int availableCapital = initialCapital;

        for (int idx = 0; idx < numberOfProjects; idx++) {
            while (!minCapitalHeap.isEmpty() && minCapitalHeap.peek().capital() <= availableCapital) {
                CapitalIndexPair capitalIndexPair = minCapitalHeap.poll();
                maxProfitHeap.offer(profits[capitalIndexPair.index()]);
            }

            if (maxProfitHeap.isEmpty()) {
                break;
            }

            availableCapital += maxProfitHeap.poll();
        }

        return availableCapital;
    }

    public static void main(String[] args) {
        assertThat(findMaximumCapital(new int[] {0, 1, 2}, new int[] {1, 2, 3}, 2, 1)).isEqualTo(6);
        assertThat(findMaximumCapital(new int[] {0, 1, 2, 3}, new int[] {1, 2, 3, 5}, 3, 0)).isEqualTo(8);
    }
}
