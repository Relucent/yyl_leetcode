package yyl.algorithms.v234;

import java.util.ArrayList;

/**
 * Given a singly linked list, determine if it is a palindrome.
 */
//给定一个单链表，确定它是否是回文
public class PalindromeLinkedList {

	public static void main(String[] args) {
		ListNode l0 = create(0);
		ListNode l1 = create(1, 2, 3, 4, 5);
		ListNode l2 = create(1, 2, 3, 2, 1);
		ListNode l3 = create(1, 2, 3, 3, 2, 1);
		System.out.println(isPalindrome(l0));
		System.out.println(isPalindrome(l1));
		System.out.println(isPalindrome(l2));
		System.out.println(isPalindrome(l3));
	}

	public static boolean isPalindrome(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		ListNode pre = null;
		//[(1)->2,(2)->3,(3)->4,(4)->5,(5)->null] 
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			ListNode temp = slow.next;
			slow.next = pre;
			pre = slow;
			slow = temp;
		}

		ListNode next = slow;

		//[null<-(1),1<-(2),(3)->4,(4)->5,(5)->null] 
		//PRE:[2,1] SLOW:[3,4,5] 
		if (fast != null) {
			slow = slow.next; //slow:3=>4
		}

		boolean result = true;
		while (slow != null) {
			if (result && pre.val != slow.val) {
				result = false;
			}
			//[null<-(1),1<-(2) ...] =>[(1)->2,(2)->3 ...]  
			slow = slow.next;
			ListNode temp = pre.next;
			pre.next = next;
			next = pre;
			pre = temp;
		}
		//[(1)->2,(2)->3,(3)->4,(4)->5,(5)->null] 
		return result;
	}

	public static boolean isPalindrome2(ListNode head) {
		ListNode cursor = head;
		ArrayList<ListNode> stack = new ArrayList<>();
		while (cursor != null) {
			stack.add(cursor);
			cursor = cursor.next;
		}

		cursor = head;
		int i = stack.size();
		while (cursor != null) {
			--i;
			if (cursor.val != stack.get(i).val) {
				return false;
			}
			cursor = cursor.next;
		}
		return true;
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
