package yyl.leetcode.p01;

import yyl.leetcode.bean.ListNode;
import yyl.leetcode.bean.TreeNode;

/**
 * <h3>有序链表转换二叉搜索树</h3><br>
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。<br>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。<br>
 * 
 * <pre>
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * 
 *       0
 *      / \
 *    -3   9
 *    /   /
  * -10  5
 * </pre>
 */
public class P0109_ConvertSortedListToBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.sortedListToBST(ListNode.create(-10, -3, 0, 5, 9)));
    }

    // 分治+快慢指针
    // 时间复杂度：O(n*log⁡{n})，其中 n 是链表的长度。设长度为 n 的链表构造二叉搜索树的时间为 T(n)，递推式为 T(n)=2*⋅T(n/2)+O(n)，根据主定理，T(n)=O(n*log⁡{n})。
    // 空间复杂度：O(log⁡{n})，平衡二叉树的高度为 O(log⁡{n})，即为递归过程中栈的最大深度，也就是需要的空间。
    static class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) {
                return null;
            }
            // 使用快慢指针查询中间节点（slow指向中间节点，last指向中间元素之前的节点）
            ListNode slow = head;
            ListNode fast = head;
            ListNode last = null;
            while (fast.next != null && fast.next.next != null) {
                last = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            // 中值作为根节点
            TreeNode node = new TreeNode(slow.val);

            // 如果last为null，说明二分之后，前面没有节点
            if (last != null) {
                last.next = null;
                node.left = sortedListToBST(head);
            }
            node.right = sortedListToBST(slow.next);
            return node;
        }
    }

    // 分治+中序遍历优化
    // 分析二叉搜索树的结构：
    // 中位数节点对应的编号为 mid=(left+right+1)；
    // 左右子树对应的编号范围分别为 [left,mid−1] 和 [mid+1,right]；
    // 如果 left>right，那么遍历到的位置对应着一个空节点，否则对应着二叉搜索树中的一个节点。
    // 题目给定了它的中序遍历结果，那么只要对其进行中序遍历，就可以还原出整棵二叉搜索树。
    // 空间复杂度：O(log⁡{n})，二分查找，但是省略了快慢指针的检索。
    // 空间复杂度：O(log⁡{n})，平衡二叉树的高度为 O(log⁡{n})，即为递归过程中栈的最大深度，也就是需要的空间。
    static class Solution2 {

        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) {
                return null;
            }
            int length = getLength(head);
            ListNode[] reference = { head };
            return buildTree(0, length - 1, reference);
        }

        private TreeNode buildTree(int left, int right, ListNode[] reference) {
            if (left > right) {
                return null;
            }
            int mid = (left + right + 1) / 2;
            TreeNode leftNode = buildTree(left, mid - 1, reference);
            TreeNode root = new TreeNode(reference[0].val);
            reference[0] = reference[0].next;
            root.left = leftNode;
            root.right = buildTree(mid + 1, right, reference);
            return root;
        }

        private int getLength(ListNode head) {
            int count = 0;
            while (head != null) {
                head = head.next;
                count++;
            }
            return count;
        }
    }
}
