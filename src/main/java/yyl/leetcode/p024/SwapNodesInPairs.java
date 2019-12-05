package yyl.leetcode.p024;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>两两交换链表中的节点</h3> <br>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。<br>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。<br>
 * 示例:<br>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.<br>
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = ListNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9);
        ListNode result = solution.swapPairs(head);
        System.out.println(result);
    }

    static class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode current = head;
            ListNode previous = null;
            head = head.next;
            while (current != null && current.next != null) {
                ListNode next = current.next;
                ListNode nextNext = next.next;// may be NULL
                next.next = current;
                current.next = nextNext;
                if (previous != null) {
                    previous.next = next;
                }
                previous = current;
                current = nextNext;
            }
            return head;
        }
    }
}
