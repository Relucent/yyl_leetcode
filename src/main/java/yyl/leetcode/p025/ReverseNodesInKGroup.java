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
        ListNode result = solution.reverseKGroup(head, 3);
        System.out.println(result);
    }

    // (1->2)(2->3)(3->4)|(4->5)(5->6)(6->7)|(7->8)(8->9)(9->10)|(10->11)(11->N)
    // (3->2)(2->1)(1->6)|(6->5)(5->4)(4->9)|(9->8)(8->7)(7->10)|(10->11)(11->N)
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k < 2) {
                return head;
            }
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;

            // (D->1)(1->2)(2->3)(3->4)|(4->5)(5->6)(6->7)|(7->8)(8->9)(9->10)|(10->11)(11->N)
            ListNode previous = dummyHead;
            ListNode quick = previous;
            ListNode cursor = null;

            while (quick != null) {

                // 让quick先跑k步，判断区间内节点是否够k个
                for (int i = 0; i < k && quick != null; i++) {
                    quick = quick.next;
                }

                // 不够k个，直接跳出
                if (quick == null) {
                    break;
                }

                // 否则反转子区间内的节点
                cursor = previous.next;
                for (int i = 1; i < k; i++) {
                    ListNode next = cursor.next;
                    cursor.next = next.next;
                    next.next = previous.next;
                    previous.next = next;
                }

                // 设置quick，previous
                quick = previous = cursor;
            }
            return dummyHead.next;
        }
    }
}
