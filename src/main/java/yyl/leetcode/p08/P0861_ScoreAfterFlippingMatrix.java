package yyl.leetcode.p08;

import yyl.leetcode.util.Assert;

/**
 * <h3>翻转矩阵后的得分</h3><br>
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。<br>
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。<br>
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。<br>
 * 返回尽可能高的分数。<br>
 * 
 * <pre>
 * 示例：
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= A.length <= 20<br>
 * ├ 1 <= A[0].length <= 20<br>
 * └ A[i][j] 是 0 或 1<br>
 **/

public class P0861_ScoreAfterFlippingMatrix {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(39, solution.matrixScore(new int[][] { //
                { 0, 0, 1, 1 }, //
                { 1, 0, 1, 0 }, //
                { 1, 1, 0, 0 }//
        }));
    }

    // 贪心
    // 为了得到最高的分数，矩阵的每一行的最左边的数都必须为 1。为了做到这一点，可以翻转那些最左边的数不为 1的那些行，而其他的行则保持不动。
    // 当将每一行的最左边的数都变为 1 之后，就只能进行列翻转了。为了使得总得分最大，要让每个列中 1的数目尽可能多。
    // 因此，扫描除了最左边的列以外的每一列，如果该列 0的数目多于 1的数目，就翻转该列，其他的列则保持不变。
    // 最后完成反转矩阵，计算所得值
    // 时间复杂度：O(mn)，其中 m 为矩阵行数，n 为矩阵列数。
    // 空间复杂度：O(1)。
    static class Solution {
        public int matrixScore(int[][] A) {
            if (A.length == 0 || A[0].length == 0) {
                return 0;
            }
            int m = A.length;
            int n = A[0].length;

            // 先依次反转所有行，保证每行的第0列为1
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 0) {
                    for (int j = 0; j < n; j++) {
                        A[i][j] = 1 - A[i][j];
                    }
                }
            }

            // 因为第0列全为1了，所有从第1列开始反转列。
            for (int j = 1; j < n; j++) {
                // 计算每一列的1的数量
                int ones = 0;
                for (int i = 0; i < m; i++) {
                    if (A[i][j] == 1) {
                        ones++;
                    }
                }
                // 如果1的数量小于一半，那么反转该列。
                if (ones < m / 2.0) {
                    for (int i = 0; i < m; i++) {
                        A[i][j] = 1 - A[i][j];
                    }
                }
            }
            // 完成反转矩阵，计算所得值
            int result = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    result += A[i][j] << (n - j - 1);
                }
            }
            return result;
        }
    }
}
