package com.github.sanjayrawat1.interview.systemdesign.consistenthashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @see <a href="https://tom-e-white.com/2007/11/consistent-hashing.html">Consistent Hashing</a>.
 * @author sanjayrawat1
 */
public class ConsistentHashing<T> {

    private final HashFunction hashFunction;

    private final int numberOfReplicas;

    private final SortedMap<Integer, T> circle = new TreeMap<>();

    public ConsistentHashing(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            add(node);
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = hashFunction.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            // here get next key > hash, if not present then first key in the map
            // why first key? if key is not in the last of circle then its first in the circle
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }

        return circle.get(hash);
    }
}
