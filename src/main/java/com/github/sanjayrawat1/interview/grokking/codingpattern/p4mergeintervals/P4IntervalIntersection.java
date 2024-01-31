package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * <br>
 * Given two lists of intervals, find the intersection of these two lists. Each list consists of <b>disjoint intervals
 * sorted on their startTime.</b>
 *
 * @author Sanjay Singh Rawat
 */
public class P4IntervalIntersection {

    public static void main(String[] args) {
        assertThat(findIntersection(List.of(new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)),
                                    List.of(new Interval(2, 3), new Interval(5, 7))))
                .containsSequence(new Interval(2, 3), new Interval(5, 6), new Interval(7, 7));
        assertThat(findIntersection(List.of(new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)),
                                    List.of(new Interval(5, 10))))
                .containsSequence(new Interval(5, 7), new Interval(9, 10));
    }

    private static List<Interval> findIntersection(List<Interval> firstIntervals, List<Interval> secondIntervals) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < firstIntervals.size() && j < secondIntervals.size()) {
            Interval firstInterval = firstIntervals.get(i);
            Interval secondInterval = secondIntervals.get(j);
            // check if intervals overlap and secondIntervals start time lies within the other firstIntervals
            boolean firstOverlapsSecond = secondInterval.getStart() >= firstInterval.getStart()
                    && secondInterval.getStart() <= firstInterval.getEnd();

            // check if intervals overlap and firstIntervals start time lies within the other secondInterval
            boolean secondOverlapsFirst = firstInterval.getStart() >= secondInterval.getStart()
                    && firstInterval.getStart() <= secondInterval.getEnd();

            // store the intersection part
            if (firstOverlapsSecond || secondOverlapsFirst) {
                int start = Math.max(firstInterval.getStart(), secondInterval.getStart());
                int end = Math.min(firstInterval.getEnd(), secondInterval.getEnd());
                result.add(new Interval(start, end));
            }

            // move next from the interval which is finishing first
            if (firstInterval.getEnd() < secondInterval.getEnd()) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
