package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>删除排序链表中的重复元素 II</h3><br>
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * </pre>
 */
public class P0083_RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode sample = ListNode.create(1, 1, 2, 3, 3);
        ListNode result = solution.deleteDuplicates(sample);
        System.out.println(result);
    }

    // 遍历法
    // 时间复杂度：O(N)，因为列表中的每个结点都检查一次以确定它是否重复，所以总运行时间为N，N为链表中的结点数
    // 空间复杂度：O(N)，新建一个链接，存储返回的数据
    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummyHead = new ListNode(-1);
            ListNode previous = dummyHead;
            ListNode current = head;
            while (current != null) {
                previous = (previous.next = new ListNode(current.val));
                ListNode next = current.next;
                while (next != null && next.val == current.val) {
                    next = next.next;
                }
                current = next;
            }
            return dummyHead.next;
        }
    }

    // 直接修改遍历法
    // 时间复杂度：O(N)，因为列表中的每个结点都检查一次以确定它是否重复，所以总运行时间为N，N为链表中的结点数
    // 空间复杂度：O(1)，没有使用额外的空间
    static class Solution1 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode current = head;
            while (current != null && current.next != null) {
                if (current.next.val == current.val) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
            return head;
        }
    }
}
