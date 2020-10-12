package yyl.leetcode.p05;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉搜索树的最小绝对差</h3><br>
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。<br>
 * 
 * <pre>
 * 示例：
 * 输入：
 *    1
 *     \
 *      3
 *     /
 *    2
 * 输出：
 * 1
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * </pre>
 * 
 * 提示： 树中至少有 2 个节点。<br>
 */
public class P0530_MinimumAbsoluteDifferenceInBst {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // Assert.assertEquals(1, solution.getMinimumDifference(TreeNode.create("[1,null,3,2]")));
        Assert.assertEquals(9, solution.getMinimumDifference(TreeNode.create("[236,104,701,null,227,null,911]")));
    }

    // 中序遍历
    // 二叉搜索树特性：若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值。
    // 二叉搜索树的中序遍历得到的值序列是递增有序的（左 < 根 < 右），因此，使用中序遍历，比较相邻两个节点的差值即可一次遍历得到结果。
    // 时间复杂度：O(n)，其中 n 为二叉搜索树节点的个数。每个节点在中序遍历中都会被访问一次且只会被访问一次，因此总时间复杂度为 O(n)。
    // 空间复杂度：O(n)。递归函数的空间复杂度取决于递归的栈深度，而栈深度在二叉搜索树为一条链的情况下会达到 O(n) 级别。
    static class Solution {
        public int getMinimumDifference(TreeNode root) {
            Reference reference = new Reference();
            inorder(root, reference);
            return reference.result;
        }

        private void inorder(TreeNode root, Reference reference) {
            if (root == null) {
                return;
            }
            inorder(root.left, reference);
            if (reference.prev != Integer.MIN_VALUE) {
                reference.result = Integer.min(root.val - reference.prev, reference.result);
            }
            reference.prev = root.val;
            inorder(root.right, reference);
        }

        private static class Reference {
            int prev = Integer.MIN_VALUE;
            int result = Integer.MAX_VALUE;
        }
    }
}
