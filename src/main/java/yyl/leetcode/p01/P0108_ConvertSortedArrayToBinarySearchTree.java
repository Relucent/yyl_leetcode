package yyl.leetcode.p01;

import java.util.ArrayList;
import java.util.List;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>将有序数组转换为二叉搜索树</h3><br>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。<br>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。<br>
 * 
 * <pre>
示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * </pre>
 */
// 二叉搜索树特征：始终满足 [左]<[根]<[右]
// 1、若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值
// 2、若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值
public class P0108_ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = solution.sortedArrayToBST(new int[] { -10, -3, 0, 5, 9 });
        System.out.println(root);
        List<Integer> list = new ArrayList<>();
        TreeNode.inorderTraversal(root, n -> list.add(n.val));
        System.out.println(list);
    }

    // 中序遍历 (二分查找法的核心思想)
    // 1、如果将二叉搜索树按中序遍历的话，得到的就是一个有序数组。
    // 2、那么反过来，根节点应该是有序数组的中间点，从中间点分开为左右两个有序数组，在分别找出其中间点作为左右两个子节点(二分查找法的核心思想)。
    // 时间复杂度：O(n)，其中 n是数组的长度。每个数字只访问一次。
    // 空间复杂度：O(log⁡{n})，其中 n是数组的长度。空间复杂度主要取决于递归栈的深度，递归栈的深度是 O(log{n})。
    static class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null) {
                return null;
            }
            return sortedArrayToBST(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            int mid = (right - left) / 2 + left;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = sortedArrayToBST(nums, left, mid - 1);
            node.right = sortedArrayToBST(nums, mid + 1, right);
            return node;
        }
    }
}
