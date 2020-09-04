package yyl.leetcode.p02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>二叉树的所有路径</h3> <br>
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。<br>
 * 说明: 叶子节点是指没有子节点的节点。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [1,2,3,null,5]
 * 
 *       (1)
 *      /   \
 *    (2)   (3)
 *      \
 *      (5)
 * 
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * </pre>
 */
public class P0257_BinaryTreePaths {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.create("[1,2,3,null,5]");
        List<String> actual = solution.binaryTreePaths(root);
        List<String> expected = Arrays.asList("1->2->5", "1->3");
        Assert.assertEquals(expected, actual);
    }

    // 深度优先搜索（递归+回溯）
    // 深度优先搜索遍历二叉树，考虑当前的节点以及它的孩子节点。
    // 1、如果当前节点不是叶子节点，则在当前的路径末尾添加该节点，并继续递归遍历该节点的每一个孩子节点。
    // 2、如果当前节点是叶子节点，则在当前路径末尾添加该节点后就是一条从根节点到叶子节点的路径，将该路径加入到答案即可。
    // 3、搜索完成当前节点的子节点之后，把当前节点从路径移除（回溯）
    // 时间复杂度：O(N)，其中 N 表示节点数目。在深度优先搜索中每个节点会被访问一次
    // 空间复杂度：O(N)，需要一个队列记录节点路径
    static class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<Integer> path = new ArrayList<>();
            List<String> answer = new ArrayList<>();
            dfs(root, path, answer);
            return answer;
        }

        private void dfs(TreeNode root, List<Integer> path, List<String> answer) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                StringBuilder builder = new StringBuilder();
                for (Integer value : path) {
                    builder.append(value).append("->");
                }
                builder.append(root.val);
                answer.add(builder.toString());
                return;
            }
            path.add(root.val);
            dfs(root.left, path, answer);
            dfs(root.right, path, answer);
            path.remove(path.size() - 1);
        }
    }
}
