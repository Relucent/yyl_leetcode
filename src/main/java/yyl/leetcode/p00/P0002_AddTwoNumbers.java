package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>两数相加</h3><br>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。<br>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。<br>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。<br>
 * 示例：<br>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)<br>
 * 输出：7 -> 0 -> 8<br>
 * 原因：342 + 465 = 807<br>
 * <br>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.<br>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.<br>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)<br>
 * Output: 7 -> 0 -> 8 <br>
 */
public class P0002_AddTwoNumbers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = ListNode.create(2, 4, 3);
        ListNode l2 = ListNode.create(5, 6, 4);
        ListNode result = solution.addTwoNumbers(l1, l2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(result);
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
}
