package yyl.leetcode.p04;

import java.util.Arrays;
import java.util.Stack;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>两数相加 II</h3><br>
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。<br>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。<br>
 * 进阶：<br>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。<br>
 * 
 * <pre>
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 * </pre>
 */
public class P0445_AddTwoNumbersII {
	public static void main(String[] args) {
		Solution solution = new Solution();
		ListNode l1 = ListNode.create(7, 2, 4, 3);
		ListNode l2 = ListNode.create(5, 6, 4);
		ListNode result = solution.addTwoNumbers(l1, l2);
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(result);
	}

	// 思路
	// 本题的主要难点在于链表中数位的顺序与我们做加法的顺序是相反的，为了逆序处理所有数位，我们可以使用栈。
	// 把所有数字压入栈中，再依次取出相加。计算过程中需要注意进位的情况。
	// (考虑到性能，写了一个IntStack)
	static class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			IntStack stack1 = new IntStack();
			IntStack stack2 = new IntStack();
			while (l1 != null) {
				stack1.push(l1.val);
				l1 = l1.next;
			}
			while (l2 != null) {
				stack2.push(l2.val);
				l2 = l2.next;
			}
			ListNode head = null;
			int carry = 0;
			while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
				int sum = carry;
				sum += !stack1.isEmpty() ? stack1.pop() : 0;
				sum += !stack2.isEmpty() ? stack2.pop() : 0;
				carry = sum / 10;
				ListNode current = new ListNode(sum % 10);
				current.next = head;
				head = current;
			}
			return head;
		}

		private class IntStack {
			private int[] elementData = new int[256];
			private int top = -1;

			public void push(int element) {
				top++;
				if (top >= elementData.length) {
					elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length >> 1));
				}
				elementData[top] = element;
			}

			public int pop() {
				return elementData[top--];
			}

			public boolean isEmpty() {
				return top == -1;
			}
		}
	}

	// 思路
	// 本题的主要难点在于链表中数位的顺序与我们做加法的顺序是相反的，为了逆序处理所有数位，我们可以使用栈。
	// 把所有数字压入栈中，再依次取出相加。计算过程中需要注意进位的情况。
	static class Solution2 {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			Stack<Integer> stack1 = new Stack<>();
			Stack<Integer> stack2 = new Stack<>();
			while (l1 != null) {
				stack1.push(l1.val);
				l1 = l1.next;
			}
			while (l2 != null) {
				stack2.push(l2.val);
				l2 = l2.next;
			}
			ListNode head = null;
			int carry = 0;
			while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
				int sum = carry;
				sum += !stack1.isEmpty() ? stack1.pop() : 0;
				sum += !stack2.isEmpty() ? stack2.pop() : 0;
				carry = sum / 10;
				ListNode current = new ListNode(sum % 10);
				current.next = head;
				head = current;
			}
			return head;
		}
	}
}
