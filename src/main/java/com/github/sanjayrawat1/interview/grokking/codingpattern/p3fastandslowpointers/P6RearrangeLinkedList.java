package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

/**
 * https://leetcode.com/problems/reorder-list/
 *
 * @author Sanjay Singh Rawat
 */
public class P6RearrangeLinkedList {

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = new Node(12);
        print(head);
        rearrange(head);
        print(head);
    }

    private static void rearrange(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node mid = slow;
        Node reversedSecondHalf = reverse(mid);
        Node firstHalf = head;

        while (firstHalf != null && reversedSecondHalf != null) {
            Node next = firstHalf.next;
            firstHalf.next = reversedSecondHalf;
            firstHalf = next;

            next = reversedSecondHalf.next;
            reversedSecondHalf.next = firstHalf;
            reversedSecondHalf = next;
        }

        if (firstHalf != null) {
            firstHalf.next = null;
        }
    }

    private static Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    private static void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            System.out.print(" -> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }
}
