package com.github.sanjayrawat1.interview.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://youtu.be/P6XGSKO2RzI
 * <p>
 * Quick sort is an efficient, recursive divide-and-conquer approach to sorting an array. In this method, a
 * `pivot` value is chosen in the original array. The array is then partitioned into two sub-arrays of values
 * less than and greater than the `pivot` value. We then combine the result of recursively calling the quick
 * sort algorithm on both sub-arrays. This continues until the base case of an empty or single-item array is
 * reached, which we return. The unwinding of the recursive calls return us the sorted array.
 * <p>
 * Quick sort is a very efficient sorting method, providing `O(N log N) performance on average. It is also
 * relatively easy to implement. These attributes make it a popular and useful sorting method.
 *
 * @author Sanjay Singh Rawat
 */
public class S4QuickSort {

    public static void main(String[] args) {
        List<Integer> unsortedList = List.of(1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92);
        List<Integer> sortedList = sort(unsortedList);
        assertThat(sortedList.size()).isEqualTo(unsortedList.size());
        assertThat(sortedList).containsExactly(1, 1, 2, 2, 4, 8, 32, 43, 43, 55, 63, 92, 123, 123, 234, 345, 5643);
    }

    public static List<Integer> sort(List<Integer> array) {
        if (array == null || array.size() <= 1) {
            return array;
        }

        int pivotPoint = array.getLast();
        List<Integer> firstHalf = new ArrayList<>();
        List<Integer> secondHalf = new ArrayList<>();

        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) < pivotPoint) {
                firstHalf.add(array.get(i));
            } else {
                secondHalf.add(array.get(i));
            }
        }

        List<Integer> sortedArray = new ArrayList<>();
        if (firstHalf.size() > 0) {
            List<Integer> sortedFirstHalf = sort(firstHalf);
            sortedArray.addAll(sortedFirstHalf);
        }

        sortedArray.add(pivotPoint);

        if (secondHalf.size() > 0) {
            List<Integer> sortedSecondHalf = sort(secondHalf);
            sortedArray.addAll(sortedSecondHalf);
        }

        return sortedArray;
    }
}
