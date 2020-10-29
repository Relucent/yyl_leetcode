package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>求根到叶子节点数字之和</h3><br>
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。<br>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。<br>
 * 计算从根到叶子节点生成的所有数字之和。<br>
 * 说明: 叶子节点是指没有子节点的节点。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 
 * 示例 2:
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 * </pre>
 */
public class P0129_SumRootToLeafNumbers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(25, solution.sumNumbers(TreeNode.create("[1,2,3]")));
        Assert.assertEquals(1026, solution.sumNumbers(TreeNode.create("[4,9,0,5,1]")));
    }

    // 深度优先搜索（中序遍历）
    // 从根节点开始，遍历每个节点，如果遇到叶子节点，则将叶子节点对应的数字加到数字之和。
    // 如果当前节点不是叶子节点，则计算其子节点对应的数字，然后对子节点递归遍历。
    // 时间复杂度：O(n) ，其中 n 是二叉树的节点个数。对每个节点访问一次。
    // 空间复杂度：O(n)，其中 n是二叉树的节点个数。空间复杂度主要取决于递归调用的栈空间，递归栈的深度等于二叉树的高度，最坏情况下，二叉树的高度等于节点个数，空间复杂度为 O(n)。
    static class Solution {

        public int sumNumbers(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode root, int prevSum) {
            if (root == null) {
                return 0;
            }
            int sum = prevSum * 10 + root.val;
            if (root.left == null && root.right == null) {
                return sum;
            }
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
}
