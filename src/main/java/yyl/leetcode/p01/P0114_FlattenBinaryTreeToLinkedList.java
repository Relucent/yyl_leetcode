package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树展开为链表</h3><br>
 * 给定一个二叉树，原地将它展开为一个单链表。<br>
 * 
 * <pre>
 * 例如，给定二叉树
 * 
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * </pre>
 */
public class P0114_FlattenBinaryTreeToLinkedList {

	public static void main(String[] args) {
		Solution solution = new Solution();
		TreeNode root = TreeNode.create("[1,2,5,3,4,null,6]");
		solution.flatten(root);
		TreeNode expected = TreeNode.create("[1,null,2,null,3,null,4,null,5,null,6]");
		Assert.assertEquals(expected, root);
	}

	// 递归
	// 后序遍历，将左子树作为当前节点的右子节点，然后叶节点指向将原先的的右子树跟节点
	// 时间复杂度：O(n)，其中 n 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次。遍历获得子树叶节点又需要遍历一次。
	// 空间复杂度：O(1)
	static class Solution {
		public void flatten(TreeNode root) {
			if (root == null) {
				return;
			}
			flatten(root.left);
			flatten(root.right);
			TreeNode temp = root.right;
			root.right = root.left;
			root.left = null;
			while (root.right != null) {
				root = root.right;
			}
			root.right = temp;
		}
	}
}
