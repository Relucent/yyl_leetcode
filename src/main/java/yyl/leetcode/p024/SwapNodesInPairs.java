package yyl.leetcode.p024;

import yyl.leetcode.bean.ListNode;

public class SwapNodesInPairs {
	public static void main(String[] args) {
		Solution solution = new Solution();
		ListNode head = ListNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9);
		ListNode result = solution.swapPairs(head);
		System.out.println(result);
	}

	static class Solution {
		public ListNode swapPairs(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}
			ListNode current = head;
			ListNode previous = null;
			head = head.next;
			while (current != null && current.next != null) {
				ListNode next = current.next;
				ListNode nextNext = next.next;//may be NULL
				next.next = current;
				current.next = nextNext;
				if (previous != null) {
					previous.next = next;
				}
				previous = current;
				current = nextNext;
			}
			return head;
		}
	}
}
