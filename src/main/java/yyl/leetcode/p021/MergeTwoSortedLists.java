package yyl.leetcode.p021;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>合并两个有序链表</h3><br>
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 <br>
 * 示例：<br>
 * 输入：1->2->4, 1->3->4<br>
 * 输出：1->1->2->3->4->4<br>
 * <br>
 * Merge Two Sorted Lists <br>
 * Merge two sorted linked lists and return it as a new list. <br>
 * The new list should be made by splicing together the nodes of the first two lists.<br>
 */
// 合并两个已排序的链表并将其作为一个新列表返回，新列表应该通过拼接前两个列表的节点来完成。
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = ListNode.create(1, 3, 5, 7, 9);
        ListNode l2 = ListNode.create(0, 2, 4, 6, 8);
        ListNode result = solution.mergeTwoLists(l1, l2);
        System.out.println(result);
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
