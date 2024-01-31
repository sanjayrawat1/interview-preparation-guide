package com.github.sanjayrawat1.interview.grokking.codingpattern.p6inplacelinkedlistreversal;

/**
 * Given the head of a Singly LinkedList, reverse the LinkedList. Write a function to return the new head of the reversed LinkedList.
 *
 * @author Sanjay Singh Rawat
 */
public class P1ReverseLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        print(head);
        head = reverse(head);
        print(head);
    }

    private static Node reverse(Node head) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            // temporarily store the next node
            Node next = current.next;

            // reverse the current node
            current.next = previous;

            // before we move to the next node, point previous to the current node
            previous = current;

            // move on to the next node
            current = next;
        }

        return previous;
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
