package yyl.leetcode.p10;

import java.util.ArrayDeque;
import java.util.Deque;

import yyl.leetcode.bean.TreeNode;

/**
 * <h3>从先序遍历还原二叉树</h3><br>
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。<br>
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。<br>
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。<br>
 * 给出遍历输出 S，还原树并返回其根节点 root。<br>
 * 
 * <pre>
 * 示例 1：
 *      1 
 *   ┌──┴──┐ 
 *   2     5 
 * ┌─┴─┐ ┌─┴─┐ 
 * 3   4 6   7 
 * 
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * </pre>
 */
public class P1028_RecoverATreeFromPreorderTraversal {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.recoverFromPreorder("1-2--3--4-5--6--7"));
    }

    // 迭代
    // 用栈维护当前节点的祖先节点集合。
    // 如果当前深度大于栈的长度，将多余的节点出栈，保证栈中只包含当前节点的祖先节点。栈顶为当前节点的父节点。
    // 如果父节点的左子树为空，则将当前值赋给左子树，否则赋值给右子树。
    // 时间复杂度：O(|s|) ，其中|s|是字符串 s的长度。不断地从 S中取出一个节点的信息，直到取完为止。这个过程实际上是对 S 进行了一次遍历。
    // 空间复杂度：O(h)，其中 h 是还原出的二叉树的高度。使用了一个栈进行迭代。由于栈中存放了从根节点到当前节点这一路径上的所有节点，因此最多只会同时有h个节点。
    static class Solution {
        public TreeNode recoverFromPreorder(String S) {
            if (S == null || S.isEmpty()) {
                return null;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();
            int pos = 0;
            while (pos < S.length()) {
                int level = 0;
                while (S.charAt(pos) == '-') {
                    level++;
                    pos++;
                }
                int value = 0;
                while (pos < S.length() && S.charAt(pos) != '-') {
                    value = value * 10 + (S.charAt(pos) - '0');
                    pos++;
                }
                TreeNode node = new TreeNode(value);
                if (level == stack.size()) {
                    if (!stack.isEmpty()) {
                        stack.peek().left = node;
                    }
                } else {
                    while (level != stack.size()) {
                        stack.pop();
                    }
                    stack.peek().right = node;
                }
                stack.push(node);
            }
            return stack.peekLast();
        }
    }
}
