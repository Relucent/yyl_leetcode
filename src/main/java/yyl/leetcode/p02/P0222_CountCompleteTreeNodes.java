package yyl.leetcode.p02;

import yyl.leetcode.bean.TreeNode;
import yyl.leetcode.util.Assert;

/**
 * <h3>完全二叉树的节点个数</h3><br>
 * 给出一个完全二叉树，求出该树的节点个数。<br>
 * 说明：<br>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。<br>
 * 
 * <pre>
 * 示例:
 * 输入: 
 *    1
 *   / \
 *  2   3
 * / \  /
 * 4  5 6
 * 
 * 输出: 6
 * </pre>
 */
public class P0222_CountCompleteTreeNodes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(6, solution.countNodes(TreeNode.create("[1,2,3,4,5,6]")));
    }

    // 深度优先搜索
    // 时间复杂度 : O(n)，每个节点遍历一次。
    // 空间复杂度 : O(n)，堆栈的空间。
    static class Solution {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
}
