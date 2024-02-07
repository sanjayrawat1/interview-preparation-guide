package com.github.sanjayrawat1.interview.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Merge sort uses a divide-and-conquer, recursive methodology to sort an array. It takes advantage of the
 * fact that it is relatively easy to sort two arrays as long as each is sorted in the first place. But we
 * will start with only one array as input, so how do we get to two sorted arrays from that? Well, we can
 * recursively divide the original input in two until we reach the base case of an array with one item. A
 * single-item array is naturally sorted, so we can start combining. This combination will unwind the recursive
 * calls that split the original array, eventually producing a final sorted array of all the elements.
 * <p>
 * The steps of merge sort are:
 * <li> Recursively split the input array in half until a sub-array with only one element is produced.
 * <li> Merge each sorted sub-array together to produce the final sorted array.
 *
 * @author Sanjay Singh Rawat
 */
public class S5MergeSort {

    public static void main(String[] args) {
        List<Integer> unsortedList = List.of(1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92);
        List<Integer> sortedList = sort(unsortedList);
        assertThat(sortedList.size()).isEqualTo(unsortedList.size());
        assertThat(sortedList).containsExactly(1, 1, 2, 2, 4, 8, 32, 43, 43, 55, 63, 92, 123, 123, 234, 345, 5643);
    }

    public static List<Integer> sort(List<Integer> list) {
        // base case: Array of length 1 is sorted, so we return the same list back
        if (list == null || list.size() <= 1) {
            return list;
        }

        // break down the list to half from mid into firstHalf and secondHalf
        int mid = list.size() / 2;
        List<Integer> firstHalf = sort(list.subList(0, mid));
        List<Integer> secondHalf = sort(list.subList(mid, list.size()));

        return merge(firstHalf, secondHalf);
    }

    private static List<Integer> merge(List<Integer> firstHalf, List<Integer> secondHalf) {
        int i = 0;
        int j = 0;
        List<Integer> mergedList = new ArrayList<>();
        while (i < firstHalf.size() && j < secondHalf.size()) {
            if (firstHalf.get(i) > secondHalf.get(j)) {
                mergedList.add(secondHalf.get(j));
                j++;
            } else {
                mergedList.add(firstHalf.get(i));
                i++;
            }
        }

        while (i < firstHalf.size()) {
            mergedList.add(firstHalf.get(i));
            i++;
        }

        while (j < secondHalf.size()) {
            mergedList.add(secondHalf.get(j));
            j++;
        }

        return mergedList;
    }
}
