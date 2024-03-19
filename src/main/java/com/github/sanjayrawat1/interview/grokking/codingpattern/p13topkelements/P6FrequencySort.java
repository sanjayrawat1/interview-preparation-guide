package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/">sort-characters-by-frequency</a>
 * <p>
 * Given a string, sort it based on the decreasing frequency of its characters.
 *
 * @author sanjayrawat1
 */
public class P6FrequencySort {

    record Pair(char character, int frequency) {}

    public static String sort(String str) {
        Map<Character, Integer> charactersFrequency = new HashMap<>();
        for (char ch : str.toCharArray()) {
            charactersFrequency.put(ch, charactersFrequency.getOrDefault(ch, 0) + 1);
        }

        Queue<Pair> maxHeap = new PriorityQueue<>(Comparator.comparingInt(Pair::frequency).reversed());

        charactersFrequency.forEach((character, frequency) -> {
            maxHeap.offer(new Pair(character, frequency));
        });

        StringBuilder sortedString = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Pair characterWithFrequency = maxHeap.poll();
            sortedString.repeat(characterWithFrequency.character(), characterWithFrequency.frequency());
            /*
            for (int i = 0; i < characterWithFrequency.frequency(); i++) {
                sortedString.append(characterWithFrequency.character());
            }
            */
        }

        return sortedString.toString();
    }

    public static void main(String[] args) {
        assertThat(sort("Programming")).isEqualTo("rrggmmPiano");
        assertThat(sort("abcbab")).isEqualTo("bbbaac");
        assertThat(sort("tree")).isEqualTo("eert");
        assertThat(sort("cccaaa")).isEqualTo("aaaccc");
        assertThat(sort("Aabb")).isEqualTo("bbAa");
    }
}
