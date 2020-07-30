package yyl.leetcode.p01;

import java.util.LinkedList;
import java.util.Queue;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>对称二叉树</h3> <br>
 * 给定一个二叉树，检查它是否是镜像对称的。<br>
 * 
 * <pre>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 * 
 * 进阶： 你可以运用递归和迭代两种方法解决这个问题吗？<br>
 */
public class P0101_SymmetricTree {

	public static void main(String[] args) {
		Solution solution = new Solution();
		Assert.assertTrue(solution.isSymmetric(TreeNode.create("[]")));
		Assert.assertTrue(solution.isSymmetric(TreeNode.create("[1,2,2,3,4,4,3]")));
		Assert.assertFalse(solution.isSymmetric(TreeNode.create("[1,2,2,null,3,null,3]")));
		Assert.assertFalse(solution.isSymmetric(TreeNode.create("[1,2,2,2,null,2]")));
		Assert.assertFalse(solution.isSymmetric(TreeNode.create("[9,-42,-42,null,76,76,null,null,13,null,13]")));
	}

	// 递归
	// 两个树互为镜像：
	// 它们的两个根结点具有相同的值
	// 每个树的右子树都与另一个树的左子树镜像对称
	// 时间复杂度：O(n)，遍历整棵树。
	// 空间复杂度：O(n)，这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 n。
	static class Solution {
		public boolean isSymmetric(TreeNode root) {
			return check(root, root);
		}

		private boolean check(TreeNode left, TreeNode right) {
			if (null == left && null == right) {
				return true;
			}
			if (null == left || null == right) {
				return false;
			}
			return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
		}
	}

	// 迭代
	// 使用队列将递归程序改写成迭代
	// 时间复杂度：O(n)，遍历整棵树。
	// 空间复杂度：O(n)，需要用一个队列来维护节点，每个节点最多进队一次，出队一次，队列中最多不会超过 n。
	static class Solution2 {
		public boolean isSymmetric(TreeNode root) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			queue.offer(root);
			while (!queue.isEmpty()) {
				TreeNode left = queue.poll();
				TreeNode right = queue.poll();
				if (null == left && null == right) {
					continue;
				}
				if ((null == left || null == right) || (left.val != right.val)) {
					return false;
				}
				queue.offer(left.left);
				queue.offer(right.right);
				queue.offer(left.right);
				queue.offer(right.left);
			}
			return true;
		}
	}
}
