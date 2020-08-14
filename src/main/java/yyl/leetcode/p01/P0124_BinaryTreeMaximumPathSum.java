package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>二叉树中的最大路径和</h3><br>
 * 给定一个非空二叉树，返回其最大路径和。<br>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,2,3]
 * 
 *        1
 *       / \
 *      2   3
 * 输出: 6
 * 
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 * </pre>
 */
public class P0124_BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxPathSum(TreeNode.create("[1,2,3]")));// 6
        System.out.println(solution.maxPathSum(TreeNode.create("[-10,9,20,null,null,15,7]")));// 42
    }

    // 首先解释题目意思：
    // 这个题目不是求路径的个数，而是求最大路径和。
    // 路径和：从一个起始节点出发到另一个节点的所有路径的节点的和
    // 解题思路
    // 1、空节点的最大贡献值等于 0。
    // 2、非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和
    // 3、对于叶节点而言，最大贡献值等于节点值
    // 时间复杂度：O(N)，其中 N 是二叉树中的节点个数。对每个节点访问不超过 2 次。
    // 空间复杂度：O(N)，其中 N 是二叉树中的节点个数。空间复杂度主要取决于递归调用层数，最大层数等于二叉树的高度，最坏情况下，二叉树的高度等于二叉树中的节点个数。
    static class Solution {
        public int maxPathSum(TreeNode root) {
            int[] result = { Integer.MIN_VALUE };
            dfs(root, result);
            return result[0];
        }

        private int dfs(TreeNode node, int[] result) {
            if (node == null) {
                return 0;
            }
            int leftGain = dfs(node.left, result);
            int rightGain = dfs(node.right, result);
            int currentGain = leftGain + rightGain + node.val;
            result[0] = Math.max(result[0], currentGain);
            return Math.max(Math.max(leftGain, rightGain) + node.val, 0);
        }
    }
}
