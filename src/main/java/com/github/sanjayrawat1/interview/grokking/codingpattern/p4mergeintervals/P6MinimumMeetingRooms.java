package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a list of intervals representing the start and endTime of N meetings, find the minimum number of rooms required to hold all
 * the meetings.
 *
 * @author Sanjay Singh Rawat
 */
public class P6MinimumMeetingRooms {

    public static void main(String[] args) {
        assertThat(minMeetingRooms(Arrays.asList())).isEqualTo(0);
        assertThat(minMeetingRooms(Arrays.asList(new Interval(1, 4)))).isEqualTo(1);
        assertThat(minMeetingRooms(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)))).isEqualTo(2);
        assertThat(minMeetingRooms(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)))).isEqualTo(1);
        assertThat(minMeetingRooms(Arrays.asList(new Interval(1, 4), new Interval(2, 3), new Interval(3, 6)))).isEqualTo(2);
        assertThat(minMeetingRooms(Arrays.asList(new Interval(4, 5), new Interval(2, 3), new Interval(2, 4), new Interval(3, 5)))).isEqualTo(2);
    }

    /**
     * <ol>
     *     <li> Sort all the meetings on their startTime.
     *     <li> Create a min-heap to store all the active meetings. This min-heap will also be used to find the active meetings with the
     *     smallest endTime.
     *     <li> Iterate through all the meetings one by one to add them in the min-heap. Let's say we are trying to schedule the meeting m1.
     *     <li> Since the min-heap contains all the active meetings, so before scheduling m1 we can remove all meetings from the heap that
     *     have ended before m1, i.e., remove all meetings from the heap that have an endTime smaller than or equal to the startTime of m1.
     *     <li> Now add m1 to the heap.
     *     <li> The heap will always have all the overlapping meetings, so we will need rooms for all of them. Keep a counter to remember
     *     the maximum size of the heap at any time which will be the minimum number of rooms needed.
     * </ol>
     */
    private static int minMeetingRooms(List<Interval> meetings) {
        if (meetings == null || meetings.size() == 0) {
            return 0;
        }
        meetings.sort(Comparator.comparingInt(Interval::getStart));
        int meetingRoomsRequired = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int idx = 0; idx < meetings.size(); idx++) {
            Interval currentMeetingInterval = meetings.get(idx);
            minHeap.offer(currentMeetingInterval.getEnd());

            if (currentMeetingInterval.getStart() < minHeap.peek()) {
                meetingRoomsRequired++;
            } else {
                minHeap.poll();
            }
        }

        return meetingRoomsRequired;
    }
}
