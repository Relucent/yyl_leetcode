package yyl.leetcode.p00;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>验证二叉搜索树</h3> <br>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。 <br>
 * 假设一个二叉搜索树具有如下特征： <br>
 * 节点的左子树只包含小于当前节点的数。 <br>
 * 节点的右子树只包含大于当前节点的数。 <br>
 * 所有左子树和右子树自身必须也是二叉搜索树。 <br>
 * 
 * <pre>
 * 例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * </pre>
 * 
 * <br>
 */
public class P0098_ValidateBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValidBST(TreeNode.create("[2,1,3]")));// true
        System.out.println(solution.isValidBST(TreeNode.create("[5,1,4,null,null,3,6]")));// false
        System.out.println(solution.isValidBST(TreeNode.create("[10,5,15,null,null,6,20]")));// false
        System.out.println(solution.isValidBST(TreeNode.create("[]")));// true
        System.out.println(solution.isValidBST(TreeNode.create("[1,1]")));// false
        System.out.println(solution.isValidBST(TreeNode.create("[2147483647]")));// true
    }

    // 递归遍历
    // 遍历树节点，根据二叉搜索树来进行判断
    // 需要注意的是，左侧子树的任意节点都必须小于右侧子树的任意节点
    // 所以遍历时候可以传递一个最大最小值的范围
    // 另外，节点值是INT,还需要考虑溢出的问题
    // 时间复杂度：O(N)，N为节点个数
    // 空间复杂度：O(N)
    static class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isValidBST(TreeNode root, long min, long max) {
            if (root == null) {
                return true;
            }
            if (root.val <= min || max <= root.val) {
                return false;
            }
            return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        }
    }
}
