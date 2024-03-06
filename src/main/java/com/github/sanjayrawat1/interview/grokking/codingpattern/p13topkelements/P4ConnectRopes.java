package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * <p>
 * Given `N` ropes with different lengths, we need to connect these ropes into one big rope with minimum
 * cost. The cost of connecting two ropes is equal to the sum of their lengths.
 *
 * @author Sanjay Singh Rawat
 */
public class P4ConnectRopes {

    /**
     * We can use a greedy approach, each time choosing the shortest two ropes to connect, which ensures the
     * minimum cost of connection.
     * <br>
     * Therefore, we can use a min heap to maintain the current rope lengths. Each time, we take out two ropes
     * from the min heap to connect, then put the connected ropes back into the min heap, until there is only
     * one rope left in the min heap.
     * <li> Given N ropes, the space complexity will be O(N log N).
     * <li> The space complexity will be O(N) .
     */
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int rope : ropeLengths) {
            minHeap.offer(rope);
        }

        int totalCost = 0;

        while (minHeap.size() > 1) {
            int firstRope = minHeap.poll();
            int secondRope = minHeap.poll();
            int currentCost = firstRope + secondRope;
            totalCost += currentCost;
            minHeap.offer(currentCost);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        assertThat(minimumCostToConnectRopes(new int[]{1, 3, 11, 5})).isEqualTo(33);
        assertThat(minimumCostToConnectRopes(new int[]{3, 4, 5, 6})).isEqualTo(36);
        assertThat(minimumCostToConnectRopes(new int[]{1, 3, 11, 5, 2})).isEqualTo(42);
    }
}
