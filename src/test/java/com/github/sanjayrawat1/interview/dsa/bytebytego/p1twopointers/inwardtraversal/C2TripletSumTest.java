package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author sanjayrawat1
 */
class C2TripletSumTest {

    private C2TripletSum tripletSum;

    @BeforeEach
    void setup() {
        this.tripletSum = new C2TripletSum();
    }

    @Test
    public void givenUnsortedArray_whenTripletSumBruteForce_thenReturnTriplets() {
        List<Integer> nums = List.of(0, -1, 2, -3, 1);
        List<List<Integer>> triplets = tripletSum.tripletSumBruteForce(nums);
        assertThat(triplets).isNotEmpty();
        assertThat(triplets).hasSize(2);
        assertThat(triplets.getFirst()).isEqualTo(List.of(-3, 1, 2));
        assertThat(triplets.getLast()).isEqualTo(List.of(-1, 0, 1));

        triplets = tripletSum.tripletSumBruteForce(List.of(0, 0, 0));
        assertThat(triplets).isNotEmpty();
        assertThat(triplets).hasSize(1);
        assertThat(triplets.getFirst()).isEqualTo(List.of(0, 0, 0));
    }

    @Test
    public void givenUnsortedArrayWithNoTriplets_whenTripletSumBruteForce_thenReturnEmpty() {
        List<Integer> nums = List.of();
        List<List<Integer>> triplets = tripletSum.tripletSumBruteForce(nums);
        assertThat(triplets).isEmpty();

        triplets = tripletSum.tripletSumBruteForce(List.of(1, -1));
        assertThat(triplets).isEmpty();
    }

    @Test
    public void givenUnsortedArray_whenTripletSum_thenReturnTriplets() {
        List<Integer> nums = new ArrayList<>(List.of(0, -1, 2, -3, 1));
        List<List<Integer>> triplets = tripletSum.tripletSum(nums);
        assertThat(triplets).isNotEmpty();
        assertThat(triplets).hasSize(2);
        assertThat(triplets.getFirst()).isEqualTo(List.of(-3, 1, 2));
        assertThat(triplets.getLast()).isEqualTo(List.of(-1, 0, 1));

        nums = new ArrayList<>(List.of(0, 0, 0));
        triplets = tripletSum.tripletSum(nums);
        assertThat(triplets).isNotEmpty();
        assertThat(triplets).hasSize(1);
        assertThat(triplets.getLast()).isEqualTo(List.of(0, 0, 0));
    }

    @Test
    public void givenUnsortedArrayWithNoTriplets_whenTripletSum_thenReturnEmpty () {
        List<Integer> nums = new ArrayList<>(List.of(1, 0, 1));
        List<List<Integer>> triplets = tripletSum.tripletSum(nums);
        assertThat(triplets).isEmpty();

        triplets = tripletSum.tripletSum(new ArrayList<>());
        assertThat(triplets).isEmpty();
    }
}