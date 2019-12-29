package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>删除链表的倒数第N个节点</h3><br>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * <pre>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * </pre>
 * 
 * 说明：<br>
 * 给定的 n 保证是有效的。<br>
 * 进阶：<br>
 * 你能尝试使用一趟扫描实现吗？<br>
 * <br>
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
public class P0019_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = ListNode.create(1, 2);
        System.out.println(l1);
        l1 = solution.removeNthFromEnd(l1, 2);
        System.out.println(l1);
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
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
}
