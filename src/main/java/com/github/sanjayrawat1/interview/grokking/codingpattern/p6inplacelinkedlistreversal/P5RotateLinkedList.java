package com.github.sanjayrawat1.interview.grokking.codingpattern.p6inplacelinkedlistreversal;

/**
 * https://leetcode.com/problems/rotate-list/
 * <br>
 * Given a head of a LinkedList and a number K, rotate the LinkedList to the right by K nodes.
 * <br>
 * <li> Example 1:
 * <br>
 * Input: head = [1,2,3,4,5], k = 2
 * <br>
 * Output: [4,5,1,2,3]
 * <li> Example 2:
 * <br>
 *
 * Input: head = [0,1,2], k = 4
 * <br>
 * Output: [2,0,1]
 *
 * @author Sanjay Singh Rawat
 */
public class P5RotateLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        print(head);
        head = rotate(head, 3);
        print(head);
    }

    /**
     * Another way of defining the rotation is to take the sub-list of K ending nodes of the LinkedList and
     * connect them to the beginning. Other than that we have to do three more things:
     * <ol>
     *     <li> Connect the last node of the LinkedList to the head, because the list will have a different tail after the rotation.
     *     <li> The new head of the LinkedList will be the node at the beginning of the sublist.
     *     <li> The node right before the start of sub-list will be the new tail of the rotated LinkedList.
     * </ol>
     */
    private static Node rotate(Node head, int rotations) {
        if (head == null || head.next == null || rotations < 1) {
            return head;
        }

        // find the length and the last node of the list
        Node lastNode = head;
        int listLength = 1;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
            listLength++;
        }

        // connect the last node with the head to make it a circular list
        lastNode.next = head;

        // no need to do rotations more than the length of the list
        rotations %= listLength;
        int skipLength = listLength - rotations;
        Node lastNodeOfRotatedList = head;
        for (int i = 0; i < skipLength - 1; i++) {
            lastNodeOfRotatedList = lastNodeOfRotatedList.next;
        }

        // lastNodeOfRotatedList.next is pointing to the sub-list of K ending nodes
        head = lastNodeOfRotatedList.next;
        lastNodeOfRotatedList.next = null;

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
