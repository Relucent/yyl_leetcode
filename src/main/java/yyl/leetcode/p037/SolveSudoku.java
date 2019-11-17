package yyl.leetcode.p037;

/**
 * <h3>解数独</h3><br>
 * 一个数独的解法需遵循如下规则：<br>
 * 数字 1-9 在每一行只能出现一次。<br>
 * 数字 1-9 在每一列只能出现一次。<br>
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。<br>
 * 空白格用 '.' 表示。<br>
 * <br>
 * 注：<br>
 * 1. 给定的数独序列只包含数字 1-9 和字符 '.' 。<br>
 * 2. 你可以假设给定的数独只有唯一解。<br>
 * 3. 给定数独永远是 9x9 形式的。<br>
 */
public class SolveSudoku {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] sample = {//
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}, //
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, //
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, //
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, //
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, //
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, //
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, //
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, //
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}//
        };//
        solution.solveSudoku(sample);
        // 5 3 4 6 7 8 9 1 2
        // 6 7 2 1 9 5 3 4 8
        // 1 9 8 3 4 2 5 6 7
        // 8 5 9 7 6 1 4 2 3
        // 4 2 6 8 5 3 7 9 1
        // 7 1 3 9 2 4 8 5 6
        // 9 6 1 5 3 7 2 8 4
        // 2 8 7 4 1 9 6 3 5
        // 3 4 5 2 8 6 1 7 9
        for (char[] cells : sample) {
            for (char cell : cells) {
                System.out.print(cell);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    static class Solution {

        public void solveSudoku(char[][] board) {
            searchSudoku(board);
        }

        // 暴力递归穷举
        private boolean searchSudoku(final char[][] board) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    if (board[row][column] != '.') {
                        continue;
                    }
                    for (char num = '1'; num <= '9'; num++) {// 尝试
                        if (!isValid(board, row, column, num)) {
                            continue;
                        }
                        board[row][column] = num;
                        if (searchSudoku(board)) {
                            return true;
                        } else {
                            board[row][column] = '.';
                        }
                    }
                    return false;
                }
            }
            return true;
        }

        // 尝试
        private boolean isValid(final char[][] board, final int row, final int column, final char num) {
            // 行
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == num) {
                    return false;
                }
            }
            // 列
            for (int i = 0; i < 9; i++) {
                if (board[i][column] == num) {
                    return false;
                }
            }
            // 9宫格
            int rStart = (row / 3) * 3;
            int rEnd = rStart + 3;
            int cStart = (column / 3) * 3;
            int cEnd = cStart + 3;
            for (int i = rStart; i < rEnd; i++) {
                for (int j = cStart; j < cEnd; j++) {
                    if (board[i][j] == num) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
