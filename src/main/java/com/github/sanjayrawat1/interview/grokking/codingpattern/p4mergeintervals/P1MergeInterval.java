package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Sanjay Singh Rawat
 */
public class P1MergeInterval {

    public static void main(String[] args) {
        assertThat(merge(Arrays.asList(new Interval(1, 3), new Interval(4, 5), new Interval(7, 9))))
                .containsSequence(new Interval(1, 3), new Interval(4, 5), new Interval(7, 9));
        assertThat(merge(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9))))
                .containsSequence(new Interval(1, 5), new Interval(7, 9));
        assertThat(merge(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(5, 9))))
                .containsSequence(new Interval(2, 4), new Interval(5, 9));
        assertThat(merge(Arrays.asList(new Interval(1, 4), new Interval(2, 6), new Interval(3, 5))))
                .containsSequence(new Interval(1, 6));

        assertThat(mergeInPlace(Arrays.asList(new Interval(1, 3), new Interval(4, 5), new Interval(7, 9)))).isEqualTo(2);
        assertThat(mergeInPlace(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)))).isEqualTo(1);
        assertThat(mergeInPlace(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(5, 9)))).isEqualTo(1);
        assertThat(mergeInPlace(Arrays.asList(new Interval(1, 4), new Interval(2, 6), new Interval(3, 5)))).isEqualTo(0);
        System.out.println(mergeInPlace(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(4, 8), new Interval(8, 9))));
    }

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }

        intervals.sort(Comparator.comparingInt(Interval::getStart));
        List<Interval> mergedIntervals = new ArrayList<>();

        int start = intervals.getFirst().getStart();
        int end = intervals.getFirst().getEnd();

        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.getStart() <= end) {
                // overlapping intervals, adjust the end.
                end = Math.max(end, interval.getEnd());
            } else {
                // non-overlapping interval, add the previous interval and reset
                mergedIntervals.add(new Interval(start, end));
                start = interval.getStart();;
                end = interval.getEnd();
            }
        }
        // add the last interval
        mergedIntervals.add(new Interval(start, end));
        return mergedIntervals;
    }

    private static int mergeInPlace(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return 0;
        }

        intervals.sort(Comparator.comparingInt(Interval::getStart));

        // (1, 4), (2, 5), (4, 8), (8, 9)
        // i = 1, idx = 0 => (1, 5), (2, 5), (7, 8), (8, 9)
        // i = 2, idx = 0, 1 => (1, 5), (7, 8), (7, 8), (8, 9)
        // i = 3, idx = 1 => (1, 5), (7, 9), (7, 8), (8, 9)
        int index = 0;
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            Interval previous = intervals.get(i - 1);

            if (current.getStart() <= previous.getEnd()) {
                intervals.get(index).setEnd(Math.max(current.getEnd(), previous.getEnd()));
            } else {
                index++;
                intervals.set(index, current);
            }
        }
        System.out.println(intervals);
        return index;
    }
}
