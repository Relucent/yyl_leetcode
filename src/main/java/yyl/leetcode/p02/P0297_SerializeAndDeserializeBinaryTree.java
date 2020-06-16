package yyl.leetcode.p02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>二叉树的序列化与反序列化</h3><br>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。<br>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。<br>
 * 
 * <pre>
 * 示例: 
 * 你可以将以下二叉树：
 * 
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 
 * 序列化为 "[1,2,3,null,null,4,5]"
 * </pre>
 * 
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */
public class P0297_SerializeAndDeserializeBinaryTree {

	public static void main(String[] args) {
		Codec codec = new Codec();
		TreeNode root = TreeNode.create("[1,2,3,null,null,4,5]");
		String serialized = codec.serialize(root);
		System.out.println(serialized);
		TreeNode deserialized = codec.deserialize(serialized);
		System.out.println(deserialized);
	}

	// 前序遍历(先根遍历)
	// 时间复杂度：O(n)
	// 空间复杂度：O(n)
	public static class Codec {

		private static final TreeNode NULL_DUMMY = new TreeNode(Integer.MIN_VALUE);

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			if (root == null) {
				return "[]";
			}
			Deque<TreeNode> queue = new ArrayDeque<>();
			queue.offer(root);
			StringBuilder builder = new StringBuilder();
			builder.append("[");

			// 尾部NULL计数，用来删除最后连续的null
			int nullTailCount = 0;
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					TreeNode node = queue.poll();
					if (node == NULL_DUMMY) {
						builder.append("null,");
						nullTailCount++;
					} else {
						nullTailCount = 0;
						builder.append(node.val).append(",");
						queue.offer(node.left != null ? node.left : NULL_DUMMY);
						queue.offer(node.right != null ? node.right : NULL_DUMMY);
					}
				}
			}

			// 删除尾部连续的null
			if (nullTailCount > 0) {
				builder.setLength(builder.length() - 5 * nullTailCount);// "null,".length()*nullTailCount
			}

			builder.setCharAt(builder.length() - 1, ']');
			return builder.toString();
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			data = data.substring(1, data.length() - 1);
			if (data.length() == 0) {
				return null;
			}
			Queue<TreeNode> queue = new ArrayDeque<>();

			// 分割字符串，获取根结点
			String[] parts = data.split(",");
			TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
			queue.add(root);
			int index = 1;
			while (!queue.isEmpty()) {

				// 获取双链表的头结点作为当前结点
				TreeNode node = queue.poll();

				// 判断左子节点是否为空
				// 判断是否还有结点可以构建的左子节点
				if (index == parts.length) {
					break;
				}
				{
					String part = parts[index++];
					if (!"null".equals(part)) {
						queue.add(node.left = new TreeNode(Integer.parseInt(part)));
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
						queue.add(node.right = new TreeNode(Integer.parseInt(part)));
					}
				}
			}
			return root;
		}
	}
}
