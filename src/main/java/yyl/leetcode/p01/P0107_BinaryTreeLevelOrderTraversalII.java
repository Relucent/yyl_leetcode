package yyl.leetcode.p01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树的层序遍历 II</h3><br>
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 <br>
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）<br>
 * 
 * <pre>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * </pre>
 */

public class P0107_BinaryTreeLevelOrderTraversalII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.create("[3,9,20,null,null,15,7]");
        List<List<Integer>> actual = solution.levelOrderBottom(root);
        List<List<Integer>> expected = Arrays.asList(//
                Arrays.asList(15, 7), //
                Arrays.asList(9, 20), //
                Arrays.asList(3)//
        );

        Assert.assertEquals(expected, actual);
    }

    // 广度优先搜索
    // 树的层次遍历可以使用广度优先搜索实现。从根节点开始搜索，每次遍历同一层的全部节点，使用一个列表存储该层的节点值。
    // 时间复杂度：O(n)，其中 n 是二叉树中的节点个数。每个节点访问一次。
    // 空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度取决于队列开销，队列中的节点个数不会超过 n 。
    static class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> answer = new LinkedList<>();
            if (root == null) {
                return answer;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> values = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    values.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                answer.add(0, values);
            }
            return answer;
        }
    }
}
