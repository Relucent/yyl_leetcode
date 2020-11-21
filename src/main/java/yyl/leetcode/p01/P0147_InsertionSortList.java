package yyl.leetcode.p01;

import yyl.leetcode.bean.ListNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>对链表进行插入排序</h3><br>
 * 插入排序算法：<br>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。<br>
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。<br>
 * 重复直到所有输入数据插入完为止。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * </pre>
 */
public class P0147_InsertionSortList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(ListNode.create(1, 2, 3, 4), solution.insertionSortList(ListNode.create(4, 2, 1, 3)));
        Assert.assertEquals(ListNode.create(-1, 0, 3, 4, 5), solution.insertionSortList(ListNode.create(1, 5, 3, 4, 0)));
    }

    // 从前往后找插入点
    // 1、首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
    // 2、创建哑节点 dummyHead，令 dummyHead.next = head。引入哑节点是为了便于在 head 节点之前插入节点。
    // 3、 维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head。
    // 4、 维护 current 为待插入的元素，初始时 current = head.next。
    // 5、比较 lastSorted 和 current 的节点值。
    // ├ 若 lastSorted.val <= current.val，说明 current 应该位于 lastSorted 之后，将 lastSorted 后移一位，current 变成新的 lastSorted。
    // └ 否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 current 的位置。令 previous 为插入 current的位置的前一个节点，进行如下操作，完成对 current 的插入：
    // - ├ lastSorted.next = current.next
    // - ├ current.next = previous.next
    // - └ previous.next = current
    // 6、令 current = lastSorted.next，此时 current 为下一个待插入的元素。
    // 7、 重复第 5 步和第 6 步，直到 current 变成空，排序结束。
    // 8、 返回 dummyHead.next，为排序后的链表的头节点。
    // 时间复杂度：O(n^2)，其中 n 是链表的长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public ListNode insertionSortList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            ListNode lastSorted = head;
            ListNode current = head.next;
            while (current != null) {
                if (lastSorted.val <= current.val) {
                    lastSorted = lastSorted.next;
                } else {
                    ListNode previous = dummyHead;
                    while (previous.next.val <= current.val) {
                        previous = previous.next;
                    }
                    lastSorted.next = current.next;
                    current.next = previous.next;
                    previous.next = current;
                }
                current = lastSorted.next;
            }
            return dummyHead.next;
        }
    }
}
