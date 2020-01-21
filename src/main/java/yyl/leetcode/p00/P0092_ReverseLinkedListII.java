package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>反转链表 II</h3><br>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。<br>
 * 说明:<br>
 * 1 ≤ m ≤ n ≤ 链表长度。<br>
 * 
 * <pre>
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * </pre>
 */
public class P0092_ReverseLinkedListII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseBetween(ListNode.create(1, 2, 3, 4, 5), 2, 4));
        System.out.println(solution.reverseBetween(ListNode.create(1, 2, 3, 4, 5, 6, 7, 8), 2, 4));
        System.out.println(solution.reverseBetween(ListNode.create(1), 1, 1));
    }

    // 一次迭代，翻转中间部分再合并头尾
    // 迭代找到中段的开始位置(M)，然后翻转链表到N位置，此时链表分为了前部分，中间部分，后部分，再将这三部分拼到一起。
    // 需要注意的是，这个题目的索引m,n是从1开始计算的(程序一般是从0开始计算)，此处容易出错
    // 时间复杂度：O(N)
    // 空间复杂度：O(1)
    static class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (head == null) {
                return null;
            }
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;

            // 这个题目的索引m,n是从1开始计算的，所以index应该从1开始计算，但是正好前面加了一个虚节点 index = 1 - 1 = 0
            // 虚节点，应为需要考虑m=1的情况
            head = dummyHead;
            int index = 0;
            // 遍历到M前一个节点
            for (; index < m - 1; index++) {
                head = head.next;
            }
            ListNode previous = head;

            // 开始翻转之后的链表，翻转到位置N
            head = previous.next;
            // 片段的新的头
            ListNode newPartHead = null;
            // 片段的新的尾
            ListNode newPartTail = head;
            // 开始翻转
            for (; index < n && head != null; index++) {
                ListNode node = head;
                head = head.next;
                node.next = newPartHead;
                newPartHead = node;
            }
            // 拼装链表三个部分
            previous.next = newPartHead;
            newPartTail.next = head;
            // 返回链表头
            return dummyHead.next;
        }
    }
}
