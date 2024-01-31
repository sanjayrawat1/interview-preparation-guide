package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sanjay Singh Rawat
 */
public class P4MiddleOfLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        assertThat(fastAndSlowPointer(head).data).isEqualTo(3);

        head.next.next.next.next.next = new Node(6);
        assertThat(fastAndSlowPointer(head).data).isEqualTo(4);

        head.next.next.next.next.next.next = new Node(7);
        assertThat(fastAndSlowPointer(head).data).isEqualTo(4);
    }

    private static Node fastAndSlowPointer(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private static Node bruteForce(Node head) {
        Node current = head;
        int length = length(current);

        int middle = length/2;

        while (middle > 0) {
            current = current.next;
            middle--;
        }

        return current;
    }

    private static int length(Node head) {
        Node current = head;
        int length = 0;

        while (current != null) {
            length++;
            current = current.next;
        }

        return length;
    }
}
