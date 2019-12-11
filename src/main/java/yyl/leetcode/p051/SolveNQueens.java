package yyl.leetcode.p051;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>N皇后</h3><br>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。<br>
 * 
 * <pre>
 * +_A_B_C_D_E_F_G_H_+
 * 8_._._._Q_._._._._8
 * 7_._._._._._._Q_._7
 * 6_._._Q_._._._._._6
 * 5_._._._._._._._Q_5
 * 4_._Q_._._._._._._4
 * 3_._._._._Q_._._._3
 * 2_Q_._._._._._._._2
 * 1_._._._._._Q_._._1
 * +_A_B_C_D_E_F_G_H_+
 * </pre>
 * 
 * 上图为 8 皇后问题的一种解法。<br>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。<br>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。<br>
 * 示例:
 * 
 * <pre>
 * 输入: 4<br>
 * 输出:
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * 
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * </pre>
 */
public class SolveNQueens {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(4));
    }

    // 回溯法求解
    // 一行只可能有一个皇后且一列也只可能有一个皇后。
    // 对于所有的主对角线有 行号+列号=常数，对于所有的次对角线有 行号-列号=常数
    // 时间复杂度 O(N!)
    // 空间复杂度 O(n)
    static class Solution {

        public List<List<String>> solveNQueens(int n) {
            boolean[] columnUsed = new boolean[n]; // 同一列是否有皇后
            boolean[] leftUpDownUsed = new boolean[2 * n - 1]; // 左上至下上是否有皇后
            boolean[] leftDownUpUsed = new boolean[2 * n - 1]; // 左下至右上是否有皇后
            boolean[][] queen = new boolean[n][n];
            List<List<String>> result = new ArrayList<>();
            backtrack(0, n, queen, columnUsed, leftUpDownUsed, leftDownUpUsed, result);
            return result;
        }

        private void backtrack(int y, int n, boolean[][] queen, boolean[] columnUsed, boolean[] leftUpDownUsed, boolean[] leftDownUpUsed,
                List<List<String>> result) {
            if (y == n) {
                result.add(toSolution(queen, n));
            } else {
                for (int x = 0; x < n; x++) {
                    if (!columnUsed[x] && !leftUpDownUsed[x + y] && !leftDownUpUsed[n - 1 + x - y]) {
                        queen[x][y] = columnUsed[x] = leftUpDownUsed[x + y] = leftDownUpUsed[n - 1 + x - y] = true;
                        backtrack(y + 1, n, queen, columnUsed, leftUpDownUsed, leftDownUpUsed, result);
                        queen[x][y] = columnUsed[x] = leftUpDownUsed[x + y] = leftDownUpUsed[n - 1 + x - y] = false;
                    }
                }
            }
        }

        private List<String> toSolution(boolean[][] queen, int n) {
            List<String> solution = new ArrayList<>();
            for (int y = 0; y < n; y++) {
                StringBuilder builder = new StringBuilder(n);
                for (int x = 0; x < n; x++) {
                    if (queen[x][y]) {
                        builder.append("Q");
                    } else {
                        builder.append(".");
                    }
                }
                solution.add(builder.toString());
            }
            return solution;
        }
    }
}
