package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

import java.util.Objects;

/**
 * @author Sanjay Singh Rawat
 */
public class Node {

    int data;

    Node next;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "" +
                Objects.hashCode(this) +
                "";
    }
}
