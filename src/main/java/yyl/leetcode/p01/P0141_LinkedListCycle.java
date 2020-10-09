package yyl.leetcode.p01;

import java.util.HashSet;
import java.util.Set;

import yyl.leetcode.bean.ListNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>环形链表</h3><br>
 * 给定一个链表，判断链表中是否有环。<br>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。<br>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。<br>
 * 进阶： 你能用 O(1)（即，常量）内存解决此问题吗？<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * </pre>
 * 
 * 提示：<br>
 * 1、 链表中节点的数目范围是 [0, 104]<br>
 * 2、 -105 <= Node.val <= 105<br>
 * 3、 pos 为 -1 或者链表中的一个 有效索引 。<br>
 * <br>
 */
public class P0141_LinkedListCycle {

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        Assert.assertTrue(solution.hasCycle(cycle(ListNode.create(3, 2, 0, -4), 1)));
        Assert.assertTrue(solution.hasCycle(cycle(ListNode.create(3, 2, 0, -4), 0)));
        Assert.assertFalse(solution.hasCycle(cycle(ListNode.create(3, 2, 0, -4), -1)));
    }

    private static ListNode cycle(ListNode head, int pos) {
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

    // 双指针
    // 两个指针，一个快指针和一个慢指针，快指针每次移动两个节点而慢指针则每次移动一个节点。如果链表中不存在环，则快指针会先于慢指针到达链表的尾部，两个指针永远也不可能“相遇”；相反，如果链表中存在环，则快指针一定会“追上”慢指针。
    // 时间复杂度：O(n)，其中 n 是链表中的节点数。
    // 当链表中不存在环时，快指针将先于慢指针到达链表尾部（最多只需要 n/2 次），时间复杂度为 O(n)
    // 当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 n+k 次（k为环形部分的长度 ）。
    // 空间复杂度：O(1)，只需要存储两个节点的引用
    static class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }
    }

    // 哈希表
    // 判断一个链表是否包含环，可以转化为判断是否有一个节点之前已经出现过。
    // 时间复杂度：O(n)，其中 n 为链表的节点数，因为哈希表的查找和添加操作的时间复杂度是 O(1) 的，所以整体的时间复杂度是 O(n)
    // 空间复杂度：O(n)，最多需要保存 n 个节点的引用
    static class Solution1 {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> seen = new HashSet<ListNode>();
            while (head != null) {
                if (!seen.add(head)) {
                    return true;
                }
                head = head.next;
            }
            return false;
        }
    }
}
