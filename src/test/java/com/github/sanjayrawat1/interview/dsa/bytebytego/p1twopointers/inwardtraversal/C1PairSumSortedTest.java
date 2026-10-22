package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author sanjayrawat1
 */
class C1PairSumSortedTest {

    private C1PairSumSorted pairSumSorted;

    @BeforeEach
    void setup() {
        this.pairSumSorted = new C1PairSumSorted();
    }

    @Test
    public void givenSortedArray_whenPairSumBruteForce_thenReturnMatchingPair() {
        List<Integer> nums = List.of(-5, -2, 3, 4, 6);
        int target = 7;
        List<Integer> result = pairSumSorted.pairSumBruteForce(nums, target);
        assertThat(result).isEqualTo(List.of(2, 3));

        nums = List.of(1, 1, 1);
        target = 2;
        result = pairSumSorted.pairSumBruteForce(nums, target);
        assertThat(result).isEqualTo(List.of(0, 1));
    }

    @Test
    public void givenInvalidPair_whenPairSumBruteForce_thenReturnEmpty() {
        List<Integer> nums = List.of(2, 4);
        int target = 5;
        List<Integer> result = pairSumSorted.pairSumBruteForce(nums, target);
        assertThat(result).isEmpty();

        nums = List.of(1, 1, 1);
        target = 1;
        result = pairSumSorted.pairSumBruteForce(nums, target);
        assertThat(result).isEmpty();
    }

    @Test
    public void givenSortedArray_whenPairWithTwoPointer_thenReturnMatchingPair() {
        List<Integer> nums = List.of(-5, -2, 3, 4, 6);
        int target = 7;
        List<Integer> result = pairSumSorted.pairSumTwoPointer(nums, target);
        assertThat(result).isEqualTo(List.of(2, 3));

        nums = List.of(1, 1, 1);
        target = 2;
        result = pairSumSorted.pairSumTwoPointer(nums, target);
        assertThat(result).isEqualTo(List.of(0, 2));
    }

    @Test
    public void givenInvalidPair_whenPairSumTwoPointer_thenReturnEmpty() {
        List<Integer> nums = List.of();
        int target = 0;
        List<Integer> result = pairSumSorted.pairSumTwoPointer(nums, target);
        assertThat(result).isEmpty();

        nums = List.of(1);
        target = 1;
        result = pairSumSorted.pairSumTwoPointer(nums, target);
        assertThat(result).isEmpty();
    }
}