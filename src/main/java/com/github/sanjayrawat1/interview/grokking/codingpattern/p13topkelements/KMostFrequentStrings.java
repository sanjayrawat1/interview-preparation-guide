package com.github.sanjayrawat1.interview.grokking.codingpattern.p13topkelements;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
/**
 *
 * @author sanjayrawat1
 */
public class KMostFrequentStrings {

    public static void main(String[] args) {
        ArrayList<String> strs = new ArrayList<>();
        strs.add("byte");
        strs.add("byte");
        strs.add("go");
        strs.add("city");

        ArrayList<String> strings = k_most_frequent_strings(strs, 2);
        System.out.println(strings);
    }

    public static ArrayList<String> k_most_frequent_strings(ArrayList<String> strs, int k) {
        Map<String, Integer> stringFrequencies = new HashMap<>();
        for (String str : strs) {
            stringFrequencies.put(str, stringFrequencies.getOrDefault(str, 0) + 1);
        }

        // Map<String, Integer> stringFrequencies = strs.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(Pair::freq)
                .thenComparing(Comparator.comparing(Pair::str).reversed()));

        for (Map.Entry<String, Integer> entry : stringFrequencies.entrySet()) {
            minHeap.offer(new Pair(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        ArrayList<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll().str());
        }

        ArrayList<String> rev = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            rev.add(res.get(i));
        }
        return rev;
    }

    static class Pair {
        String str;
        int freq;

        public Pair(String str, int freq) {
            this.str = str;
            this.freq = freq;
        }

        public String str() {
            return str;
        }

        public int freq() {
            return freq;
        }
    }
}
