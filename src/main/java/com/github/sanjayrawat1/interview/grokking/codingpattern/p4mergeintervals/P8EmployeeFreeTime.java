package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * For 'K' employees, we are given a list of intervals representing the working hours of each employee.
 * Our goal is to find out if there is a <b>free interval that is common for all employees.</b>
 * You can assume that each list of employee working hours is sorted on the startTime.
 * <br>
 * Input: Employee Working Hours=[[[1,3], [9,12]], [[2,4]], [[6,8]]]
 * Output: [4,6], [8,9]
 *
 * @author Sanjay Singh Rawat
 */
public class P8EmployeeFreeTime {

    public static void main(String[] args) {
        assertThat(employeeFreeTime(List.of(List.of(new Interval(1, 3), new Interval(5, 6)), List.of(new Interval(2, 3), new Interval(6, 8)))))
                .containsSequence(new Interval(3, 5));
        assertThat(employeeFreeTime(List.of(List.of(new Interval(1, 3), new Interval(9, 12)), List.of(new Interval(2, 4)), List.of(new Interval(6, 8)))))
                .containsSequence(new Interval(4, 6), new Interval(8, 9));
        assertThat(employeeFreeTime(List.of(List.of(new Interval(1, 3)), List.of(new Interval(2, 4)), List.of(new Interval(3, 5), new Interval(7, 9)))))
                .containsSequence(new Interval(5, 7));

        assertThat(employeeFreeTimeMinHeap(List.of(List.of(new Interval(1, 3), new Interval(5, 6)), List.of(new Interval(2, 3), new Interval(6, 8)))))
                .containsSequence(new Interval(3, 5));
        assertThat(employeeFreeTimeMinHeap(List.of(List.of(new Interval(1, 3), new Interval(9, 12)), List.of(new Interval(2, 4)), List.of(new Interval(6, 8)))))
                .containsSequence(new Interval(4, 6), new Interval(8, 9));
        assertThat(employeeFreeTimeMinHeap(List.of(List.of(new Interval(1, 3)), List.of(new Interval(2, 4)), List.of(new Interval(3, 5), new Interval(7, 9)))))
                .containsSequence(new Interval(5, 7));
    }

    private static List<Interval> employeeFreeTime(List<List<Interval>> schedules) {
        List<Interval> freeTime = new ArrayList<>();

        // O(N), N is the total number of intervals
        List<Interval> allTime = new ArrayList<>();
        for (List<Interval> schedule : schedules) {
            allTime.addAll(schedule);
        }

        // O(N log N)
        allTime.sort(Comparator.comparingInt(Interval::getStart));

        // O(N)
//        int end = allTime.get(0).getEnd();
        Interval previous = allTime.getFirst();
        for (int i = 1; i < allTime.size(); i++) {
            Interval current = allTime.get(i);
            if (current.getStart() > previous.getEnd()) {
                freeTime.add(new Interval(previous.getEnd(), current.getStart()));
            }
//            end = Math.max(end, current.getEnd());
            previous = previous.getEnd() > current.getEnd() ? previous : current;
        }

        return freeTime;
    }

    /**
     * Can we find a better solution?
     * <br>
     * One fact that we are not utilizing is that each employee list is individually sorted!
     * <br>
     * How about we take the first interval of each employee and insert it in a Min-Heap.
     * This Min-Heap can always give us the interval with the smallest startTime.
     * Once we have the smallest start-time interval, we can then compare it with the next smallest
     * start-time interval (again from the Heap) to find the gap.
     * This interval comparison is similar to what we suggested in the previous approach.
     * <br>
     * Whenever we take an interval out of the Min-Heap, we can insert the same employee’s next interval.
     * This also means that we need to know which interval belongs to which employee.
     * <br>
     * <li> The above algorithm’s time complexity is O(N*logK), where N is the total number of intervals, and K is the total number of employees.
     * This is because we are iterating through the intervals only once (which will take O(N)), and every time we process an interval,
     * we remove (and can insert) one interval in the Min-Heap, which will take O(logK).
     * At any time, the heap will not have more than K elements.
     *
     * <li> The space complexity of the above algorithm will be O(K) as at any time, the heap will not have more than K elements.
     */
    public static List<Interval> employeeFreeTimeMinHeap(List<List<Interval>> schedules) {
        if (schedules == null) {
            return new ArrayList<>();
        }

        List<Interval> result = new ArrayList<>();
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>();

        // insert the first interval of each employee to the queue
        for (int i = 0; i < schedules.size(); i++) {
            minHeap.add(new EmployeeInterval(schedules.get(i).get(0), i, 0));
        }

        EmployeeInterval previous = minHeap.peek();
        while (!minHeap.isEmpty()) {
            EmployeeInterval current = minHeap.remove();
            // if previousInterval is not overlapping with the next interval, insert a free interval
            if (current.interval.getStart() > previous.interval.getEnd()) {
                result.add(new Interval(previous.interval.getEnd(), current.interval.getStart()));
                previous = current;
            } else {
                // overlapping intervals, update the prev if needed
//                if (previous.interval.getEnd() < current.interval.getEnd()) {
//                    previous = current;
//                }
                previous = previous.interval.getEnd() > current.interval.getEnd() ? previous : current;
            }

            // if there are more intervals available for the same employee, add their next interval
            List<Interval> schedule = schedules.get(previous.employeeIndex);
            if (schedule.size() > previous.intervalIndex + 1) {
                minHeap.add(new EmployeeInterval(schedule.get(previous.intervalIndex + 1), previous.employeeIndex, previous.intervalIndex + 1));
            }
        }
        return result;
    }

    static class EmployeeInterval implements Comparable<EmployeeInterval> {
        public Interval interval;
        public int employeeIndex;
        public int intervalIndex;

        public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
            this.interval = interval;
            this.employeeIndex = employeeIndex;
            this.intervalIndex = intervalIndex;
        }

        @Override
        public int compareTo(EmployeeInterval other) {
            return this.interval.getStart() - other.interval.getStart();
        }
    }
}
