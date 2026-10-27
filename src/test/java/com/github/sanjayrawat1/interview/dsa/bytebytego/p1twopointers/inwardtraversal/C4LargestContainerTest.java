package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author sanjayrawat1
 */
class C4LargestContainerTest {

    private C4LargestContainer largestContainer;

    @BeforeEach
    void setup() {
        this.largestContainer = new C4LargestContainer();
    }

    @Test
    public void givenHeightsArray_whenLargestContainerBruteForce_thenReturnMaxWater() {
        List<Integer> heights = List.of(2, 7, 8, 3, 7, 6);
        int expected = 24;
        int actual = largestContainer.largestContainerBruteForce(heights);
        assertEquals(expected, actual);

        heights = List.of(1);
        expected = 0;
        actual = largestContainer.largestContainerBruteForce(heights);
        assertEquals(expected, actual);

        heights = List.of(0, 1, 0);
        expected = 0;
        actual = largestContainer.largestContainerBruteForce(heights);
        assertEquals(expected, actual);
    }

    @Test
    public void givenHeightsArrayAsEmpty_whenLargestContainerBruteForce_thenReturnMaxWater() {
        List<Integer> heights = List.of();
        int expected = 0;
        int actual = largestContainer.largestContainerBruteForce(heights);
        assertEquals(expected, actual);
    }

    @Test
    public void givenHeightsArray_whenLargestContainer_thenReturnMaxWater() {
        List<Integer> heights = List.of(2, 7, 8, 3, 7, 6);
        int expected = 24;
        int actual = largestContainer.largestContainer(heights);
        assertEquals(expected, actual);

        heights = List.of(1);
        expected = 0;
        actual = largestContainer.largestContainer(heights);
        assertEquals(expected, actual);

        heights = List.of(0, 1, 0);
        expected = 0;
        actual = largestContainer.largestContainer(heights);
        assertEquals(expected, actual);
    }

    @Test
    public void givenHeightsArrayAsEmpty_whenLargestContainer_thenReturnMaxWater() {
        List<Integer> heights = List.of();
        int expected = 0;
        int actual = largestContainer.largestContainer(heights);
        assertEquals(expected, actual);
    }
}