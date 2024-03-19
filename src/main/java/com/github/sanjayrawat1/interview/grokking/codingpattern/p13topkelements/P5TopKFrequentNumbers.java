package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-elements/">top-k-frequent-elements</a>
 * <p>
 * Given an unsorted array of numbers, find the top `K` frequently occurring numbers. If two numbers have
 * the same frequency then the number with a larger value should be given preference
 *
 * @author Sanjay Singh Rawat
 */
public class P5TopKFrequentNumbers {

    record Pair(int number, int frequency) {}

    /**
     * <ol>
     *     <li> Create a hash map to store the frequency of each element.
     *     <li> Iterate through the array’s elements and update each element’s frequency in the hash map.
     *     <li> Create a min-heap to contain elements that have min frequency and min value at top.
     *     <li> For each element in the hash map, insert a key-value pair (number-frequency) into the heap.
     *     <li> If the heap size exceeds k, remove the minimum element from the heap.
     *     <li> After processing all elements in the array, the heap contains the k elements with the
     *          highest frequency.
     * </ol>
     */
    public static List<Integer> findKFrequentNumbers(int[] nums, int k) {
        Map<Integer, Integer> numbersFrequency = new HashMap<>();
        for (int num : nums) {
            numbersFrequency.put(num, numbersFrequency.getOrDefault(num, 0) + 1);
        }

        Queue<Pair> minFrequencyAndMinNumberHeap = new PriorityQueue<>(Comparator.comparingInt(Pair::frequency)
                .thenComparing(Pair::number));

        numbersFrequency.forEach((number, frequency) -> {
            minFrequencyAndMinNumberHeap.offer(new Pair(number, frequency));
            if (minFrequencyAndMinNumberHeap.size() > k) {
                minFrequencyAndMinNumberHeap.poll();
            }
        });

        List<Integer> mostFrequentNumbers = new ArrayList<>();
        while (!minFrequencyAndMinNumberHeap.isEmpty()) {
            mostFrequentNumbers.add(minFrequencyAndMinNumberHeap.poll().number);
        }

        return mostFrequentNumbers;
    }

    public static void main(String[] args) {
        assertThat(findKFrequentNumbers(new int[] {1, 3, 5, 12, 11, 12, 11}, 2)).containsExactlyInAnyOrder(11, 12);
        assertThat(findKFrequentNumbers(new int[] {5, 12, 11, 3, 11}, 2)).containsExactlyInAnyOrder(11, 12);
        assertThat(findKFrequentNumbers(new int[] {1, 1, 1, 2, 2, 4, 5, 5}, 3)).containsExactlyInAnyOrder(1, 2, 5);
        assertThat(findKFrequentNumbers(new int[] {1, 1, 1, 2, 2, 4, 5, 5}, 2)).containsExactlyInAnyOrder(1, 5);
        assertThat(findKFrequentNumbers(new int[] {1, 1, 2, 4, 5, 5}, 2)).containsExactlyInAnyOrder(1, 5);
    }
}
