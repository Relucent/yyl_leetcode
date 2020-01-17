package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>分隔链表</h3><br>
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。<br>
 * 你应当保留两个分区中每个节点的初始相对位置。<br>
 * 
 * <pre>
 * 示例:  
 * 输入: head = 1->4->3->2->5->2, x = 3 
 * 输出: 1->2->2->4->3->5
 * </pre>
 */
public class P0086_PartitionList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partition(ListNode.create(1, 4, 3, 2, 5, 2), 3));
    }

    // 拆分合并法
    // 可以将这个链表，拆分成两个链表。其中一个包括全部值小于x的元素，另一个包括全部值大于等于x的元素。然后再将这两个链表合并。
    // 时间复杂度：O(N)，其中N是原链表的长度，对该链表进行了一次遍历。
    // 空间复杂度：O(1)，只移动了原有的节点，没有申请新空间
    static class Solution {
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return head;
            }
            ListNode beforeHead = new ListNode(0);
            ListNode before = beforeHead;
            ListNode afterHead = new ListNode(0);
            ListNode after = afterHead;
            while (head != null) {
                if (head.val < x) {
                    before = (before.next = head);
                } else {
                    after = (after.next = head);
                }
                head = head.next;
            }
            before.next = afterHead.next;
            after.next = null;
            return beforeHead.next;
        }
    }
}
