package yyl.leetcode.p01;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>环形链表 II</h3><br>
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。<br>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。<br>
 * 说明：不允许修改给定的链表。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * </pre>
 * 
 * 进阶： 你是否可以不用额外空间解决此题？<br>
 */
public class P0142_LinkedListCycleII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        {
            ListNode head = buildCycle(ListNode.create(3, 2, 0, -4), 1);
            ListNode node = solution.detectCycle(head);
            assertPosEquals(head, 1, node);
        }
        {
            ListNode head = buildCycle(ListNode.create(1, 2), 0);
            ListNode node = solution.detectCycle(head);
            assertPosEquals(head, 0, node);
        }
        {
            ListNode head = buildCycle(ListNode.create(1), -1);
            ListNode node = solution.detectCycle(head);
            assertPosEquals(head, -1, node);
        }
    }

    private static ListNode buildCycle(ListNode head, int pos) {
        if (pos == -1) {
            return head;
        }
        ListNode tail = head;
        ListNode back = null;
        int index = 0;
        for (ListNode next = head; next != null; next = next.next) {
            tail = next;
            if (index == pos) {
                back = next;
            }
            index++;
        }
        tail.next = back;
        return head;
    }

    private static void assertPosEquals(ListNode head, int pos, ListNode node) {
        if (node == null) {
            if (pos != -1) {
                throw new AssertionError();
            }
            return;
        }
        int index = 0;
        for (ListNode next = head; next != null; next = next.next) {
            if (next == node) {
                if (index == pos) {
                    return;
                }
            }
            index++;
        }
        throw new AssertionError();
    }

    // 三指针
    // 首先创建两个指针，一个快指针和一个慢指针，快指针每次移动两个节点而慢指针则每次移动一个节点。
    // 如果链表中不存在环，则快指针会先于慢指针到达链表的尾部，两个指针永远也不可能“相遇”；如果链表中存在环，则快指针一定会“追上”慢指针。
    // 设：
    // 链表中环外部分的长度为 a，慢指针进入环后，又走了 b的距离与 快指针相遇，慢指针还剩 b距离到环开头。（环距离为 b+c）
    // 此时，快指针已经走完了环的 x 圈，因此它走过的总距离为：
    // a+k(b+c)+b = a+(k+1)b+xc
    // 任意时刻，快指针走过的距离都为慢指针的 2倍，慢指针走过的距离为 a+b，可得：
    // a+(k+1)b+xc = 2(a+b)
    // 化简：
    // a=c+(x−1)(b+c)
    // 可以看出相遇点到入环点的距离加上 n−1圈的环长(a+c)，恰好等于从链表头部到入环点的距离。
    // 因此，当发现快指针与 慢指针相遇时，再额外使用一个指针指向链表头部；随后它和慢指针每次向后移动一个位置，最终，它们会在入环点相遇。
    // 时间复杂度：O(n)，其中 n 是链表中的节点数。
    // 空间复杂度：O(1)，只需要存储两个节点的引用
    public static class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    ListNode next = head;
                    while (next != slow) {
                        next = next.next;
                        slow = slow.next;
                    }
                    return next;
                }
            }
            return null;
        }
    }
}
