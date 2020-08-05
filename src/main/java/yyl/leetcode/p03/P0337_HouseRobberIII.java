package yyl.leetcode.p03;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>打家劫舍 III</h3><br>
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。<br>
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。<br>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 * 
 *      3
 *     / \
 *    2   3
 *     \   \ 
 *      3   1
 * 
 * 输出: 7 
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 * 
 *      3
 *     / \
 *    4   5
 *   / \   \ 
 *  1   3   1
 * 
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * </pre>
 */
public class P0337_HouseRobberIII {

	public static void main(String[] args) {
		Solution solution = new Solution();
		Assert.assertEquals(7, solution.rob(TreeNode.create("[3,2,3,null,3,null,1]")));
		Assert.assertEquals(9, solution.rob(TreeNode.create("[3,4,5,1,3,null,1]")));
	}

	// 深度优先遍历+动态规划
	// 每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷
	// 当前节点选择偷时，那么两个孩子节点就不能选择偷了
	// 当前节点选择不偷时，那么两个孩子节点需要返回最多钱的情况(两个孩子节点偷不偷没关系)
	// 我们使用一个大小为 2 的数组来表示 dp = int[2]； 0 代表不偷，1 代表可能偷
	// 任何一个节点能偷到的最大钱的状态可以定义为
	// 当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子偷，能得到最多的钱+ 右孩子偷，能得到最多的钱
	// 当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子不偷，能得到最多的钱 + 右孩子不偷，能得到最多的钱 + 当前节点的钱数
	// 当前节点可能偷：max(当前节点选择不偷,当前节点选择偷)
	// 时间复杂度：O(n)。n为二叉树的节点个数，每个节点遍历一次。
	// 空间复杂度：O(n)。但是栈空间的使用代价依旧是 O(n)。
	static class Solution {

		public int rob(TreeNode root) {
			return dfs(root)[1];
		}

		private int[] dfs(TreeNode root) {
			if (root == null) {
				return new int[] { 0, 0 };
			}
			int[] left = dfs(root.left);
			int[] right = dfs(root.right);
			// 不偷
			int x = left[1] + right[1];
			// 偷
			int y = (root.val + left[0] + right[0]);
			// 可能偷
			int z = Math.max(x, y);
			return new int[] { x, z };
		}
	}
}
