package yyl.leetcode.p025;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>K个一组翻转链表</h3><br>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 <br>
 * k 是一个正整数，它的值小于或等于链表的长度。 <br>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 <br>
 * 示例 : <br>
 * 给定这个链表：1->2->3->4->5 <br>
 * 当 k = 2 时，应当返回: 2->1->4->3->5 <br>
 * 当 k = 3 时，应当返回: 3->2->1->4->5 <br>
 * 说明 : <br>
 * 你的算法只能使用常数的额外空间。 <br>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 <br>
 * <br>
 * Reverse Nodes in k-Group<br>
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.<br>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out
 * nodes in the end should remain as it is.<br>
 * You may not alter the values in the nodes, only nodes itself may be changed.<br>
 * Only constant memory is allowed.<br>
 * 
 * <pre>
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * </pre>
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = ListNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        // ListNode head = ListNode.create(1, 2, 3);
        ListNode result = solution.reverseKGroup(head, 4);
        System.out.println(result);
    }

    // (1->2)(2->3)(3->4)|(4->5)(5->6)(6->7)|(7->8)(8->9)(9->N)
    // (1->6)(2->1)(3->2)|(4->9)(5->4)(6->5)|(7->N)(8->7)(9->8)
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k < 2) {
                return head;
            }
            ListNode cursor = head;
            ListNode previous = null;

            ListNode dummyHead = new ListNode(0);
            ListNode prePreGroupHead = null;
            ListNode preGroupHead = dummyHead;
            int groupTailIndex = k - 1;
            int count = 0;
            while (cursor != null) {
                if (count % k == 0) {
                    prePreGroupHead = preGroupHead;
                    preGroupHead = cursor;
                    ListNode next = cursor.next;
                    cursor.next = null;
                    previous = cursor;
                    cursor = next;
                } else if (count % k == groupTailIndex) {
                    prePreGroupHead.next = cursor;
                    ListNode next = cursor.next;
                    cursor.next = previous;
                    previous = cursor;
                    cursor = next;
                } else {
                    ListNode next = cursor.next;
                    cursor.next = previous;
                    previous = cursor;
                    cursor = next;
                }
                count++;
            }
            if (count % k != 0) {
                cursor = previous;
                previous = null;
                while (cursor != null) {
                    ListNode next = cursor.next;
                    cursor.next = previous;
                    previous = cursor;
                    cursor = next;
                }
                prePreGroupHead.next = previous;
            }
            return dummyHead.next;
        }
    }
}
