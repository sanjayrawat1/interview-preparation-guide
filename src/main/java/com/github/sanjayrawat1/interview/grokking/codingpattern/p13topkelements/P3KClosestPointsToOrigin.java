package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * <p>
 * Given an array of points in a 2D plane, find K closest points to the origin.
 *
 * @author Sanjay Singh Rawat
 */
public class P3KClosestPointsToOrigin {

    record Point(int x, int y) {}

    record PointDistance(Point point, int distance) {}

    public static Point[] closestPoints(Point[] points, int k) {
        // The Euclidean distance between (1, 2) and the origin is sqrt(5).
        // The Euclidean distance between (1, 3) and the origin is sqrt(10).
        // Since sqrt(5) < sqrt(10), therefore (1, 2) is closer to the origin.
        Queue<PointDistance> maxHeap = new PriorityQueue<>(Comparator.comparingInt(PointDistance::distance).reversed());
        for (Point point : points) {
            int distance = point.x() * point.x() + point.y() * point.y();
            maxHeap.offer(new PointDistance(point, distance));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        Point[] closestPointsToOrigin = new Point[k];
        int idx = 0;
        while (!maxHeap.isEmpty()) {
            PointDistance pointDistance = maxHeap.poll();
            closestPointsToOrigin[idx] = pointDistance.point();
            idx++;
        }

        return closestPointsToOrigin;
    }

    public static void main(String[] args) {
        assertThat(closestPoints(new Point[]{new Point(1, 3), new Point(3, 4), new Point(2, -1)}, 2))
                .containsExactlyInAnyOrder(new Point(1, 3), new Point(2, -1));
        assertThat(closestPoints(new Point[]{new Point(1, 3), new Point(-2, 2)}, 1))
                .containsExactlyInAnyOrder(new Point(-2, 2));
        assertThat(closestPoints(new Point[]{new Point(3, 3), new Point(5, -1), new Point(-2, 4)}, 2))
                .containsExactlyInAnyOrder(new Point(-2, 4), new Point(3, 3));
    }
}
