package yyl.leetcode.p08;

import java.util.ArrayList;
import java.util.List;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>叶子相似的树</h3> <br>
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。 <br>
 * 
 * <pre>
 *        [3]
 *    ┌────┴────┐
 *   [5]       [1]
 *  ┌─┴─┐     ┌─┴─┐
 * (6)  [2]  (9) (8)
 *     ┌─┴─┐
 *    (7) (4)
 * </pre>
 * 
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。 <br>
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。 <br>
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * 
 * <pre>
 * 示例 1：
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 
 * 示例 2：
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 * 
 * 示例 3：
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 * 
 * 示例 4：
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 * 
 * 示例 5：
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 * 
 * 提示：
 *     给定的两棵树可能会有 1 到 200 个结点。
 *     给定的两棵树上的值介于 0 到 200 之间。
 * </pre>
 */
public class P0872_LeafSimilarTrees {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.leafSimilar(//
                TreeNode.create("[3,5,1,6,2,9,8,null,null,7,4]"), //
                TreeNode.create("[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]")//
        ));
        Assert.assertTrue(solution.leafSimilar(//
                TreeNode.create("[1]"), //
                TreeNode.create("[1]")//
        ));
        Assert.assertFalse(solution.leafSimilar(//
                TreeNode.create("[1]"), //
                TreeNode.create("[2]")//
        ));
        Assert.assertTrue(solution.leafSimilar(//
                TreeNode.create("[1,2]"), //
                TreeNode.create("[2,2]")//
        ));
        Assert.assertFalse(solution.leafSimilar(//
                TreeNode.create("[1,2,3]"), //
                TreeNode.create("[1,3,2]")//
        ));
    }

    /**
     * <pre>
      * Definition for a binary tree node.
      * public class TreeNode {
      *     int val;
      *     TreeNode left;
      *     TreeNode right;
      *     TreeNode() {}
      *     TreeNode(int val) { this.val = val; }
      *     TreeNode(int val, TreeNode left, TreeNode right) {
      *         this.val = val;
      *         this.left = left;
      *         this.right = right;
      *     }
      * }
     * </pre>
     */
    // 深度优先搜索
    // 可以使用深度优先搜索的方法得到一棵树的「叶值序列」。
    // 时间复杂度：O(n1+n2)，其中 n1 和 n2 分别是两棵树的节点个数。
    // 空间复杂度：O(n1+n2)。空间复杂度主要取决于存储「叶值序列」的空间以及深度优先搜索的过程中需要使用的栈空间。
    static class Solution {

        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leafvals1 = new ArrayList<>();
            List<Integer> leafvals2 = new ArrayList<>();
            dfs(root1, leafvals1);
            dfs(root2, leafvals2);
            return leafvals1.equals(leafvals2);
        }

        private void dfs(TreeNode root, List<Integer> leafvals) {
            if (root.left == null && root.right == null) {
                leafvals.add(root.val);
            } else {
                if (root.left != null) {
                    dfs(root.left, leafvals);
                }
                if (root.right != null) {
                    dfs(root.right, leafvals);
                }
            }
        }
    }
}
