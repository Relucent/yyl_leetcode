package yyl.leetcode.p00;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>二叉树的中序遍历</h3><br>
 * 给定一个二叉树，返回它的中序 遍历。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * </pre>
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？<br>
 */
public class P0094_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.create("[1,null,2,3]");
        System.out.println(solution.inorderTraversal(root));
    }

    // 迭代
    // 时间复杂度：O(N)，虽然时间复杂度和递归一致，但是其实每个节点需要入栈出栈，遍历了两次
    // 时间复杂度：O(N)，存储返回结果
    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            ArrayDeque<TreeNode> stack = new ArrayDeque<>();
            do {
                // root(或者当前右子节点)不为空，迭代遍历左侧子树，入栈
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                // 栈不为空
                if (!stack.isEmpty()) {
                    // 弹出，这个node是未处理的最左节点
                    TreeNode node = stack.pop();
                    // 处理
                    result.add(node.val);
                    // 然后将root设置为右子节点
                    root = node.right;
                }
            } while (!stack.isEmpty() || root != null);
            return result;
        }
    }

    // 递归
    // 时间复杂度：O(N)，每个节点遍历一次
    // 时间复杂度：O(N)，存储返回结果
    static class Solution1 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            ldr(root, result);
            return result;
        }

        private void ldr(TreeNode root, List<Integer> result) {
            if (root != null) {
                ldr(root.left, result);
                result.add(root.val);
                ldr(root.right, result);
            }
        }
    }
}
