package yyl.algorithms.v206;

/**
 * Reverse a singly linked list.
 */
//将单链表逆转
public class ReverseLinkedList {

	public static void main(String[] args) {
		ListNode list = create(1, 2, 3, 4, 5, 6, 7, 8, 9);
		println(list);
		list = reverseList2(list);
		println(list);
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

	/* Definition for singly-linked list. */
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static ListNode create(int... values) {
		ListNode dummyHead = new ListNode(0);
		ListNode pre = dummyHead;
		for (int val : values) {
			ListNode temp = new ListNode(val);
			pre.next = temp;
			pre = temp;
		}
		return dummyHead.next == null ? dummyHead : dummyHead.next;
	}

	public static void println(ListNode l) {
		if (l == null) {
			System.out.println("null");
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			sb.append(l.val);
			l = l.next;
			if (l == null) {
				sb.append(']');
				break;
			}
			sb.append(',').append(' ');
		}
		System.out.println(sb);
	}
}
