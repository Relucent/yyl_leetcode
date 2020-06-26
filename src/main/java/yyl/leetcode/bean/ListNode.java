package yyl.leetcode.bean;

/**
 * Definition for singly-linked list.
 */
public class ListNode {

	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		ListNode node = this;
		for (int i = 0;; i++) {
			builder.append(node.val);
			node = node.next;
			if (node == null) {
				builder.append(']');
				break;
			} else if (i == 31) {
				builder.append(", ...]");
				break;
			}
			builder.append(',').append(' ');
		}
		return builder.toString();
	}

	public static ListNode create(int... values) {
		ListNode dummyHead = new ListNode(0);
		ListNode previous = dummyHead;
		for (int val : values) {
			ListNode temp = new ListNode(val);
			previous.next = temp;
			previous = temp;
		}
		return dummyHead.next;
	}

	public static String toString(ListNode node) {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (;;) {
			if (node == null) {
				builder.append(']');
				break;
			}
			builder.append(node.val);
			node = node.next;
			if (node != null) {
				builder.append(',').append(' ');
			}
		}
		return builder.toString();
	}
}
