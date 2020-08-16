package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>图像渲染</h3><br>
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。<br>
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。<br>
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。<br>
 * 最后返回经过上色渲染后的图像。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析: 
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 * </pre>
 * 
 * 注意:<br>
 * image 和 image[0] 的长度在范围 [1, 50] 内。<br>
 * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。<br>
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。<br>
 */
public class P0733_FloodFill {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
        int sr = 1;
        int sc = 1;
        int newColor = 2;
        int[][] actual = solution.floodFill(image, sr, sc, newColor);
        int[][] expected = { { 2, 2, 2 }, { 2, 2, 0 }, { 2, 0, 1 } };
        Assert.assertEquals(expected, actual);
    }

    // 深度优先搜索
    // 从给定的起点开始，进行深度优先搜索。每次搜索到一个方格时，如果其与初始位置的方格颜色相同，就将该方格的颜色更新，以防止重复搜索；如果不相同，则进行回溯。
    // 时间复杂度：O(m*n)，其中 m和 n分别是二维数组的行数和列数。最坏情况下需要遍历所有的方格一次。
    // 空间复杂度：O(m*n)，其中 m和 n分别是二维数组的行数和列数。主要为栈空间的开销。
    static class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int m = image.length;
            int n = image[0].length;
            int color = image[sr][sc];
            dfs(image, m, n, sr, sc, color, newColor);
            return image;
        }

        private void dfs(int[][] image, int m, int n, int y, int x, int color, int newColor) {
            if (0 <= y && y < m && 0 <= x && x < n && image[y][x] == color && image[y][x] != newColor) {
                image[y][x] = newColor;
                dfs(image, m, n, y - 1, x, color, newColor);
                dfs(image, m, n, y + 1, x, color, newColor);
                dfs(image, m, n, y, x - 1, color, newColor);
                dfs(image, m, n, y, x + 1, color, newColor);
            }
        }
    }
}
