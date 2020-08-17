package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>平衡二叉树</h3> <br>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。<br>
 * 本题中，一棵高度平衡二叉树定义为： 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。<br>
 * 
 * <pre>
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * 返回 true 。
 * 
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * 
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 *  
 * 返回 false 。
 * </pre>
 */
public class P0110_BalancedBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.isBalanced(TreeNode.create("[3,9,20,null,null,15,7]")));
        Assert.assertFalse(solution.isBalanced(TreeNode.create("[1,2,2,3,3,null,null,4,4]")));
    }

    // 自底向上的递归
    // 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
    // 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
    // 时间复杂度：O(n)，其中 n 是二叉树中的节点个数。最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)。
    // 空间复杂度：O(n)，空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n。
    static class Solution {

        public boolean isBalanced(TreeNode root) {
            return height(root) != -1;
        }

        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = height(root.left);
            int right = height(root.right);
            if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }
}
