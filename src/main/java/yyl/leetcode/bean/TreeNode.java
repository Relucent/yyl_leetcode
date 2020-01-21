package yyl.leetcode.bean;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    /**
     * 创建二叉树
     * @param values 二叉树节点数据
     * @return 二叉树跟节点
     */
    public static TreeNode create(String input) {
        // 处理"[","]"
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        // 分割字符串，获取根结点
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        // 将根结点加入到双链表中
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        // 遍历双链表
        while (!nodeQueue.isEmpty()) {
            // 获取双链表的头结点作为当前结点
            TreeNode node = nodeQueue.remove();

            // 判断是否还有结点可以构建左子
            if (index == parts.length) {
                break;
            }

            // 构建当前结点的左子
            item = parts[index++];
            item = item.trim();
            // 判断左子是否为空
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                // 将左子加到双链表中
                nodeQueue.add(node.left);
            }

            // 判断是否还有结点可以构建右子
            if (index == parts.length) {
                break;
            }

            // 构建当前结点的右子
            item = parts[index++];
            item = item.trim();
            // 判断右子是否为空
            if (!item.equals("null")) {
                int rightNumber = Integer.valueOf(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }


    /**
     * 前序遍历，根左右(DLR)
     * @param root 根节点
     * @param action 回调动作
     */
    public void preorderTraversal(TreeNode root, Consumer<TreeNode> action) {
        if (root != null) {
            action.accept(root);
            preorderTraversal(root.left, action);
            preorderTraversal(root.right, action);
        }
    }

    /**
     * 中序遍历，左根右(LDR)
     * @param root 根节点
     * @param action 回调动作
     */
    public void inorderTraversal(TreeNode root, Consumer<TreeNode> action) {
        if (root != null) {
            preorderTraversal(root.left, action);
            action.accept(root);
            preorderTraversal(root.right, action);
        }
    }

    /**
     * 后序遍历，左右根(LRD)
     * @param root 根节点
     * @param action 回调动作
     */
    public void postorderTraversal(TreeNode root, Consumer<TreeNode> action) {
        if (root != null) {
            preorderTraversal(root.left, action);
            preorderTraversal(root.right, action);
            action.accept(root);
        }
    }
}
