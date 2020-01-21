package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>不同的二叉搜索树 II</h3><br>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的“二叉搜索树”。<br>
 * 
 * <pre>
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5种不同结构的二叉搜索树：
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * </pre>
 */
public class P0095_UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> trees = solution.generateTrees(3);
        for (TreeNode tree : trees) {
            System.out.println(tree);
        }
    }

    // 递归求解
    // 题目要求是“二叉搜索树”，二叉搜索树的特性是：左节点小于根节点，右节点大于根节点。
    // 从序列 1...n 中取出数字 i，作为当前树的树根。于是，剩余 i - 1 个元素可用于左子树，n - i 个元素用于右子树。
    // 每次会产生 G(i-1)种左子树 和 G(n-i)种右子树
    static class Solution {
        public List<TreeNode> generateTrees(int n) {
            if (n <= 0) {
                return new ArrayList<>();
            }
            return generateTrees(1, n);
        }

        private List<TreeNode> generateTrees(int start, int end) {
            List<TreeNode> result = new ArrayList<>();
            if (start > end) {
                result.add(null);
            } else {
                for (int i = start; i <= end; ++i) {
                    List<TreeNode> left = generateTrees(start, i - 1);
                    List<TreeNode> right = generateTrees(i + 1, end);
                    for (int j = 0; j < left.size(); ++j) {
                        for (int k = 0; k < right.size(); ++k) {
                            TreeNode root = new TreeNode(i);
                            root.left = left.get(j);
                            root.right = right.get(k);
                            result.add(root);
                        }
                    }
                }
            }
            return result;
        }
    }
}
