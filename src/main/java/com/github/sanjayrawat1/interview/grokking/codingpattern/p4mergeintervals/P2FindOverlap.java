package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a set of intervals, find out if any two intervals overlap.
 *
 * @author Sanjay Singh Rawat
 */
public class P2FindOverlap {

    public static void main(String[] args) {
        assertThat(overlaps(Arrays.asList(new Interval(1, 3), new Interval(4, 5), new Interval(7, 9)))).isFalse();
        assertThat(overlaps(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)))).isTrue();
        assertThat(overlaps(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(5, 9)))).isTrue();
        assertThat(overlaps(Arrays.asList(new Interval(1, 4), new Interval(2, 6), new Interval(3, 5)))).isTrue();
        assertThat(overlaps(Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6)))).isFalse();
        assertThat(overlaps(Arrays.asList(new Interval(1, 2)))).isFalse();

        assertThat(overlaps1(Arrays.asList(new Interval(1, 3), new Interval(4, 5), new Interval(7, 9)))).isFalse();
        assertThat(overlaps1(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)))).isTrue();
        assertThat(overlaps1(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(5, 9)))).isTrue();
        assertThat(overlaps1(Arrays.asList(new Interval(1, 4), new Interval(2, 6), new Interval(3, 5)))).isTrue();
        assertThat(overlaps1(Arrays.asList(new Interval(1, 2), new Interval(3, 4), new Interval(5, 6)))).isFalse();
        assertThat(overlaps1(Arrays.asList(new Interval(1, 2)))).isFalse();
    }

    private static boolean overlaps(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return false;
        }

        intervals.sort(Comparator.comparingInt(Interval::getStart));

        int end = intervals.getFirst().getEnd();

        for (int i = 1; i < intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);
            if (currentInterval.getStart() <= end) {
                return true;
            } else {
                end = currentInterval.getEnd();
            }
        }
        return false;
    }

    private static boolean overlaps1(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return false;
        }

        intervals.sort(Comparator.comparingInt(Interval::getStart));

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            Interval previous = intervals.get(i - 1);

            if (current.getStart() <= previous.getEnd()) {
                return true;
            }
        }

        return false;
    }
}
