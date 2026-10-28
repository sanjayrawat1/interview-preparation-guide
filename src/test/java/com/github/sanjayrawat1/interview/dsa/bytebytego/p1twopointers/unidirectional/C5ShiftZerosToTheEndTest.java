package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.unidirectional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author sanjayrawat1
 */
class C5ShiftZerosToTheEndTest {

    private C5ShiftZerosToTheEnd shiftZerosToTheEnd;

    @BeforeEach
    void setup() {
        this.shiftZerosToTheEnd = new C5ShiftZerosToTheEnd();
    }

    @Test
    public void givenAnArray_whenShiftZerosToTheEndNaive_thenShiftZerosToEndUsingTempArray() {
        List<Integer> nums = new ArrayList<>(List.of(0, 1, 0, 3, 2));
        List<Integer> expected = List.of(1, 3, 2, 0, 0);
        shiftZerosToTheEnd.shiftZerosToTheEndNaive(nums);
        assertThat(nums).isEqualTo(expected);
    }

    @Test
    public void givenAnArray_whenShiftZerosToTheEnd_thenShiftZerosToEndUsingTempArray() {
        List<Integer> nums = new ArrayList<>(List.of(0, 1, 0, 3, 2));
        List<Integer> expected = List.of(1, 3, 2, 0, 0);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);

        nums = new ArrayList<>(List.of(0, 0, 0));
        expected = List.of(0, 0, 0);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);

        nums = new ArrayList<>(List.of(1, 3, 2));
        expected = List.of(1, 3, 2);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);

        nums = new ArrayList<>(List.of(1, 1, 1, 0, 0));
        expected = List.of(1, 1, 1, 0, 0);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);

        nums = new ArrayList<>(List.of(0, 0, 1, 1, 1));
        expected = List.of(1, 1, 1, 0, 0);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);
    }

    @Test
    public void givenAnEmptyArray_whenShiftZerosToTheEnd_thenShiftZerosToEndUsingTempArray() {
        List<Integer> nums = new ArrayList<>();
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEmpty();
    }

    @Test
    public void givenAnArrayWithOneZero_whenShiftZerosToTheEnd_thenShiftZerosToEndUsingTempArray() {
        List<Integer> nums = new ArrayList<>(List.of(0));
        List<Integer> expected = List.of(0);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);
    }

    @Test
    public void givenAnArrayWithOne1_whenShiftZerosToTheEnd_thenShiftZerosToEndUsingTempArray() {
        List<Integer> nums = new ArrayList<>(List.of(1));
        List<Integer> expected = List.of(1);
        shiftZerosToTheEnd.shiftZerosToTheEnd(nums);
        assertThat(nums).isEqualTo(expected);
    }
}