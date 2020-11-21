package yyl.leetcode.p01;

import yyl.leetcode.bean.ListNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>排序链表</h3><br>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。<br>
 * 进阶： 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 
 * <pre>
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * </pre>
 * 
 * 提示：<br>
 * 链表中节点的数目在范围 [0, 5 * 104] 内<br>
 * -105 <= Node.val <= 105<br>
 */
public class P0148_SortList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(ListNode.create(1, 2, 3, 4), solution.sortList(ListNode.create(4, 2, 1, 3)));
        Assert.assertEquals(ListNode.create(-1, 0, 3, 4, 5), solution.sortList(ListNode.create(-1, 5, 3, 4, 0)));
        Assert.assertEquals(ListNode.create(), solution.sortList(ListNode.create()));
    }

    // 归并排序
    // 要求 O(n*log(n)) 时间复杂度，首先想到的就是分治思想。
    // 大致思想是： 1.将链表划分左右两部分--> 2.对左右链表进行排序 --> 3.将左右两个有序链表进行合并
    // 时间复杂度：O(nlog⁡n)，其中 n 是链表的长度。
    // 空间复杂度：O(log⁡n)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。
    static class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return head;
            }
            // 递归退出条件：即链表被划分后只剩一个结点
            if (head.next == null) {
                return head;
            }

            // 使用快慢指针进行链表划分
            ListNode fast = head;
            ListNode slow = head;
            ListNode previous = head;
            while (fast != null && fast.next != null) {
                previous = slow;
                fast = fast.next.next;
                slow = slow.next;
            }
            // 划分结果是：fast为原链表尾.next，slow为划分后链表右半部分表，previous为左半部分表尾结点，所以previous.next要为null
            previous.next = null;
            // 左半部分递归划分
            ListNode left = sortList(head);
            // 右半部分递归划分
            ListNode right = sortList(slow);
            // 合并两个有序链表
            return merge(left, right);
        }

        // 合并两个有序链表，只有一个结点的链表也是是有序的
        private ListNode merge(ListNode left, ListNode right) {
            ListNode dummy = new ListNode(0);
            ListNode node = dummy;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    node.next = left;
                    node = node.next;
                    left = left.next;
                } else {
                    node.next = right;
                    node = node.next;
                    right = right.next;
                }
            }
            if (left == null) {
                node.next = right;
            } else {
                node.next = left;
            }
            return dummy.next;
        }
    }

    // 自底向上归并排序（满足题目进阶）
    // 使用自底向上的方法实现归并排序，则可以达到 O(1) 的空间复杂度。
    // 首先求得链表的长度 length，然后将链表拆分成子链表进行合并。
    // 时间复杂度：O(nlog⁡n)，其中 n 是链表的长度。
    // 空间复杂度：O(1)。
    static class Solution2 {

        public ListNode sortList(ListNode head) {
            if (head == null) {
                return head;
            }
            // 计算链表长度
            int length = 0;
            ListNode node = head;
            while (node != null) {
                node = node.next;
                length++;
            }
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;

            // subLength是每段的长度
            for (int subLength = 1; subLength < length; subLength *= 2) {
                ListNode previous = dummyHead;
                ListNode current = dummyHead.next;

                // 两两分组
                while (current != null) {

                    ListNode head1 = current;
                    for (int i = 1; i < subLength && current.next != null; i++) {
                        current = current.next;
                    }

                    ListNode head2 = current.next;
                    current.next = null;
                    current = head2;

                    for (int i = 1; i < subLength && current != null && current.next != null; i++) {
                        current = current.next;
                    }

                    ListNode next = null;
                    if (current != null) {
                        next = current.next;
                        current.next = null;
                    }

                    // 合并后的有序链表
                    ListNode merged = merge(head1, head2);
                    previous.next = merged;

                    // previous 指向合并后的有序链表尾
                    while (previous.next != null) {
                        previous = previous.next;
                    }

                    current = next;
                }
            }
            return dummyHead.next;
        }

        // 合并两个有序链表，只有一个结点的链表也是是有序的
        private ListNode merge(ListNode left, ListNode right) {
            ListNode dummy = new ListNode(0);
            ListNode head = dummy;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    head.next = left;
                    head = head.next;
                    left = left.next;
                } else {
                    head.next = right;
                    head = head.next;
                    right = right.next;
                }
            }
            if (left == null) {
                head.next = right;
            } else {
                head.next = left;
            }
            return dummy.next;
        }
    }
}
