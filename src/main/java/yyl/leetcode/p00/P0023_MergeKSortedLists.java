package yyl.leetcode.p00;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>合并K个排序链表</h3><br>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。 <br>
 * 示例:<br>
 * 输入: [ 1->4->5, 1->3->4, 2->6 ]<br>
 * 输出: 1->1->2->3->4->4->5->6 <br>
 * Merge k Sorted Lists <br>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.<br>
 */
public class P0023_MergeKSortedLists {

    public static void main(String[] args) {
        ListNode[] lists = { //
                ListNode.create(1, 4, 7, 10), //
                ListNode.create(3, 6, 9, 12, 15), //
                ListNode.create(2, 5, 8, 11, 14),//
        };//
        Solution solution = new Solution();

        ListNode result = solution.mergeKLists(lists);
        System.out.println(result);
    }

    static class Solution {

        // 如果使用一般的循环会 提交测试遇到大数据量会 Time Limit Exceeded，所以需要进行优化
        // 优化思路：采用分治的方法，将链表不断将其划分(partition)，再将其归并(merge)
        // 两个有序链表的复杂度：O(n)，其中 n是两个链表中的总节点数。
        // 时间复杂度： O(NlogK)，其中 k 是链表的数目。
        // 空间复杂度：O(1)
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return mergeKLists(lists, 0, lists.length - 1);
        }

        private ListNode mergeKLists(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            if (left < right) {
                int min = (left + right) >> 1;// (left + right)/2
                return mergeTwoLists(//
                        mergeKLists(lists, left, min), //
                        mergeKLists(lists, min + 1, right)//
                );
            }
            return null;
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val > l2.val) {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }
}
