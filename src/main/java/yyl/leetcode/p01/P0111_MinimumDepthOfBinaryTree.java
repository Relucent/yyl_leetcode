package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树的最小深度</h3> <br>
 * 给定一个二叉树，找出其最小深度。 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。<br>
 * 说明: 叶子节点是指没有子节点的节点。<br>
 * 
 * <pre>
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * 返回它的最小深度  2.
 * </pre>
 */
public class P0111_MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.minDepth(TreeNode.create("[3,9,20,null,null,15,7]")));
        Assert.assertEquals(2, solution.minDepth(TreeNode.create("[1,2]")));
        Assert.assertEquals(2, solution.minDepth(TreeNode.create("[1,null,2]")));
    }

    // 深度优先搜索
    // 使用深度优先搜索的方法，遍历整棵树，记录最小深度。
    // 对于每一个非叶子节点，只需要分别计算其左右子树的最小叶子节点深度。这样就可以递归地解决该问题。
    // 时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。
    // 空间复杂度：O(H)，其中 H 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(log⁡{N})。
    static class Solution {
        public int minDepth(TreeNode root) {
            if (null == root) {
                return 0;
            }
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            if (left == 0) {
                return right + 1;
            }
            if (right == 0) {
                return left + 1;
            }
            return Math.min(left, right) + 1;
        }
    }
}
