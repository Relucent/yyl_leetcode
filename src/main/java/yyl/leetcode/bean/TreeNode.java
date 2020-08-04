package yyl.leetcode.bean;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + val;
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		TreeNode other = (TreeNode) object;
		if (left == null) {
			if (other.left != null) {
				return false;
			}
		} else if (!left.equals(other.left)) {
			return false;
		}
		if (right == null) {
			if (other.right != null) {
				return false;
			}
		} else if (!right.equals(other.right)) {
			return false;
		}
		return val == other.val;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.add(this);
		int level = 1;
		builder.append("[");
		TreeNode dummyNull = new TreeNode(Integer.MIN_VALUE);
		int nullTails = 0;
		while (!queue.isEmpty()) {
			if (level > 5) {
				nullTails = 0;
				builder.append("...,");
				break;
			}
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node == dummyNull) {
					nullTails++;
					builder.append("null,");
				} else {
					nullTails = 0;
					builder.append(node.val).append(",");
					TreeNode left = node.left;
					TreeNode right = node.right;
					queue.add(left == null ? dummyNull : left);
					queue.add(right == null ? dummyNull : right);
				}
			}
			level++;
		}
		if (nullTails > 0) {
			// "null,".length() = 5
			builder.setLength(builder.length() - 5 * nullTails);
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * 创建二叉树
	 * @param values 二叉树节点数据
	 * @return 二叉树跟节点
	 */
	public static TreeNode create(String input) {
		// 处理"[","]"
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return null;
		}

		// 分割字符串，获取根结点
		String[] parts = input.split(",");
		String item = parts[0];
		TreeNode root = new TreeNode(Integer.parseInt(item));
		// 将根结点加入到双链表中
		Queue<TreeNode> nodeQueue = new ArrayDeque<>();
		nodeQueue.add(root);

		int index = 1;
		// 遍历双链表
		while (!nodeQueue.isEmpty()) {
			// 获取双链表的头结点作为当前结点
			TreeNode node = nodeQueue.poll();

			// 判断左子节点是否为空
			// 判断是否还有结点可以构建的左子节点
			if (index == parts.length) {
				break;
			}
			{
				String part = parts[index++];
				if (!"null".equals(part)) {
					nodeQueue.add(node.left = new TreeNode(Integer.parseInt(part)));
				}
			}

			// 判断右子节点是否为空
			// 判断是否还有结点可以构建的右子节点
			if (index == parts.length) {
				break;
			}
			{
				String part = parts[index++];
				if (!"null".equals(part)) {
					nodeQueue.add(node.right = new TreeNode(Integer.parseInt(part)));
				}
			}
		}
		return root;
	}

	/**
	 * 前序遍历，根左右(DLR)
	 * @param root 根节点
	 * @param action 回调动作
	 */
	public static void preorderTraversal(TreeNode root, Consumer<TreeNode> action) {
		if (root != null) {
			action.accept(root);
			preorderTraversal(root.left, action);
			preorderTraversal(root.right, action);
		}
	}

	/**
	 * 中序遍历，左根右(LDR)
	 * @param root 根节点
	 * @param action 回调动作
	 */
	public static void inorderTraversal(TreeNode root, Consumer<TreeNode> action) {
		if (root != null) {
			preorderTraversal(root.left, action);
			action.accept(root);
			preorderTraversal(root.right, action);
		}
	}

	/**
	 * 后序遍历，左右根(LRD)
	 * @param root 根节点
	 * @param action 回调动作
	 */
	public static void postorderTraversal(TreeNode root, Consumer<TreeNode> action) {
		if (root != null) {
			preorderTraversal(root.left, action);
			preorderTraversal(root.right, action);
			action.accept(root);
		}
	}
}
