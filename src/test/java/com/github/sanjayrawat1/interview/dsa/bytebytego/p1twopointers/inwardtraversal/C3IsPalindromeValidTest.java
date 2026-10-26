package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.inwardtraversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author sanjayrawat1
 */
class C3IsPalindromeValidTest {

    private C3IsPalindromeValid isPalindromeValid;

    @BeforeEach
    void setup() {
        this.isPalindromeValid = new C3IsPalindromeValid();
    }

    @Test
    public void givenPalindromicString_whenIsPalindromeValid_thenReturnTrue() {
        String s = "a dog! a panic in a pagoda.";
        boolean isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertTrue(isPalindromeValid);

        s = "12.02.2021";
        isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertTrue(isPalindromeValid);

        s = "aa";
        isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertTrue(isPalindromeValid);
    }

    @Test
    public void givenEmptyString_whenIsPalindromeValid_thenReturnTrue() {
        String s = "";
        boolean isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertTrue(isPalindromeValid);
    }

    @Test
    public void givenNull_whenIsPalindromeValid_thenReturnTrue() {
        String s = null;
        boolean isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertTrue(isPalindromeValid);
    }

    @Test
    public void givenNonPalindromicString_whenIsPalindromeValid_thenReturnFalse() {
        String s = "hello, world!";
        boolean isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertFalse(isPalindromeValid);

        s = "21.02.2021";
        isPalindromeValid = this.isPalindromeValid.isPalindromeValid(s);
        assertFalse(isPalindromeValid);
    }
}