package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>恢复二叉搜索树</h3><br>
 * 二叉搜索树中的两个节点被错误地交换。<br>
 * 请在不改变其结构的情况下，恢复这棵树。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 * 
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 * 输出: [2,1,4,null,null,3]
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * </pre>
 * 
 * 进阶:<br>
 * 使用 O(n) 空间复杂度的解法很容易实现。<br>
 * 你能想出一个只使用常数空间的解决方案吗？<br>
 */
public class P0098_RecoverBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.create("[1,3,null,null,2]");
        solution.recoverTree(root);
        System.out.println(root);
    }


    // 中序遍历(LDR)通用解法
    // 道题要求我们复原一个二叉搜索树，说是其中有两个的顺序被调换
    // 使用用到递归(中序遍历树)
    // 并将所有节点存到一个一维向量中，把所有节点值存到另一个一维向量中，然后对存节点值的一维向量排序，再将排好的数组按顺序赋给节点。
    // 这种解法可针对任意个数目的节点错乱的情况。
    // 时间复杂度：O(NlogN+N)，N为节点数，中序遍历时间复杂度O(N)，快排时间复杂度O(NlogN)
    // 空间复杂度：O(h)，h为树的深度(递归的深度等于二叉树的高度)
    static class Solution {

        public void recoverTree(TreeNode root) {
            List<TreeNode> nodes = new ArrayList<>();
            List<Integer> vals = new ArrayList<>();
            inorder(root, nodes, vals);
            Collections.sort(vals);
            for (int i = 0; i < nodes.size(); i++) {
                nodes.get(i).val = vals.get(i);
            }
        }

        private void inorder(TreeNode root, List<TreeNode> nodes, List<Integer> vals) {
            if (root == null) {
                return;
            }
            inorder(root.left, nodes, vals);
            nodes.add(root);
            vals.add(root.val);
            inorder(root.right, nodes, vals);
        }
    }

    // 中序遍历三指针解法
    // 同样使用用到递归(中序遍历树)
    // 需要三个指针：previous，first，second
    // first，second分别表示第一个和第二个错乱位置的节点，previous指向当前节点的中序遍历的前一个节点。
    // 判断previous和当前节点值的大小，如果previous的大（表示节点错误），则记录节点：first指向previous指的节点，把second指向当前节点。
    // 最后交换first，second
    // 这个解法只针对存在两个节点错误的情况
    // 时间复杂度：O(N)，N为节点数，中序遍历全部节点
    // 空间复杂度：O(h)，h为树的深度(递归的深度等于二叉树的高度)
    static class Solution1 {

        public void recoverTree(TreeNode root) {
            TreeNode[] reference = new TreeNode[3];// previous，first，second
            inorder(root, reference);
            if (reference[1] != null && reference[2] != null) {
                int temp = reference[1].val;
                reference[1].val = reference[2].val;
                reference[2].val = temp;
            }
        }

        private void inorder(TreeNode root, TreeNode[] reference) {
            if (root == null) {
                return;
            }
            inorder(root.left, reference);
            if (reference[0] != null) {
                if (reference[0].val > root.val) {
                    if (reference[1] == null) {
                        reference[1] = reference[0];
                    }
                    reference[2] = root;
                }
            }
            reference[0] = root;
            inorder(root.right, reference);
        }
    }

    // 使用Morris中序遍历算法
    // 在中序遍历中，直接排在某个节点前面的节点，我们称之为该节点的前序节点
    // Morris遍历算法是一种空间复杂度 O(1)的算法
    // Morris算法是利用了null节点记录了返回的路径，所以不需要额外的空间辅助记录返回的路径
    // 算法步骤如下：
    // 1， 根据当前节点，找到其前序节点，如果前序节点的右孩子是空，那么把前序节点的右孩子指向当前节点，然后进入当前节点的左孩子。
    // 2， 如果当前节点的左孩子为空，打印当前节点，然后进入右孩子。
    // 3，如果当前节点的前序节点其右孩子指向了它本身，那么把前序节点的右孩子设置为空(擦除)，打印当前节点，然后进入右孩子。
    // 时间复杂度：O(N)，N为节点数，中序遍历全部节点
    // 空间复杂度：O(1)
    static class Solution2 {
        public void recoverTree(TreeNode root) {
            if (null == root) {
                return;
            }
            // 错误节点1和2
            TreeNode node1 = null;
            TreeNode node2 = null;

            // 使用 Morris算法中序遍历
            TreeNode mostRight = null;
            TreeNode current = root;
            TreeNode previous = null;
            while (current != null) {
                mostRight = current.left;
                if (mostRight != null) {
                    while (mostRight.right != null && mostRight.right != current) {
                        mostRight = mostRight.right;
                    }
                    if (mostRight.right == null) {
                        mostRight.right = current;
                        current = current.left;
                        continue;
                    }
                    // if(mostRight.right==current)
                    else {
                        // 擦除
                        mostRight.right = null;
                    }
                }
                // 发现错误位置节点
                if (previous != null && previous.val > current.val) {
                    node1 = node1 == null ? previous : node1;
                    node2 = current;
                }
                previous = current;
                current = current.right;
            }
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }
    }
}
