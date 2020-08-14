package yyl.leetcode.lcci;

import java.util.HashSet;
import java.util.Set;

import yyl.leetcode.bean.ListNode;

/**
 * <h3>面试题 02.01. 移除重复节点</h3><br>
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。<br>
 * 
 * <pre>
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 
 * 提示：
 * 
 *     链表长度在[0, 20000]范围内。
 *     链表元素在[0, 20000]范围内。
 * </pre>
 * 
 * 进阶： 如果不得使用临时缓冲区，该怎么解决？<br>
 */
public class L0201_RemoveDuplicateNodeLcci {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateNodes(ListNode.create(1, 2, 3, 3, 2, 1)));
        System.out.println(solution.removeDuplicateNodes(ListNode.create(1, 1, 1, 1, 2)));
        System.out.println(solution.removeDuplicateNodes(null));
    }

    // 哈希表
    // 对给定的链表进行一次遍历，并用一个哈希集合（HashSet）来存储所有出现过的节点。
    // 从链表的头节点 head 开始进行遍历，遍历的指针记为 node。
    // 由于头节点一定不会被删除，因此我们可以枚举待移除节点的前驱节点，减少编写代码的复杂度。
    // 需要考虑链表为null的情况
    // 时间复杂度：O(N)，其中 N 是给定链表中节点的数目。
    // 空间复杂度：O(N)。在最坏情况下，给定链表中每个节点都不相同，哈希表中需要存储所有的 N 个值。
    static class Solution {
        public ListNode removeDuplicateNodes(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode node = head;
            Set<Integer> set = new HashSet<>();
            set.add(node.val);
            while (node.next != null) {
                if (set.add(node.next.val)) {
                    node = node.next;
                } else {
                    node.next = node.next.next;
                }
            }
            return head;
        }
    }

    // 两重循环 (进阶)
    // 因为不使用临时缓冲区，所以需要两层循环（使用时间换空间）
    // 第一重循环从链表的头节点开始，枚举一个保留的节点。
    // 第二重循环从枚举的保留节点开始，到链表的末尾结束，将所有与保留节点相同的节点全部移除。
    // 时间复杂度：O(N^2)，其中 N是给定链表中节点的数目
    // 空间复杂度：O(1)
    static class Solution1 {
        public ListNode removeDuplicateNodes(ListNode head) {
            ListNode cursor = head;
            while (cursor != null) {
                ListNode node = cursor;
                while (node.next != null) {
                    if (cursor.val == node.next.val) {
                        node.next = node.next.next;
                    } else {
                        node = node.next;
                    }
                }
                cursor = cursor.next;
            }
            return head;
        }
    }
}
