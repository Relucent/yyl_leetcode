package yyl.leetcode.p02;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>反转链表</h3><br>
 * 反转一个单链表。<br>
 * 
 * <pre>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * </pre>
 * 
 * 进阶:你可以迭代或递归地反转链表。你能否用两种方法解决这道题？<br>
 */
public class P0206_ReverseLinkedList {

	public static void main(String[] args) {
		ListNode list = ListNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Solution solution = new Solution();
		System.out.println(list);
		list = solution.reverseList(list);
		System.out.println(list);
	}

	// 迭代
	// 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
	// 遍历列表时，将当前节点的 next 指针改为指向前一个元素。因为每次next发生改变，需要使用previous存储当前元素作为下一个元素的next。
	// 遍历到null，表示已经到尾部，返回新的头引用（previous）
	// 时间复杂度：O(N)
	// 空间复杂度：O(1)
	static class Solution {
		public ListNode reverseList(ListNode head) {
			ListNode previous = null;
			while (head != null) {
				ListNode next = head.next;
				head.next = previous;
				previous = head;
				head = next;
			}
			return previous;
		}
	}

	// 递归
	// 假设列表的其余部分已经被反转，反转它前面的部分
	// 如果当前节点下一个节点是Ø，那么说明已经到链表尾，返回这个节点作为新的新的头引用
	// 需要考空链表的情况
	// 时间复杂度：O(N)
	// 空间复杂度：O(1)
	static class Solution2 {
		public ListNode reverseList(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}
			ListNode next = head.next;
			ListNode previous = reverseList(head.next);
			next.next = head;
			head.next = null;
			return previous;
		}
	}
}
