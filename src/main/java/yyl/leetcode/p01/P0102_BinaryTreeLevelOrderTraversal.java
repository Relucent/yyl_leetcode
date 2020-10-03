package yyl.leetcode.p01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树的层序遍历
 * <h3><br>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。<br>
 * 
 * <pre>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * 返回其层次遍历结果：
 * 
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * </pre>
 */
public class P0102_BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.create("[3,9,20,null,null,15,7]");
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(3), Arrays.asList(9, 20), Arrays.asList(15, 7));
        List<List<Integer>> actual = solution.levelOrder(root);
        Assert.assertEquals(expected, actual);
    }

    // 广度优先搜索
    // 时间复杂度：O(n)，每个点进队出队各一次， 故渐进时间复杂度为 O(n)。
    // 空间复杂度：O(n)，队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> answer = new ArrayList<>();
            if (root == null) {
                return answer;
            }
            Queue<TreeNode> deque = new LinkedList<>();
            deque.offer(root);
            while (!deque.isEmpty()) {
                List<Integer> values = new ArrayList<>();
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    values.add(node.val);
                    if (node.left != null) {
                        deque.offer(node.left);
                    }
                    if (node.right != null) {
                        deque.offer(node.right);
                    }
                }
                answer.add(values);
            }
            return answer;
        }
    }
}
