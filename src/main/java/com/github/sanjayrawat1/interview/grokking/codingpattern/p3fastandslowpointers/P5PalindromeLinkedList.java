package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * @author Sanjay Singh Rawat
 */
public class P5PalindromeLinkedList {

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(2);

        assertThat(isPalindrome(head)).isTrue();

        head.next.next.next.next.next = new Node(2);

        assertThat(isPalindrome(head)).isFalse();
    }

    private static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        // reverse the second half
        Node secondHalf = reverse(slow);
        // store the head of reversed part to revert back later
        Node copySecondHalf = secondHalf;
        Node firstHalf = head;

        while (secondHalf != null) {
            if (secondHalf.data != firstHalf.data) {
                break;
            }

            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // revert the reverse of the second half
        reverse(copySecondHalf);

        // if both halves match
        if (firstHalf == null || secondHalf == null) {
            return true;
        }

        return false;
    }

    private static Node reverse(Node head) {
        Node current = head;
        Node prev = null;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    private static Node reverseAndClone(Node node) {
        Node head = null;
        while (node != null) {
            Node n = new Node(node.data);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    private static void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current);
            System.out.print(" -> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }
}
