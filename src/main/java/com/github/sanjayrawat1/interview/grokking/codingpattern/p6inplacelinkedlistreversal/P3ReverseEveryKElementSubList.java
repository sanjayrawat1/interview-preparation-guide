package com.github.sanjayrawat1.interview.grokking.codingpattern.p6inplacelinkedlistreversal;

/**
 * @author Sanjay Singh Rawat
 */
public class P3ReverseEveryKElementSubList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        print(head);
        head = reverse(head, 3);
        print(head);
    }

    private static Node reverse(Node head, int k) {
        if (head == null || k < 2) {
            return head;
        }

        Node current = head;
        Node previous = null;

        while (true) {
            Node lastNodeOfPreviousPart = previous;

            // after reversing the LL current will become the last node of the sublist
            Node lastNodeOfSubList = current;

            int i = 0;

            // reverse k nodes
            while (current != null && i < k) {
                Node next = current.next;
                current.next = previous;
                previous = current;
                current = next;
                i++;
            }

            // connect with the previous part
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = previous;
            } else {
                head = previous;
            }

            // connect with the next part
            lastNodeOfSubList.next = current;

            if (current == null) {
                break;
            }

            previous = lastNodeOfSubList;
        }
        return head;
    }

    private static void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val);
            System.out.print(" -> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }
}
