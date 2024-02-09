package com.github.sanjayrawat1.interview.grokking.codingpattern.p9twoheaps;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-right-interval/
 * <p>
 * Given an array of intervals, find the next interval of each interval. In a list of intervals, for an interval
 * 'i' its next interval 'j' will have the smallest 'start' greater than or equal to the 'end' of 'i'. Write
 * a function to return an array containing indices of the next interval of each input interval. If there is
 * no next interval of a given interval, return -1. It is given that none of the intervals have the same start point.
 *
 * <li> Example 1:
 * <br>
 * Input: Intervals [[2,3], [3,4], [5,6]] <br>
 * Output: [1, 2, -1] <br>
 * Explanation: <br>
 * The next interval of [2,3] is [3,4] having index ‘1’. <br>
 * Similarly, the next interval of [3,4] is [5,6] having index ‘2’. <br>
 * There is no next interval for [5,6] hence we have ‘-1’.
 *
 * <li> Example 2:
 * <br>
 * Input: Intervals [[3,4], [1,5], [4,6]] <br>
 * Output: [2, -1, -1] <br>
 * Explanation: <br>
 * The next interval of [3,4] is [4,6] which has index ‘2’. <br>
 * There is no next interval for [1,5] and [4,6].
 *
 * @author Sanjay Singh Rawat
 */
public class P4NextInterval {

    record Interval(int start, int end) {}

    record IntervalIndexPair(Interval interval, int index) {}

    /**
     * We can push all intervals into two heaps: one heap to sort the intervals on maximum start time (let's
     * call it maxStartHeap) and the other on maximum end time (let's call it maxEndHeap). We can then iterate
     * through all intervals of the maxEndHeap to find their next interval. Our algorithm will have the
     * following steps:
     * <ol>
     *     <li> Take out the top (having highest end) interval from the maxEndHeap to find its next interval.
     *          Let's call this interval topEnd.
     *     <li> Find an interval in the maxStartHeap with the closest start greater than or equal to the end
     *          of topEnd. Since maxStartHeap is sorted by 'start' of intervals, it is easy to find the
     *          interval with the highest 'start'. Let's call this interval topStart.
     *     <li> Add the index of topStart in the result array as the next interval of topEnd. If we can't
     *          find the next interval, add '-1' in the result array.
     *     <li> Put the topStart back in the maxStartHeap, as it could be the next interval of other intervals.
     *     <li> Repeat steps 1-4 until we have no intervals left in maxEndHeap.
     * </ol>
     *
     * <li> The time complexity of our algorithm will be O(NlogN), where N is the total number of intervals.
     * <li> The space complexity will be O(N) because we will be storing all the intervals in the heaps.
     */
    public static int[] findNextInterval(Interval[] intervals) {
        int n = intervals.length;
        Queue<IntervalIndexPair> maxStartHeap = new PriorityQueue<>((o1, o2) -> o2.interval().start() - o1.interval().start());
        Queue<IntervalIndexPair> maxEndHeap = new PriorityQueue<>((o1, o2) -> o2.interval().end() - o1.interval().end());
        int[] result = new int[n];

        for (int idx = 0; idx < n; idx++) {
            Interval interval = intervals[idx];
            maxStartHeap.offer(new IntervalIndexPair(interval, idx));
            maxEndHeap.offer(new IntervalIndexPair(interval, idx));
        }

        // go through all the intervals to find each interval's next interval
        for (int i = 0; i < n; i++) {
            // let's find the next interval of the interval which has the highest 'end'
            IntervalIndexPair topEnd = maxEndHeap.poll();
            result[topEnd.index()] = -1;
            if (!maxStartHeap.isEmpty() && maxStartHeap.peek().interval().start() >= topEnd.interval().end()) {
                IntervalIndexPair topStart = maxStartHeap.poll();
                // find the interval that has the closest 'start'
                while (!maxStartHeap.isEmpty() && maxStartHeap.peek().interval().start() >= topEnd.interval().end()) {
                    topStart = maxStartHeap.poll();
                }
                result[topEnd.index()] = topStart.index();
                // put the interval back as it could be the next interval of other intervals
                maxStartHeap.offer(topStart);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // max start = (5, 2), (3, 1), (2, 0)
        // max end   = (6, 2), (4, 1), (3, 0)
        assertThat(findNextInterval(new Interval[] {new Interval(2, 3), new Interval(3, 4), new Interval(5, 6)}))
                .containsExactly(1, 2, -1);

        // max start = (4, 2), (3, 0), (1, 1)
        // max end   = (6, 2), (5, 1), (4, 0)
        assertThat(findNextInterval(new Interval[] {new Interval(3, 4), new Interval(1, 5), new Interval(4, 6)}))
                .containsExactly(2, -1, -1);
    }
}
