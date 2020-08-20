package yyl.leetcode.p05;

import yyl.leetcode.util.Assert;

/**
 * <h3>扫雷游戏</h3><br>
 * 让我们一起来玩扫雷游戏！<br>
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。<br>
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：<br>
 * 1、如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。<br>
 * 2、如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。<br>
 * 3、如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。<br>
 * 4、如果在此次点击中，若无更多方块可被揭露，则返回面板。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: 
 * [
 *     ['E', 'E', 'E', 'E', 'E'],
 *     ['E', 'E', 'M', 'E', 'E'],
 *     ['E', 'E', 'E', 'E', 'E'],
 *     ['E', 'E', 'E', 'E', 'E']
 * ]
 * Click : [3,0]
 * 输出: 
 * 
 * [
 *     ['B', '1', 'E', '1', 'B'],
 *     ['B', '1', 'M', '1', 'B'],
 *     ['B', '1', '1', '1', 'B'],
 *     ['B', 'B', 'B', 'B', 'B']
 * ]
 * 
 * 示例 2：
 * 输入: 
 * [
 *     ['B', '1', 'E', '1', 'B'],
 *     ['B', '1', 'M', '1', 'B'],
 *     ['B', '1', '1', '1', 'B'],
 *     ['B', 'B', 'B', 'B', 'B']
 * ]
 * Click : [1,2]
 * 输出: 
 * [
 *     ['B', '1', 'E', '1', 'B'],
 *     ['B', '1', 'X', '1', 'B'],
 *     ['B', '1', '1', '1', 'B'],
 *     ['B', 'B', 'B', 'B', 'B']
 * ]
 * </pre>
 * 
 * 注意：<br>
 * 输入矩阵的宽和高的范围为 [1,50]。<br>
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。<br>
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。<br>
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。<br>
 */
public class P0529_Minesweeper {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // Assert.assertEquals(new char[][] { //
        // { 'B', '1', 'E', '1', 'B' }, //
        // { 'B', '1', 'M', '1', 'B' }, //
        // { 'B', '1', '1', '1', 'B' }, //
        // { 'B', 'B', 'B', 'B', 'B' }, //
        // }, solution.updateBoard(new char[][] { //
        // { 'E', 'E', 'E', 'E', 'E' }, //
        // { 'E', 'E', 'M', 'E', 'E' }, //
        // { 'E', 'E', 'E', 'E', 'E' }, //
        // { 'E', 'E', 'E', 'E', 'E' },//
        // }, new int[] { 3, 0 }));
        Assert.assertEquals(new char[][] { //
                { 'B', '1', 'E', '1', 'B' }, //
                { 'B', '1', 'X', '1', 'B' }, //
                { 'B', '1', '1', '1', 'B' }, //
                { 'B', 'B', 'B', 'B', 'B' }, //
        }, solution.updateBoard(new char[][] { //
                { 'B', '1', 'E', '1', 'B' }, //
                { 'B', '1', 'M', '1', 'B' }, //
                { 'B', '1', '1', '1', 'B' }, //
                { 'B', 'B', 'B', 'B', 'B' }, //
        }, new int[] { 1, 2 }));
    }

    // 深度优先搜索
    // 一次点击过程会从一个位置出发，逐渐向外圈扩散，所以这个题目可以使用「搜索」的方式来实现。
    // 复杂度分析
    // 时间复杂度：O(n*m)，其中 n和m分别代表面板的宽和高。最坏情况下会遍历整个面板。
    // 空间复杂度：O(n*m)。空间复杂度取决于递归的栈深度，而递归栈深度在最坏情况下有可能遍历整个面板。
    static class Solution {

        // 八个方向：↑↗→↘↓↙←↖
        private static final int[][] AROUNDS = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

        public char[][] updateBoard(char[][] board, int[] click) {
            int m = board.length;
            int n = board[0].length;
            int y = click[0];
            int x = click[1];
            // 规则 1
            if (board[y][x] == 'M') {
                board[y][x] = 'X';
            }
            // 其他情况
            else {
                dfs(board, m, n, y, x);
            }
            return board;
        }

        // 深度优先搜索
        private void dfs(char[][] board, int m, int n, int y, int x) {
            // 越界或者已经标记过
            if (!isBounds(m, n, y, x) || board[y][x] != 'E') {
                return;
            }
            // 获得周围M的个数
            int num = 0;
            for (int[] around : AROUNDS) {
                if (isMine(board, m, n, y + around[0], x + around[1])) {
                    num++;
                }
            }
            // 规则 2
            if (num == 0) {
                board[y][x] = 'B';
                for (int[] around : AROUNDS) {
                    dfs(board, m, n, y + around[0], x + around[1]);
                }
            }
            // 规则 3
            else {
                board[y][x] = (char) ('0' + num);
            }
        }

        // 判断是一个M
        private boolean isMine(char[][] board, int m, int n, int y, int x) {
            return isBounds(m, n, y, x) && board[y][x] == 'M';
        }

        // 检测范围
        private boolean isBounds(int m, int n, int y, int x) {
            return 0 <= y && y < m && 0 <= x && x < n;
        }
    }
}
