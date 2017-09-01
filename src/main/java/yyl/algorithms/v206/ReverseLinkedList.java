package yyl.algorithms.v206;

import yyl.algorithms.bean.ListNode;

/**
 * Reverse a singly linked list.
 */
//将单链表逆转
public class ReverseLinkedList {

	public static void main(String[] args) {
		ListNode list = ListNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println(list);
		list = reverseList2(list);
		System.out.println(list);
	}

	public static ListNode reverseList(ListNode head) {
		ListNode pre = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p1 = head;
		ListNode p2 = head.next;
		while (p1 != null && p2 != null) {
			ListNode next = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = next;
		}
		head.next = null;
		return p1;
	}
}
