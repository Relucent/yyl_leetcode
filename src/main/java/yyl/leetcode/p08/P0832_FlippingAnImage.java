package yyl.leetcode.p08;

import yyl.leetcode.util.Assert;

/**
 * <h3>翻转图像</h3><br>
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。<br>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。<br>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * 示例 2：
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= A.length = A[0].length <= 20 <br>
 * └ 0 <= A[i][j] <= 1 <br>
 */
public class P0832_FlippingAnImage {

    public static void main(String[] args) {
        Solution solution = new Solution();

        Assert.assertEquals(new int[][] { //
                { 1, 0, 0 }, //
                { 0, 1, 0 }, //
                { 1, 1, 1 } //
        }, solution.flipAndInvertImage(new int[][] { //
                { 1, 1, 0 }, //
                { 1, 0, 1 }, //
                { 0, 0, 0 } //
        }));

        Assert.assertEquals(new int[][] { //
                { 1, 1, 0, 0 }, //
                { 0, 1, 1, 0 }, //
                { 0, 0, 0, 1 }, //
                { 1, 0, 1, 0 }//
        }, solution.flipAndInvertImage(new int[][] { //
                { 1, 1, 0, 0 }, //
                { 1, 0, 0, 1 }, //
                { 0, 1, 1, 1 }, //
                { 1, 0, 1, 0 } //
        }));
    }

    // 模拟
    // 首先对矩阵 A 的每一行进行水平翻转操作，然后对矩阵中的每个元素进行反转操作。该做法需要遍历矩阵两次。
    // 可以使用双指针来进行水平翻转操做；可以使用异或对元素进行反转操作。
    // 位运算异或说明：两个位相同为0，相异为1
    // 时间复杂度：O(n^2)，其中n 是矩阵 A 的行数和列数。需要遍历矩阵两次。
    // 空间复杂度：O(1)。额外使用的空间为常数。
    static class Solution {
        public int[][] flipAndInvertImage(int[][] A) {
            int n = A.length;
            for (int i = 0; i < n; i++) {
                int left = 0;
                int right = n - 1;
                while (left < right) {
                    int temp = A[i][right];
                    A[i][right] = A[i][left];
                    A[i][left] = temp;
                    left++;
                    right--;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = A[i][j] ^ 1;
                }
            }
            return A;
        }
    }

    // 模拟 + 优化
    // 可以在进行水平翻转操作的同时进行反转，这这样只需要遍历矩阵一次。
    // 时间复杂度：O(n^2)，其中n 是矩阵 A 的行数和列数。需要遍历矩阵一次，进行翻转操作。
    // 空间复杂度：O(1)。额外使用的空间为常数。
    static class Solution1 {
        public int[][] flipAndInvertImage(int[][] A) {
            int n = A.length;
            for (int i = 0; i < n; i++) {
                int left = 0;
                int right = n - 1;
                while (left < right) {
                    int temp = A[i][right];
                    A[i][right] = A[i][left] ^= 1;
                    A[i][left] = temp ^= 1;
                    left++;
                    right--;
                }
                if (left == right) {
                    A[i][left] ^= 1;
                }
            }
            return A;
        }
    }
}
