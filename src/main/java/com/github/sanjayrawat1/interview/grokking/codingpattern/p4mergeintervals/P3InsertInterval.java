package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/insert-interval/
 * <br>
 * Given a list of non-overlapping intervals sorted by their start time, <b>insert a given interval at the correct position</b>
 * and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
 *
 * @author Sanjay Singh Rawat
 */
public class P3InsertInterval {

    public static void main(String[] args) {
        assertThat(insertInterval(Arrays.asList(new Interval(1, 3), new Interval(5, 7), new Interval(8, 12)), new Interval(4, 6)))
                .containsSequence(new Interval(1, 3), new Interval(4, 7), new Interval(8, 12));
        assertThat(insertInterval(Arrays.asList(new Interval(1, 3), new Interval(5, 7), new Interval(8, 12)), new Interval(4, 10)))
                .containsSequence(new Interval(1, 3), new Interval(4, 12));
        assertThat(insertInterval(Arrays.asList(new Interval(2, 3), new Interval(5, 7)), new Interval(1, 4)))
                .containsSequence(new Interval(1, 4), new Interval(5, 7));
    }

    /**
     * If the given list was not sorted, we could have simply appended the new interval to it and merged the intervals after sorting
     * by start time. But since the given list is sorted, we should try to come up with a solution better than O(N log N).
     * <br>
     * When inserting a new interval in a sorted list, we need to first find the correct index where the new interval can be placed.
     * In other words, we need to skip all the intervals which end before the start of the new interval.
     */
    private static List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() < 2) {
            return intervals;
        }

        List<Interval> mergedIntervals = new ArrayList<>();

        int idx = 0;
        // skip and add to output all intervals that come before the newInterval
        while (idx < intervals.size() && intervals.get(idx).getEnd() < newInterval.getStart()) {
            mergedIntervals.add(intervals.get(idx));
            idx++;
        }

        // merge all intervals that overlap with newInterval
        while (idx < intervals.size() && intervals.get(idx).getStart() <= newInterval.getEnd()) {
            int start = Math.min(intervals.get(idx).getStart(), newInterval.getStart());
            int end = Math.max(intervals.get(idx).getEnd(), newInterval.getEnd());
            newInterval.setStart(start);
            newInterval.setEnd(end);
            idx++;
        }

        // insert the newInterval
        mergedIntervals.add(newInterval);

        // add all the remaining intervals to the output
        while (idx < intervals.size()) {
            mergedIntervals.add(intervals.get(idx));
            idx++;
        }

        return mergedIntervals;
    }
}
