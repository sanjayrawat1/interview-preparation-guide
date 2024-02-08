package com.github.sanjayrawat1.interview.grokking.codingpattern.p9twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 * <p>
 * Design a class to calculate the median of a number stream. The class should have the following two
 * methods:
 * <ol>
 *     <li> `insertNum(int num)`: stores the number in the class.
 *     <li> `findMedian()`: return the median of all numbers inserted in the class if the count of numbers
 *          inserted in the class is even, the median will be the average of the middle two numbers.
 * </ol>
 * Assume 'x' is the median of a list. This means that half of the numbers in the list will be smaller than
 * (or equal to) 'x' and half will be greater than (or equal to) 'x'. This leads us to the approach where we
 * can divide the list into two halves: one half to store all the smaller numbers and one half to store the
 * larger numbers. The median of all the numbers will either the largest number in the smaller half or the
 * smallest in the larger half. If the total number of elements is even, the median will be the average of
 * these two numbers.
 * <p>
 * The best data structure to find the smallest or largest number among a list of numbers is a Heap. Let's
 * see how we can use a heap to find a better algorithm:
 * <li> We can store the first half of numbers in a Max Heap, as we are interested in knowing the largest
 * number in the first half.
 * <li> We can store the second half of numbers in a Min Heap, as we are interested in knowing the smallest
 * number in the second half.
 * <li> Inserting a number in a heap will take O(log N).
 * <li> At any time, the median of the current list of numbers can be calculated from the top elements of
 * the two heaps.
 *
 * @author Sanjay Singh Rawat
 */
public class P1FindMedianOfNumberStream {

    private static Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    private static Queue<Integer> minHeap = new PriorityQueue<>();

    /**
     * We can insert a number in the Max Heap (i.e. first half) if the number is smaller than the top (largest)
     * number of the heap. After every insertion, we will balance the number of elements in both heaps, so
     * that they have an equal number of elements. If the count of numbers is odd, let’s decide to have more
     * numbers in max-heap than the Min Heap.
     */
    public static void insertNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balance();
    }

    private static void balance() {
        // either both the heaps will have equal number of elements
        // or maxHeap will have one or more elements than minHeap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            // because maxHeap will have one more element than the minHeap
            return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            // we have an even number of elements so take the average of the middle two elements
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        insertNum(3);
        insertNum(1);
        assertThat(findMedian()).isEqualTo(2);

        insertNum(5);
        assertThat(findMedian()).isEqualTo(3);

        insertNum(4);
        assertThat(findMedian()).isEqualTo(3.5);
    }
}
