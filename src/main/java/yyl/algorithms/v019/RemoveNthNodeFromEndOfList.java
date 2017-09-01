package yyl.algorithms.v019;

import yyl.algorithms.bean.ListNode;

/**
 * Remove Nth Node From End of List <br>
 * Given a linked list, remove the nth node from the end of list and return its head.<br>
 * 
 * <pre>
 * For example, 
 *  Given linked list: 1->2->3->4->5, and n = 2.
 *  After removing the second node from the end, the linked list becomes 1->2->3->5.
 * </pre>
 * 
 * Note:<br>
 * Given n will always be valid.<br>
 * Try to do this in one pass. <br>
 */
// 从列表末端删除第n个节点 
// 备注: 给定的n总是有效的，尝试只遍历一次链表
public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
		ListNode l1 = ListNode.create(1, 2);
		System.out.println(l1);
		l1 = removeNthFromEnd(l1, 2);
		System.out.println(l1);
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode fast = head;
		while ((n--) != 0) {
			fast = fast.next;
		}
		if (fast == null) {
			return head.next;
		}
		ListNode cursor = head;
		while (fast.next != null) {
			fast = fast.next;
			cursor = cursor.next;
		}
		cursor.next = cursor.next.next;
		return head;
	}
}
