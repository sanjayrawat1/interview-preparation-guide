package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

/**
 * @author Sanjay Singh Rawat
 */
public class P2StartOfLinkedListCycle {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = head.next.next;

        System.out.println(startOfCycle(head).data);
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(startOfCycle(head).data);
        head.next.next.next.next.next.next = head;
        System.out.println(startOfCycle(head).data);
    }

    private static Node startOfCycle(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
