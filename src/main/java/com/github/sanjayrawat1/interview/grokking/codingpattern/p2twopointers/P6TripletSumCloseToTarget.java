package com.github.sanjayrawat1.interview.grokking.codingpattern.p2twopointers;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of unsorted numbers and a target number, find a triplet in the array
 * whose sum is as close to the target number as possible, return the sum of the triplet.
 * If there are more than one such triplet, return the sum of the triplet with the smallest sum.
 *
 * @author Sanjay Singh Rawat
 */
public class P6TripletSumCloseToTarget {

    public static void main(String[] args) {
        assertThat(twoPointers(new int[] {-1, 0, 2, 3}, 3)).isEqualTo(2);
        assertThat(twoPointers(new int[] {0, 0, 1, 1, 2, 6}, 5)).isEqualTo(4);
        assertThat(twoPointers(new int[] {-4, -1, 0, 3, 10}, 2)).isEqualTo(2);
        assertThat(twoPointers(new int[] {-2, 0, 1, 2}, 2)).isEqualTo(1);
        assertThat(twoPointers(new int[] {-3, -1, 1, 2}, 1)).isEqualTo(0);
        assertThat(twoPointers(new int[] {-3, 1, 1, 3}, 2)).isEqualTo(1);
    }

    private static int twoPointers(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(arr);
        int smallestDiff = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];
                int targetDiff = targetSum - currentSum;
                if (targetDiff == 0) {
                    // If targetDiff equals 0, that means the sum of our current triplet exactly matches the target sum,
                    // and we return the targetSum immediately as our result.
                    return targetSum;
                }

                // We check if the absolute value of targetDiff is less than the absolute value of smallestDifference (meaning we've found a closer sum),
                // or if it's equal but targetDiff is greater (meaning it's a larger sum that is equally close). If either condition is true, we update
                // smallestDifference with targetDiff.
                if (Math.abs(targetDiff) < Math.abs(smallestDiff)
                        || (Math.abs(targetDiff) == Math.abs(smallestDiff)
                            && targetDiff > smallestDiff)) {
                    smallestDiff = targetDiff;
                }

                if (targetDiff > 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return targetSum - smallestDiff;
    }
}
