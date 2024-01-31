package com.github.sanjayrawat1.interview.grokking.codingpattern.p4mergeintervals;

import java.util.Objects;

/**
 * @author Sanjay Singh Rawat
 */
public class Interval {

    private int start;

    private int end;

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Interval interval)) {
            return false;
        }
        return start == interval.start && end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    public String toString() {
        return "[" + this.start + ", " + this.end + "]";
    }
}
