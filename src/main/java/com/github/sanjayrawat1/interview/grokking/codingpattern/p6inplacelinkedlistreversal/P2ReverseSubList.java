package com.github.sanjayrawat1.interview.grokking.codingpattern.p6inplacelinkedlistreversal;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * <br>
 * Given the head of a LinkedList and two pointers `p` and `q`, reverse the linked list from position `p` to `q`.
 * <ul>
 * <li>
 *     Example 1:<br>
 *     Input: head = [1,2,3,4,5], left = 2, right = 4<br>
 *     Output: [1,4,3,2,5]
 * <li>
 *     Example 2:<br>
 *     Input: head = [5], left = 1, right = 1<br>
 *     Output: [5]
 * </ul>
 *
 * @author Sanjay Singh Rawat
 */
public class P2ReverseSubList {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        print(head);
        head = reverse(head, 2, 4);
        print(head);
    }

    private static Node reverse(Node head, int p, int q) {
        if (p == q) {
            return head;
        }
        // 1 -> 2 -> 3 -> 4 -> 5 -> NULL
        // p = 2
        // q = 4
        Node current = head;
        Node previous = null;
        int i = 0;

        while (current != null && i < p - 1) {
            previous = current;
            current = current.next;
            i++;
        }

        // we are interested in three parts of the LL,
        // 1. the part before index p
        // 2. the part between p and q
        // 3. and the part after index q
        Node lastNodeOfFirstPart = previous;

        // after reversing the LL, current will become the last node of the subList
        Node lastNodeOfSubList = current;

        // will be used to temporarily store the next node
        Node next = null;

        i = 0;
        // reverse nodes between p and q
        while (current != null && i < q - p + 1) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            i++;
        }

        // connect with the first part
        if (lastNodeOfFirstPart != null) {
            // previous is now the first node of the sub list
            lastNodeOfFirstPart.next = previous;
            // this means p === 1 i.e., we are changing the first node(head) of the LL
        } else {
            head = previous;
        }

        // connect with the last part
        lastNodeOfSubList.next = current;
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
