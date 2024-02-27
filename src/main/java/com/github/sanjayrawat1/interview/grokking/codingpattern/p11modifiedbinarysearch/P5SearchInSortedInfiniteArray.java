package com.github.sanjayrawat1.interview.grokking.codingpattern.p11modifiedbinarysearch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 * <p>
 * Given an infinite sorted array (or an array with unknown size), find if a given number 'key' is present
 * in the array. Write a function to return the index of the key if it is present in the array, otherwise
 * return -1.
 * <p>
 * Since it is not possible to define an array with infinite (unknown) size, you will be provided with an
 * interface `ArrayReader` to read elements of the array. `ArrayReader.get(index)` will return the number
 * at `index`; if array's size is smaller than the index, it will return `Integer.MAX_VALUE`.
 *
 * @author Sanjay Singh Rawat
 */
public class P5SearchInSortedInfiniteArray {

    private static class ArrayReader {
        int[] arr;

        public ArrayReader(int[] arr) {
            this.arr = arr;
        }

        int get(int index) {
            if (index >= arr.length) {
                return Integer.MAX_VALUE;
            }
            return arr[index];
        }
    }

    /**
     * The only issue with applying binary search in this problem is that we don't know the bounds of the
     * array. To handle this situation, we will first find the proper bounds of the array where we can
     * perform a binary search.
     * <p>
     * An efficient way to find the proper bounds is to start at the beginning of the array with the bound's
     * size as 1 and exponentially increase the bound's size (i.e., double it) until we find the bounds that
     * can have the key.
     *
     * <li> There are two parts of the algorithm. In the first part, we keep increasing the bound’s size
     *      exponentially (double it every time) while searching for the proper bounds. Therefore, this step
     *      will take O(log N) assuming that the array will have maximum N numbers. In the second step,
     *      we perform the binary search which will take O(log N), so the overall time complexity of our
     *      algorithm will be O(log N + log N) which is asymptotically equivalent to O(log N).
     * <li> The algorithm runs in constant space O(1).
     */
    public static int searchInInfiniteArray(ArrayReader reader, int key) {
        int start = 0;
        int end = 1;

        while (reader.get(end) < key) {
            int newStart = end + 1;
            end += (end - start + 1) * 2;
            start = newStart;
        }
        return binarySearch(reader, key, start, end);
    }

    private static int binarySearch(ArrayReader reader, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == reader.get(mid)) {
                return mid;
            }

            if (key > reader.get(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[] {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
        assertThat(searchInInfiniteArray(reader, 11)).isEqualTo(-1);
        assertThat(searchInInfiniteArray(reader, 16)).isEqualTo(6);

        reader = new ArrayReader(new int[] {1, 3, 8, 10, 15});
        assertThat(searchInInfiniteArray(reader, 15)).isEqualTo(4);
        assertThat(searchInInfiniteArray(reader, 200)).isEqualTo(-1);
    }
}
