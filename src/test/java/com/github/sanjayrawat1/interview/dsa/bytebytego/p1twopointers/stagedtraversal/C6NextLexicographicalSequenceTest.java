package com.github.sanjayrawat1.interview.dsa.bytebytego.p1twopointers.stagedtraversal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author sanjayrawat1
 */
class C6NextLexicographicalSequenceTest {

    private C6NextLexicographicalSequence nextLexicographicalSequence;

    @BeforeEach
    void setup() {
        this.nextLexicographicalSequence = new C6NextLexicographicalSequence();
    }

    @Test
    public void givenString_whenNextLexicographicalSequence_thenReturnNextLexicographicalSequence() {
        String s = "abcedda";
        String expected = "abdacde";
        String actual = nextLexicographicalSequence.nextLexicographicalSequence(s);
        assertThat(actual).isEqualTo(expected);

        s = "ccba";
        expected = "abcc";
        actual = nextLexicographicalSequence.nextLexicographicalSequence(s);
        assertThat(actual).isEqualTo(expected);

        s = "a";
        expected = "a";
        actual = nextLexicographicalSequence.nextLexicographicalSequence(s);
        assertThat(actual).isEqualTo(expected);

        s = "aaaa";
        expected = "aaaa";
        actual = nextLexicographicalSequence.nextLexicographicalSequence(s);
        assertThat(actual).isEqualTo(expected);

        s = "ynitsed";
        expected = "ynsdeit";
        actual = nextLexicographicalSequence.nextLexicographicalSequence(s);
        assertThat(actual).isEqualTo(expected);
    }
}