package yyl.leetcode.p01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树的锯齿形层序遍历</h3><br>
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。<br>
 * 
 * <pre>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 
 * 返回锯齿形层序遍历如下：
 * 
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * </pre>
 */
public class P0103_BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.create("[3,9,20,null,null,15,7]");
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7));
        List<List<Integer>> actual = solution.zigzagLevelOrder(root);
        Assert.assertEquals(expected, actual);
    }

    // 广度优先遍历 + 集合翻转
    // 广度优先遍历输出节点内容，按层数的奇偶来决定每一层的输出顺序，可以设置一个变量来判断该层是否需要做翻转
    // 时间复杂度：O(N)，其中 N 为二叉树的节点数。每个节点会且仅会被遍历一次。
    // 空间复杂度：O(N)，队列中元素的个数不超过 n 个，故渐进空间复杂度为 O(n)。
    static class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> answer = new ArrayList<>();
            if (root == null) {
                return answer;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            boolean reverse = false;
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
                if (reverse) {
                    Collections.reverse(values);
                }
                answer.add(values);
                reverse = !reverse;
            }
            return answer;
        }
    }
}
