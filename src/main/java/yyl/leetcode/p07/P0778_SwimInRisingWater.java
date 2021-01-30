package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>水位上升的泳池中游泳</h3> <br>
 * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。 <br>
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。 <br>
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？ <br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [[0,2],[1,3]]
 * 输出: 3
 * 解释:
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 * 
 * 示例2:
 * 输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * 输出: 16
 * 解释:
 *  #0  #1  #2  #3  #4
 *  24  23  22  21  #5
 * #12 #13 #14 #15 #16
 * #11  17  18  19  20
 * #10  #9  #8  #7  #6
 * 
 * 最终的路线 #进行了标记。
 * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
 * </pre>
 * 
 * 提示: <br>
 * ├ 2 <= N <= 50. <br>
 * └ grid[i][j] 是 [0, ..., N*N - 1] 的排列。<br>
 */

public class P0778_SwimInRisingWater {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.swimInWater(new int[][] { //
                { 0, 2 }, //
                { 1, 3 } //
        }));
        Assert.assertEquals(16, solution.swimInWater(new int[][] { //
                { 0, 1, 2, 3, 4 }, //
                { 24, 23, 22, 21, 5 }, //
                { 12, 13, 14, 15, 16 }, //
                { 11, 17, 18, 19, 20 }, //
                { 10, 9, 8, 7, 6 } //
        }));
    }

    // 判定 + 二分法 + 深度优先搜索
    // 当前这个问题可以转换成为：找一个时刻 t，使得这个二维网格上数值 小于等于 t 的部分，存在一条从左上角到右下角的路径。
    // 这个问题可以转化成一个「判定性」问题，即：是否存在一条从左上角到右下角的路径，其经过的所有边权的最大值不超过 t。
    // ├ 根据题目中的描述，问题具有的 单调性：
    // │├ 如果等待的时间 t 越少，网格上可以游泳的部分就越少，存在从左上角到右下角的一条路径的可能性越小；
    // │└ 如果等待的时间 t 越多，网格上可以游泳的部分就越多，存在从左上角到右下角的一条路径的可能性越大。
    // ├ 时间 t 的边界
    // │ ├ t 一定大于等于网格左上角和右下角，因为水位必须大于等于这两个平台才可以通过
    // │ └ t 一定小于等于网格中最大的值，因为水位等于网格中最大的值的时候，已经可以游到任意位置
    // │ - └ 因为题目规定 grid[i][j] 是 [0, ..., N*N - 1] 的排列，所以最大值是 N^2-1。（如果没有这个限定需要遍历才能获取）
    // └ 可以使用二分查找不断缩小搜索的区间，然后验证该时间是否符合要求
    // 时间复杂度：O(N^2*log⁡N)，其中 N 是方格的边长。最差情况下进行 log(N^2) 次二分查找，每一次二分查找最差情况下要遍历所有单元格一次，时间复杂度为 O(N^2)。总的时间复杂度为 O(N^2log(⁡N^2))=O(2(N^2)*log(⁡N))=O(N^2*log⁡N)
    // 空间复杂度：O(n^2)，数组 visited 的大小为 N^2 ，如果使用深度优先遍历，须要使用的栈的大小最多为 N^2。
    static class Solution {

        private static final int[][] DIRECTION = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        public int swimInWater(int[][] grid) {
            int n = grid.length;
            int left = Math.max(grid[0][0], grid[n - 1][n - 1]);
            int right = n * n - 1;
            int answer = 0;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (dfs(grid, 0, 0, n, new boolean[n][n], middle)) {
                    answer = middle;
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }

            return answer;
        }

        private boolean dfs(int[][] grid, int i, int j, int n, boolean[][] visited, int t) {
            visited[i][j] = true;

            if (i == n - 1 && j == n - 1) {
                return true;
            }

            for (int[] direction : DIRECTION) {
                int di = i + direction[0];
                int dj = j + direction[1];
                if (di < 0 || di >= n || dj < 0 || dj >= n || visited[di][dj] || grid[di][dj] > t) {
                    continue;
                }
                if (dfs(grid, di, dj, n, visited, t)) {
                    return true;
                }
            }
            return false;
        }
    }
}
