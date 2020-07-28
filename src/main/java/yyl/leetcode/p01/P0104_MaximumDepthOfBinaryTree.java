package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树的最大深度</h3><br>
 * 给定一个二叉树，找出其最大深度。<br>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。<br>
 * 说明: 叶子节点是指没有子节点的节点。<br>
 * 
 * <pre>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  返回它的最大深度 3 。
 * </pre>
 */
public class P0104_MaximumDepthOfBinaryTree {

	public static void main(String[] args) {
		Solution solution = new Solution();
		TreeNode root = TreeNode.create("[3,9,20,null,null,15,7]");
		Assert.assertEquals(solution.maxDepth(root), 3);
	}

	// 递归 （深度优先搜索）
	// 如果根节点左子树和右子树的最大深度 为 left 和 right，那么该二叉树的最大深度即为 max(left, right)+1
	// 使用递归遍历，用同样的方法计算左子树和右子树的最大深度，递归到空节点终止（空树深度为 0）。
	// 时间复杂度：O(n)，其中 n 为二叉树节点的个数，每个节点在递归中只被遍历一次。
	// 空间复杂度：O(height)，其中 height 表示二叉树的高度。递归函数需要栈空间，而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
	static class Solution {
		public int maxDepth(TreeNode root) {
			return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
		}
	}
}
