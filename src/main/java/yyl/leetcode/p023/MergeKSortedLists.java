package yyl.leetcode.p023;

import yyl.leetcode.bean.ListNode;

/**
 * Merge k Sorted Lists <br>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.<br>
 */
public class MergeKSortedLists {
	public static void main(String[] args) {
		ListNode[] lists = { //
				ListNode.create(1, 4, 7, 10), //
				ListNode.create(3, 6, 9, 12, 15), //
				ListNode.create(2, 5, 8, 11, 14),//
		};//
		Solution solution = new Solution();

		ListNode result = solution.mergeKLists(lists);
		System.out.println(result);
	}

	static class Solution {

		//如果使用一般的循环会 提交测试遇到大数据量会 Time Limit Exceeded，所以需要进行优化
		//优化思路：采用分治的方法，将链表不断将其划分(partition)，再将其归并(merge) 
		public ListNode mergeKLists(ListNode[] lists) {
			if (lists == null || lists.length == 0) {
				return null;
			}
			return mergeKLists(lists, 0, lists.length - 1);
		}

		private ListNode mergeKLists(ListNode[] lists, int left, int right) {
			if (left == right) {
				return lists[left];
			}
			if (left < right) {
				int min = (left + right) >> 1;// (left + right)/2
				return mergeTwoLists(//
						mergeKLists(lists, left, min), //
						mergeKLists(lists, min + 1, right)//
				);
			}
			return null;
		}

		private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
			if (l1 == null) {
				return l2;
			}
			if (l2 == null) {
				return l1;
			}
			if (l1.val > l2.val) {
				l2.next = mergeTwoLists(l1, l2.next);
				return l2;
			}
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		}
	}
}
