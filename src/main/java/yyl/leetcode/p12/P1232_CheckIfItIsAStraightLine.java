package yyl.leetcode.p12;

import yyl.leetcode.util.Assert;

/**
 * <h3>缀点成线</h3><br>
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。<br>
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 
 * 示例 2：
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 * </pre>
 * 
 * 提示： <br>
 * ├ 2 <= coordinates.length <= 1000 <br>
 * ├ coordinates[i].length == 2 <br>
 * ├ -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4 <br>
 * └ coordinates 中不含重复的点 <br>
 */
public class P1232_CheckIfItIsAStraightLine {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // Assert.assertTrue(solution.checkStraightLine(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } }));
        // Assert.assertFalse(solution.checkStraightLine(new int[][] { { 1, 1 }, { 2, 2 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 7, 7 } }));
        // Assert.assertFalse(solution.checkStraightLine(new int[][] { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 6, 7 } }));
        Assert.assertTrue(solution.checkStraightLine(new int[][] { { 0, 0 }, { 0, 1 }, { 0, -1 } }));

    }

    // 数学
    // 如果点在一条直线上，那么斜率一定相同，计算除法时分母可能等于0 需要转浮点，因此可以使用乘法来比较
    // 如果 (Yn-Y0)*(X1-X0)==(Xn-X0) * (Y1-Y0) 则 (X0,Y0),(X1,Y1),(Xn,Yn)共线。
    // 时间复杂度：O(n)，其中 n 是数组中的元素数量。
    // 时间复杂度：O(1)
    static class Solution {
        public boolean checkStraightLine(int[][] coordinates) {
            int n = coordinates.length;
            for (int i = 1; i < n - 1; ++i) {
                int y1 = coordinates[i][1] - coordinates[i - 1][1];
                int x1 = coordinates[i][0] - coordinates[i - 1][0];
                int y2 = coordinates[i + 1][1] - coordinates[i][1];
                int x2 = coordinates[i + 1][0] - coordinates[i][0];
                if (y1 * x2 != y2 * x1) {
                    return false;
                }
            }
            return true;
        }
    }
}
