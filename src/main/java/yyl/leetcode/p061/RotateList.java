package yyl.leetcode.p061;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>旋转链表</h3><br>
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * </pre>
 */
public class RotateList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list = ListNode.create(1, 2, 3, 4, 5);
        System.out.println(solution.rotateRight(list, 2));
    }

    // 循环遍历链表
    // 时间复杂度：O(n)，N 是链表中的元素个数
    // 空间复杂度：O(1)
    static class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            // 如果链表为NULL，或者只有一个节点，或者 k=0 那么不需要移动直接返回
            if (head == null || head.next == null || k == 0) {
                return head;
            }
            // 循环遍历链表，直到末尾
            ListNode previous = head;
            int count = 1;
            for (; previous.next != null; count++) {
                previous = previous.next;
            }
            // 我们将链表做成循环链表
            previous.next = head;

            // 计算新链表头的位置(每个链表右移动，相当于头位置左移，原先的k代表头左移位置，现在的n代表头右移动位置)
            int n = count - (k % count);
            // 从头head前一个节点(previous)开始遍历n个节点
            for (int i = 0; i < n; i++) {
                previous = previous.next;
            }
            // previous.next 就是新的头节点, previous 就是新的尾节点
            head = previous.next;
            previous.next = null;
            return head;
        }
    }
}
