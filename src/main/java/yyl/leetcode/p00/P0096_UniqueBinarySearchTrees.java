package yyl.leetcode.p00;

/**
 * <h3>不同的二叉搜索树</h3><br>
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？<br>
 * 
 * <pre>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 以上的输出对应以下 5种不同结构的二叉搜索树：
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * </pre>
 */
public class P0096_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numTrees(3));
    }

    // 公式法
    // 卡塔兰数，英文名Catalan number，是组合数学中一个常出现在各种计数问题中出现的数列。以比利时的数学家欧仁·查理·卡塔兰 (1814–1894)的名字来命名
    // 其前几项为 : 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,16796...
    // 卡特兰数C[n]满足以下递推关系 C[n+1] = C[0]C[n] + C[1]C[n-1] ... C[n]C[0]
    // 卡塔兰数计算公式为：
    // C[0] = 1
    // C[n+1] = ((2(2n+1)​) / (n+2))*C[n]
    // 利用公式可以快速求解
    // 时间复杂度：O(1)
    // 空间复杂度：O(1)
    static class Solution {
        public int numTrees(int n) {
            long c = 1;
            for (int i = 0; i < n; i++) {
                c = ((2 * (2 * i + 1)) / (i + 2)) * c;
            }
            return (int) c;
        }
    }

    // 动态规划
    // 题目要求是“二叉搜索树”，二叉搜索树的特性是：左节点小于根节点，右节点大于根节点。
    // 从序列 1...n 中取出数字 i，作为当前树的树根。于是，剩余 i - 1 个元素可用于左子树，n - i 个元素用于右子树。
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    static class Solution1 {
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; ++i) {
                for (int j = 0; j < i; ++j) {
                    dp[i] += dp[j] * dp[i - j - 1];
                }
            }
            return dp[n];
        }
    }
}
