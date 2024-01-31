package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/meeting-rooms/
 * <br>
 * Given an array of intervals representing N appointments, find out if a person can <b>attend all the appointments.</b>
 *
 * @author Sanjay Singh Rawat
 */
public class P5ConflictingAppointment {

    public static void main(String[] args) {
        assertThat(canAttendAllAppointments(Arrays.asList(new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)))).isFalse();
        assertThat(canAttendAllAppointments(Arrays.asList(new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)))).isTrue();
        assertThat(canAttendAllAppointments(Arrays.asList(new Interval(4, 5), new Interval(2, 3), new Interval(3, 6)))).isFalse();


        assertThat(whatAreTheConflicts(List.of(new Interval(4, 5), new Interval(2, 3), new Interval(3, 6), new Interval(5, 7), new Interval(7, 8))))
                .contains(List.of(new Interval(4, 5), new Interval(3, 6)), List.of(new Interval(3, 6), new Interval(5, 7)));
        assertThat(whatAreTheConflicts(List.of(new Interval(1, 5), new Interval(5, 8), new Interval(4, 5), new Interval(6, 10), new Interval(10, 30))))
                .contains(List.of(new Interval(1, 5), new Interval(4, 5)), List.of(new Interval(5, 8), new Interval(6, 10)));
    }

    private static boolean canAttendAllAppointments(List<Interval> appointments) {
        if (appointments.size() < 2) {
            return true;
        }

        appointments.sort(Comparator.comparingInt(Interval::getStart));

        for (int idx = 1; idx < appointments.size(); idx++) {
            Interval current = appointments.get(idx);
            Interval previous = appointments.get(idx - 1);

            if (current.getStart() < previous.getEnd()) {
                return false;
            }
        }
        return true;
    }

    private static List<List<Interval>> whatAreTheConflicts(List<Interval> appointments) {
        List<List<Interval>> conflicts = new ArrayList<>();

        for (int i = 0; i < appointments.size(); i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (isConflicting(appointments.get(i), appointments.get(j))) {
                    conflicts.add(List.of(appointments.get(i), appointments.get(j)));
                }
            }
        }
        return conflicts;
    }

    private static boolean isConflicting(Interval first, Interval second) {
        boolean firstOverlapsSecond = second.getStart() > first.getStart() && second.getStart() < first.getEnd();

        boolean secondOverlapsFirst = first.getStart() > second.getStart() && first.getStart() < second.getEnd();

        return firstOverlapsSecond || secondOverlapsFirst;
    }
}
