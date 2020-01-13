package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>删除排序链表中的重复元素 II</h3><br>
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 
 * <pre>
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * </pre>
 */
public class P0082_RemoveDuplicatesFromSortedListII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode sample = ListNode.create(1, 2, 3, 3, 4, 4, 5);
        ListNode result = solution.deleteDuplicates(sample);
        System.out.println(result);
    }

    // 遍历法
    // 时间复杂度：O(N)，N为链表长度
    // 空间复杂度：O(N)，新建一个链接，存储返回的数据
    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummyHead = new ListNode(-1);
            ListNode previous = dummyHead;
            ListNode current = head;
            while (current != null) {
                ListNode next = current.next;
                if (next == null || next.val != current.val) {
                    previous = (previous.next = new ListNode(current.val));
                } else {
                    while (next != null && next.val == current.val) {
                        next = next.next;
                    }
                }
                current = next;
            }
            return dummyHead.next;
        }
    }

    // 递归法
    // 时间复杂度：O(N)，N为链表长度
    // 空间复杂度：O(1)，直接修改的原链表
    static class Solution1 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode p = head.next;
            if (head.val == p.val) {
                while (p != null && head.val == p.val) {
                    p = p.next;
                }
                return deleteDuplicates(p);
            } else {
                head.next = deleteDuplicates(p);
                return head;
            }
        }
    }
}
