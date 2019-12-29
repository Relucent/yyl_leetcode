package yyl.leetcode.p02;

import java.util.ArrayList;

import yyl.leetcode.bean.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 */
//给定一个单链表，确定它是否是回文
public class P0234_PalindromeLinkedList {

	public static void main(String[] args) {
		ListNode l0 = ListNode.create(0);
		ListNode l1 = ListNode.create(1, 2, 3, 4, 5);
		ListNode l2 = ListNode.create(1, 2, 3, 2, 1);
		ListNode l3 = ListNode.create(1, 2, 3, 3, 2, 1);

		Solution solution = new Solution();

		System.out.println(solution.isPalindrome(l0));
		System.out.println(solution.isPalindrome(l1));
		System.out.println(solution.isPalindrome(l2));
		System.out.println(solution.isPalindrome(l3));
	}

	static class Solution {
		public boolean isPalindrome(ListNode head) {
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
	}

	static class Solution2 {
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
	}
}