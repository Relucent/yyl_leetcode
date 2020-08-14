package yyl.leetcode.p01;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>路径总和</h3><br>
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。 <br>
 * 说明: 叶子节点是指没有子节点的节点。<br>
 * 
 * <pre>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * 
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * </pre>
 */
public class P0112_PathSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hasPathSum(TreeNode.create("[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 22));
    }

    // 递归（深度优先搜索）
    // 将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
    // 1、若当前节点就是叶子节点，那么直接判断 sum 是否等于 val 即可。
    // 2、若当前节点不是叶子节点，只需要递归地询问它的子节点是否能满足条件即可。
    // 3、注意空树的结果为 false
    // 时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。
    // 空间复杂度：O(H)，其中 H 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(log⁡{N})。
    static class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            // 根节点不能为空
            if (root == null) {
                return false;
            }
            // 当前是叶子节点，直接判断 sum 是否等于 val
            if (root.left == null && root.right == null) {
                return sum == root.val;
            }
            // 前节点不是叶子节点，归地询问它的子节点是否能满足条件
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
}