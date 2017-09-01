package yyl.algorithms.v002;

import yyl.algorithms.bean.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.<br>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.<br>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)<br>
 * Output: 7 -> 0 -> 8 <br>
 */
// 你有两个非空链表代表两个非负整数。数字以相反的顺序存储，每个节点包含一个单一的数字。<br>
// 两个链表相加返回一个链表。(你可能认为这两个数字不包含任何前导零，除了0号本身)
public class AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = ListNode.create(2, 4, 3);
		ListNode l2 = ListNode.create(5, 6, 4);
		ListNode result = addTwoNumbers(l1, l2);
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(result);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode cursor1 = l1;
		ListNode cursor2 = l2;
		ListNode pre = dummyHead;
		int up = 0;
		while (cursor1 != null || cursor2 != null) {
			int v1 = cursor1 != null ? cursor1.val : 0;
			int v2 = cursor2 != null ? cursor2.val : 0;
			int sum = v1 + v2 + up;
			up = sum / 10;
			pre = (pre.next = new ListNode(sum % 10));
			if (cursor1 != null) {
				cursor1 = cursor1.next;
			}
			if (cursor2 != null) {
				cursor2 = cursor2.next;
			}
		}
		if (up > 0) {
			pre.next = new ListNode(up);
		}
		return dummyHead.next;
	}
}
