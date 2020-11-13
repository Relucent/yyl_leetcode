package yyl.leetcode.p03;

import yyl.leetcode.bean.ListNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>奇偶链表</h3><br>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。<br>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL 
 * 输出: 2->3->6->7->1->5->4->NULL
 * </pre>
 * 
 * 说明:<br>
 * ├应当保持奇数节点和偶数节点的相对顺序。<br>
 * └链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。<br>
 */
public class P0328_OddEvenLinkedList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = ListNode.create(1, 2, 3, 4, 5);
        ListNode expected = ListNode.create(1, 3, 5, 2, 4);
        ListNode actual = solution.oddEvenList(head);
        Assert.assertEquals(expected, actual);
    }

    // 一次遍历，分离节点后合并
    // 对于原始链表，每个节点都是奇数节点或偶数节点。
    // 头节点是奇数节点，头节点的后一个节点是偶数节点，相邻节点的奇偶性不同。
    // 因此可以将奇数节点和偶数节点分离成奇数链表和偶数链表，然后将偶数链表连接在奇数链表之后，合并后的链表即为结果链表。
    // 时间复杂度：O(n)，其中 n 是链表的节点数，需要遍历链表中的每个节点。
    // 空间复杂度：O(1)。
    static class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;
            while (even != null && even.next != null) {
                odd = odd.next = even.next;
                even = even.next = odd.next;
            }
            odd.next = evenHead;
            return head;
        }
    }
}
