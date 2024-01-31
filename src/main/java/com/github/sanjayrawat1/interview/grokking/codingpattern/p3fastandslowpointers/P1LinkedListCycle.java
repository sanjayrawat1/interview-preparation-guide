package com.github.sanjayrawat1.interview.grokking.codingpattern.p3fastandslowpointers;

/**
 * @author Sanjay Singh Rawat
 */
public class P1LinkedListCycle {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        System.out.println(findCycleLength(head));
        head.next.next.next.next.next.next = head.next.next;
        System.out.println(findCycleLength(head));
        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println(findCycleLength(head));
    }

    private static boolean hashCycle(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    private static int findCycleLength(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return calculateCycleLength(slow);
            }
        }

        return 0;
    }

    private static int calculateCycleLength(Node slow) {
        Node current = slow;
        int cycleLength = 0;
        while (true) {
            current = current.next;
            cycleLength++;
            if (current == slow) {
                break;
            }
        }
        return cycleLength;
    }
}
