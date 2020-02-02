package yyl.leetcode.p00;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3></h3><br>
 * 给定两个二叉树，编写一个函数来检验它们是否相同。<br>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *         [1,2,3],   [1,2,3]
 * 输出: true
 * 
 * 示例 2:
 * 输入:      1          1
 *           /           \
 *          2             2
 * 
 *         [1,2],     [1,null,2]
 * 输出: false
 * 
 * 示例 3:
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *         [1,2,1],   [1,1,2]
 * 输出: false
 * </pre>
 */
public class P0100_SameTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isSameTree(//
                TreeNode.create("[1,2,3]"), //
                TreeNode.create("[1,2,3]")//
        ));
        System.out.println(solution.isSameTree(//
                TreeNode.create("[1,2,1]"), //
                TreeNode.create("[1,1,2]")//
        ));
    }

    // 递归
    // 判断两个二叉树是否相等，只需要判断三个条件：
    // 1、两个根结点相等
    // 2、两个左结点相等
    // 3、两个右结点相等
    // 时间复杂度：O(N)，N为节点数，遍历全部节点
    // 空间复杂度：O(h)，h为树的深度
    static class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            return (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        }
    }
}
